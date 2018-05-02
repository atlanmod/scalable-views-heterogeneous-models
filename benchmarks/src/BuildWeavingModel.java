import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

import org.atlanmod.emfviews.virtuallinks.CommonVirtualLinksFactory;
import org.atlanmod.emfviews.virtuallinks.ConcreteConcept;
import org.atlanmod.emfviews.virtuallinks.ContributingModel;
import org.atlanmod.emfviews.virtuallinks.VirtualAssociation;
import org.atlanmod.emfviews.virtuallinks.VirtualLink;
import org.atlanmod.emfviews.virtuallinks.VirtualLinksFactory;
import org.atlanmod.emfviews.virtuallinks.WeavingModel;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.gmt.modisco.java.cdo.java.JavaPackage;
import org.eclipse.rmf.reqif10.ReqIF10Package;
import org.eclipse.rmf.reqif10.serialization.ReqIF10ResourceFactoryImpl;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.internal.resource.UMLResourceFactoryImpl;

import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.blueprints.neo4j.option.BlueprintsNeo4jOptionsBuilder;
import fr.inria.atlanmod.neoemf.data.blueprints.util.BlueprintsURI;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory;
import trace.Log;
import trace.TracePackage;
import traceneoemf.TraceneoemfPackage;
import virtuallinksneoemf.VirtuallinksneoemfFactory;

public class BuildWeavingModel {

  static void matchRule(WeavingModel weavingModel, String ruleName,
                        Resource left,
                        Predicate<EObject> leftGuard,
                        Function<EObject, String> leftKey,
                        Resource right,
                        Predicate<EObject> rightGuard,
                        Function<EObject, String> rightKey,
                        Map<String, ContributingModel> modelsByURI,
                        Map<ContributingModel, Map<String, ConcreteConcept>> conceptsForModel,
                        CommonVirtualLinksFactory factory) {

    // Pre-process right model in a HashMap for faster lookup
    Map<String, List<EObject>> map = new HashMap<>();

    {
      Iterator<EObject> it = right.getAllContents();
      while (it.hasNext()) {
        EObject e = it.next();
        if (rightGuard.test(e)) {
          map.computeIfAbsent(rightKey.apply(e), (s) -> new ArrayList<>()).add(e);
        }
      }
    }

    // Build the weaving model
    List<VirtualLink> links = weavingModel.getVirtualLinks();

    // If the resource is in NeoEMF, we can use getAllInstances for much
    // faster lookup
    Iterator<EObject> it;
    if (left instanceof PersistentResource) {
      it = ((PersistentResource) left).getAllInstances(TraceneoemfPackage.Literals.LOG).iterator();
    } else {
      it = Util.asStream(left.getAllContents()).filter(leftGuard).iterator();
    }

    while (it.hasNext()) {
      EObject l = it.next();
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

        // Get left concept
        {
          String sourceModelURI = l.eClass().getEPackage().getNsURI();
          ContributingModel sourceModel =
              modelsByURI.computeIfAbsent(sourceModelURI,
                                          (uri) -> {
                                            ContributingModel m = factory.createContributingModel();
                                            m.setURI(uri);
                                            weavingModel.getContributingModels().add(m);
                                            return m;
                                          });

          Map<String, ConcreteConcept> sourceConcepts =
              conceptsForModel.computeIfAbsent(sourceModel, (m) -> new HashMap<>() );

          ConcreteConcept lSource =
              sourceConcepts.computeIfAbsent(left.getURIFragment(l),
                                             (uri) -> {
                                               ConcreteConcept c = factory.createConcreteConcept();
                                               c.setModel(sourceModel);
                                               c.setPath(uri);
                                               return c;
                                             });
          vAsso.setSource(lSource);
        }

        // Get right concept
        {
          String targetModelURI = r.eClass().getEPackage().getNsURI();
          ContributingModel targetModel =
              modelsByURI.computeIfAbsent(targetModelURI,
                                          (uri) -> {
                                            ContributingModel m = factory.createContributingModel();
                                            m.setURI(uri);
                                            weavingModel.getContributingModels().add(m);
                                            return m;
                                          });

          Map<String, ConcreteConcept> targetConcepts =
              conceptsForModel.computeIfAbsent(targetModel, (m) -> new HashMap<>() );

          ConcreteConcept lTarget =
              targetConcepts.computeIfAbsent(right.getURIFragment(r),
                                             (uri) -> {
                                               ConcreteConcept c = factory.createConcreteConcept();
                                               c.setModel(targetModel);
                                               c.setPath(uri);
                                               return c;
                                             });

          vAsso.setTarget(lTarget);
        }

        links.add(vAsso);
      }
    }
  }

  static WeavingModel populateWeavingModel(WeavingModel weavingModel,
                                           Resource traceResource, Resource javaResource,
                                           Predicate<EObject> classDeclGuard,
                                           Function<EObject, String> classDeclKey,
                                           Predicate<EObject> packageGuard,
                                           Function<EObject, String> packageKey,
                                           Resource umlResource, Resource reqResource,
                                           CommonVirtualLinksFactory factory) throws Exception {

    Map<String, ContributingModel> modelsByURI = new HashMap<>();
    Map<ContributingModel, Map<String, ConcreteConcept>> conceptsForModel = new HashMap<>();

    // Match Trace and Java using a fast method
    Util.time("populate javaClass rule", () -> {
      matchRule(weavingModel, "javaClass", traceResource,
                Log.class::isInstance,
                (l) -> ((Log) l).getSource().split("\\.")[0],
                javaResource,
                classDeclGuard,
                classDeclKey,
                modelsByURI, conceptsForModel,
                factory);
    });

    // Match Java and UML using the fast method because ECL has
    // troubles loading CDO models
    Util.time("populate components rule", () -> {
      matchRule(weavingModel, "components", javaResource,
                packageGuard,
                packageKey,
                umlResource,
                Component.class::isInstance,
                (c) -> ((Component) c).getName().toLowerCase(),
                modelsByURI, conceptsForModel,
                factory);
    });

    // Match the rest using ECL
    Util.time("match ECL rules", () -> {
      new EclDelegateMut().createWeavingModel(weavingModel,
                                              Util.resourceURI("/views/java-trace/chain.ecl"),
                                              Arrays.asList(umlResource, reqResource),
                                              modelsByURI, conceptsForModel, factory);
    });

   return weavingModel;
  }

  static Resource javaXMIResource;
  static Resource javaCDOResource;
  static Resource umlResource;
  static Resource reqResource;
  static Resource traceResource;

  static void createWeavingModel(URI traceInput, Resource javaResource,
                                 Predicate<EObject> classDeclGuard,
                                 Function<EObject, String> classDeclKey,
                                 Predicate<EObject> packageGuard,
                                 Function<EObject, String> packageKey,
                                 URI output, CommonVirtualLinksFactory factory) throws Exception {
    Util.time("Load trace resource", () -> {
      traceResource = Util.loadResource(traceInput);
    });

    Resource outResource = Util.saveResource(output, BlueprintsNeo4jOptionsBuilder.newBuilder()
                                             .weakCache().directWriteLongListSupport().autocommit(50000).asMap());
    WeavingModel weavingModel = factory.createWeavingModel();
    outResource.getContents().add(weavingModel);

    populateWeavingModel(weavingModel, traceResource, javaResource,
                         classDeclGuard, classDeclKey,
                         packageGuard, packageKey,
                         umlResource, reqResource, factory);

    Util.time("Save weaving model", () -> {
      outResource.save(Util.saveOptions);
      Util.closeResource(outResource);
    });

    Util.closeResource(traceResource);
  }

  public static void main(String args[]) throws Exception {
    // Create a weaving model by comparing input models

    // Init EMF + NeoEMF
    {
      Map<String, Object> map = Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap();
      map.put("xmi", new XMIResourceFactoryImpl());
      map.put("uml", new UMLResourceFactoryImpl());
      map.put("reqif", new ReqIF10ResourceFactoryImpl());

      org.eclipse.gmt.modisco.java.emf.JavaPackage.eINSTANCE.eClass();
      org.eclipse.gmt.modisco.java.cdo.java.JavaPackage.eINSTANCE.eClass();
      TracePackage.eINSTANCE.eClass();
      ReqIF10Package.eINSTANCE.eClass();
      UMLPackage.eINSTANCE.eClass();

      PersistenceBackendFactoryRegistry.register(BlueprintsURI.SCHEME,
                                                 BlueprintsPersistenceBackendFactory.getInstance());
      Resource.Factory.Registry.INSTANCE.getProtocolToFactoryMap()
      .put(BlueprintsURI.SCHEME, PersistentResourceFactory.getInstance());
      TraceneoemfPackage.eINSTANCE.eClass();
    }

    // Load models
    Util.time("Load contributing models", () -> {
      final URI javaXMIInput = Util.resourceURI("/models/petstore-java.xmi");
      final URI javaCDOInput = Util.resourceURI("/models/petstore-java.cdo");
      final URI umlInput = Util.resourceURI("/models/petstore-components.uml");
      final URI reqInput = Util.resourceURI("/models/petstore-requirements.reqif");

      // For some reason, the delegation to a the default package registry does not work,
      // and ECL cannot find the packages above.  Have to force it by copying them to
      // the registry of the resource set instead.  We have to match the resource set
      // with the resource, otherwise ECL will be very confused.
      javaXMIResource = Util.loadResource(javaXMIInput);
      javaXMIResource.getResourceSet().getPackageRegistry().put(JavaPackage.eNS_URI, JavaPackage.eINSTANCE);

      javaCDOResource = Util.loadResource(javaCDOInput);
      // No need to pass the CDO resource since ECL does not use it

      umlResource = new ResourceSetImpl().getResource(umlInput, true);
      umlResource.getResourceSet().getPackageRegistry().put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);

      reqResource = new ResourceSetImpl().getResource(reqInput, true);
      reqResource.getResourceSet().getPackageRegistry().put(ReqIF10Package.eNS_URI, ReqIF10Package.eINSTANCE);
    });

    final int[] sizes = {10, 100, 1000, 10000, 100000, 1000000};
    final int warmups = 0;
    final int measures = 1;

    for (int s : sizes) {
      Util.bench(String.format("Weaving model for Java Trace with %s elements", s),
                 () -> {
                   final URI traceInput = Util.resourceURI("/models/java-trace/%d.xmi", s);
                   final URI output = Util.resourceURI("/views/java-trace/weaving-%d.xmi", s);
                   createWeavingModel(traceInput, javaXMIResource,
                                      org.eclipse.gmt.modisco.java.ClassDeclaration.class::isInstance,
                                      (c) -> ((org.eclipse.gmt.modisco.java.ClassDeclaration) c).getName(),
                                      org.eclipse.gmt.modisco.java.Package.class::isInstance,
                                      (p) -> ((org.eclipse.gmt.modisco.java.Package) p).getName(),
                                      output, VirtualLinksFactory.eINSTANCE);
                 }, warmups, measures);
    }


    for (int s : sizes) {
      Util.bench(String.format("NeoEMF Weaving model for NeoEMF Trace with %s elements", s),
                 () -> {
                   final URI traceInput = Util.resourceURI("/models/neoemf-trace/%d.graphdb", s);
                   final URI output = Util.resourceURI("/views/neoemf-trace/weaving-%d.graphdb", s);
                   createWeavingModel(traceInput, javaCDOResource,
                                      org.eclipse.gmt.modisco.java.cdo.java.ClassDeclaration.class::isInstance,
                                      (c) -> ((org.eclipse.gmt.modisco.java.cdo.java.ClassDeclaration) c).getName(),
                                      org.eclipse.gmt.modisco.java.cdo.java.Package.class::isInstance,
                                      (p) -> ((org.eclipse.gmt.modisco.java.cdo.java.Package) p).getName(),
                                      output, VirtuallinksneoemfFactory.eINSTANCE);
                 }, warmups, measures);
    }

    Util.closeCDOBackend();
  }

}
