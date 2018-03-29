import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.atlanmod.emfviews.virtuallinks.ConcreteConcept;
import org.atlanmod.emfviews.virtuallinks.ContributingModel;
import org.atlanmod.emfviews.virtuallinks.VirtualAssociation;
import org.atlanmod.emfviews.virtuallinks.VirtualLink;
import org.atlanmod.emfviews.virtuallinks.VirtualLinksFactory;
import org.atlanmod.emfviews.virtuallinks.WeavingModel;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.emf.JavaPackage;
import org.eclipse.rmf.reqif10.ReqIF10Package;
import org.eclipse.rmf.reqif10.serialization.ReqIF10ResourceFactoryImpl;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.internal.resource.UMLResourceFactoryImpl;

import trace.Log;
import trace.TracePackage;

public class BuildWeavingModel {

  static void matchRule(WeavingModel weavingModel, String ruleName,
                        Resource left,
                        Function<EObject, Boolean> leftGuard,
                        Function<EObject, String> leftKey,
                        Resource right,
                        Function<EObject, Boolean> rightGuard,
                        Function<EObject, String> rightKey) {

    // Pre-process right model in a HashMap for faster lookup
    Map<String, List<EObject>> map = new HashMap<>();

    {
      Iterator<EObject> it = right.getAllContents();
      while (it.hasNext()) {
        EObject e = it.next();
        if (rightGuard.apply(e)) {
          map.computeIfAbsent(rightKey.apply(e), (s) -> new ArrayList<>()).add(e);
        }
      }
    }

    // Build the weaving model
    VirtualLinksFactory factory = VirtualLinksFactory.eINSTANCE;
    List<VirtualLink> links = weavingModel.getVirtualLinks();
    Map<String, ContributingModel> modelsByURI = new HashMap<>();

    Iterator<EObject> it = left.getAllContents();
    while (it.hasNext()) {
      EObject l = it.next();
      if (leftGuard.apply(l)) {
        String key = leftKey.apply(l);
        List<EObject> matches = map.get(key);
        if (matches == null) {
          continue;
        }
        for (EObject r : matches) {
          VirtualAssociation vAsso = factory.createVirtualAssociation();
          vAsso.setName(ruleName);
          vAsso.setLowerBound(0);
          vAsso.setUpperBound(1);

          ConcreteConcept lSource = factory.createConcreteConcept();
          lSource.setPath(left.getURIFragment(l));

          String sourceModelURI = l.eClass().getEPackage().getNsURI();
          lSource.setModel(modelsByURI.computeIfAbsent(sourceModelURI,
                                                       (uri) -> {
                                                         ContributingModel m = factory.createContributingModel();
                                                         m.setURI(uri);
                                                         weavingModel.getContributingModels().add(m);
                                                         return m;
                                                       }));
          vAsso.setSource(lSource);

          ConcreteConcept lTarget = factory.createConcreteConcept();
          lTarget.setPath(right.getURIFragment(r));

          String targetModelURI = r.eClass().getEPackage().getNsURI();
          lTarget.setModel(modelsByURI.computeIfAbsent(targetModelURI,
                                                       (uri) -> {
                                                         ContributingModel m = factory.createContributingModel();
                                                         m.setURI(uri);
                                                         weavingModel.getContributingModels().add(m);
                                                         return m;
                                                       }));

          vAsso.setTarget(lTarget);

          links.add(vAsso);
        }
      }
    }
  }

  public static void main(String args[]) throws Exception {
    // Create a weaving model by comparing input models

    final String inputModel = "10";
    final URI traceInput = resourceURI(String.format("/models/java-trace/%s.xmi", inputModel));
    final URI javaInput = resourceURI("/models/petstore-java.xmi");
    final URI umlInput = resourceURI("/models/petstore-components.uml");
    final URI reqInput = resourceURI("/models/petstore-requirements.reqif");
    final URI output = resourceURI(String.format("/views/java-trace/weaving-%s.xmi", inputModel));

    long beginTimer = System.currentTimeMillis();

    VirtualLinksFactory factory = VirtualLinksFactory.eINSTANCE;
    WeavingModel weavingModel = factory.createWeavingModel();

    // Load input models
    Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
    .put("xmi", new XMIResourceFactoryImpl());
    Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
    .put("uml", new UMLResourceFactoryImpl());
    Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
    .put("reqif", new ReqIF10ResourceFactoryImpl());
    JavaPackage.eINSTANCE.eClass();
    TracePackage.eINSTANCE.eClass();
    ReqIF10Package.eINSTANCE.eClass();
    UMLPackage.eINSTANCE.eClass();

    // For some reason, the delegation to a the default package registry does not work,
    // and ECL cannot find the packages above.  Have to force it by copying them to
    // the registry of the resource set instead.  We have to match the resource set
    // with the resource, otherwise ECL will be very confused.
    Resource javaResource = new ResourceSetImpl().getResource(javaInput, true);
    javaResource.getResourceSet().getPackageRegistry().put(JavaPackage.eNS_URI, JavaPackage.eINSTANCE);

    Resource umlResource = new ResourceSetImpl().getResource(umlInput, true);
    umlResource.getResourceSet().getPackageRegistry().put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);

    Resource reqResource = new ResourceSetImpl().getResource(reqInput, true);
    reqResource.getResourceSet().getPackageRegistry().put(ReqIF10Package.eNS_URI, ReqIF10Package.eINSTANCE);

    // Match Trace and Java using a fast method
    matchRule(weavingModel, "javaClass", new ResourceSetImpl().getResource(traceInput, true),
              Log.class::isInstance,
              (l) -> ((Log) l).getSource().split("\\.")[0],
              javaResource,
              ClassDeclaration.class::isInstance,
              (c) -> ((ClassDeclaration) c).getName());

    // Match the rest using ECL
    new EclDelegateMut().createWeavingModel(weavingModel,
                                            resourceURI("/views/java-trace/chain.ecl"),
                                            Arrays.asList(javaResource, umlResource, reqResource));

    // Save the weaving model
    Resource out = new ResourceSetImpl().createResource(output);
    out.getContents().add(weavingModel);
    out.save(null);

    long endTimer = System.currentTimeMillis();
    System.out.printf("Weaving model for %s created in %ds\n", inputModel, ((endTimer - beginTimer)/1000));
  }

  static String here = new File(".").getAbsolutePath();

  static URI resourceURI(String relativePath) {
    return URI.createFileURI(here + relativePath);
  }
}
