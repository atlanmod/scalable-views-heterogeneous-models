import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.epsilon.ecl.EclModule;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.introspection.IPropertyGetter;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.eol.models.ModelRepository;

import org.atlanmod.emfviews.core.EpsilonResource;
import org.atlanmod.emfviews.core.ViewResource;
import org.atlanmod.emfviews.core.VirtualLinkMatch;

public class VirtualLinkMatcher implements ViewResource.Delegator {
  private static EObject asEObject(Object obj, IModel owner, Map<IModel, EpsilonResource> epsToResource) {
    if (obj instanceof EObject) {
      return (EObject) obj;
    } else {
      return epsToResource.get(owner).asEObject(obj);
    }
  }

  @FunctionalInterface
  interface Key {
     String apply(Object o, IPropertyGetter get) throws EolRuntimeException;
  }

  // Fast matching when the compare rule is just 'leftKey == rightKey'
  private static void matchEq(String linkName,
                              IModel left, String leftType, Key leftKey,
                              IModel right, String rightType, Key rightKey,
                              Map<IModel, EpsilonResource> epsToResource,
                              List<VirtualLinkMatch> matches) throws EolRuntimeException {
    Map<String, List<EObject>> map = new HashMap<>();
    IPropertyGetter get = right.getPropertyGetter();
    for (Object o : right.getAllOfType(rightType)) {
      map.computeIfAbsent(rightKey.apply(o, get), (s) -> new ArrayList<>())
      .add(asEObject(o, right, epsToResource));
    }

    get = left.getPropertyGetter();
    for (Object o : left.getAllOfType(leftType)) {
      String key = leftKey.apply(o, get);
      List<EObject> targets = map.get(key);
      if (targets != null) {
        for (EObject t : targets) {
          VirtualLinkMatch m = new VirtualLinkMatch();
          m.linkName = linkName;
          m.source = asEObject(o, left, epsToResource);
          m.target = t;
          matches.add(m);
        }
      }
    }
  }

  private static void match(String linkName,
                            IModel left, String leftType, String leftKey,
                            IModel right, String rightType, String rightKey,
                            BiFunction<String, String, Boolean> f,
                            Map<IModel, EpsilonResource> epsToResource,
                            List<VirtualLinkMatch> matches) throws EolRuntimeException {
    IPropertyGetter lget = left.getPropertyGetter();
    IPropertyGetter rget = right.getPropertyGetter();

    for (Object l : left.getAllOfType(leftType)) {
      for (Object r : right.getAllOfType(rightType)) {
        String lk = (String) lget.invoke(l, leftKey);
        String rk = (String) rget.invoke(r, rightKey);
        if (f.apply(lk, rk)) {
          VirtualLinkMatch m = new VirtualLinkMatch();
          m.linkName = linkName;
          m.source = asEObject(l, left, epsToResource);
          m.target = asEObject(r, right, epsToResource);
          matches.add(m);
        }
      }
    }
  }

  @Override
  public List<VirtualLinkMatch> match(Map<String,
                                      Resource> models,
                                      EclModule module, Map<IModel, EpsilonResource> epsToResource)
                                        throws EolRuntimeException {
    List<VirtualLinkMatch> matches = new ArrayList<>();

    ModelRepository repo = module.getContext().getModelRepository();

    // Rule 'javaClass'
    IModel trace = repo.getModelByName("trace");
    IModel java = repo.getModelByNameSafe("java");
    if (java == null)
      java = repo.getModelByNameSafe("javacdo");

    matchEq("javaClass",
            trace, "Log",              (o, get) -> ((String) get.invoke(o, "source")).split("\\.")[0],
            java,  "ClassDeclaration", (o, get) ->  (String) get.invoke(o, "name"),
            epsToResource, matches);

    // Rule 'component'
    IModel uml = repo.getModelByName("uml");
    matchEq("component",
            java, "Package",   (o, get) -> ((String) get.invoke(o, "name")),
            uml,  "Component", (o, get) -> ((String) get.invoke(o, "name")).toLowerCase(),
            epsToResource, matches);

    // Rule 'requirements'
    IModel req = repo.getModelByName("req");
    match("requirements",
          uml, "Component", "name",
          req, "Row",       "desc",
          (c, row) -> row.toLowerCase().contains(c.toLowerCase()),
          epsToResource, matches);

    return matches;
  }

}
