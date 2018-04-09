import java.io.File;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

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
import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.emf.JavaPackage;
import org.eclipse.rmf.reqif10.ReqIF10Package;
import org.eclipse.rmf.reqif10.serialization.ReqIF10ResourceFactoryImpl;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.internal.resource.UMLResourceFactoryImpl;

import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.blueprints.neo4j.option.BlueprintsNeo4jResourceOptions;
import fr.inria.atlanmod.neoemf.data.blueprints.option.BlueprintsResourceOptions;
import fr.inria.atlanmod.neoemf.data.blueprints.util.BlueprintsURI;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory;
import trace.TracePackage;
import traceneoemf.TraceneoemfPackage;

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
  }

  static void createWeavingModel(Resource traceResource, Resource javaResource, Resource umlResource,
                                 Resource reqResource, Resource output,
                                 Function<EObject, Boolean> leftGuard,
                                 Function<EObject, String> leftKey) throws Exception {
    VirtualLinksFactory factory = VirtualLinksFactory.eINSTANCE;
    WeavingModel weavingModel = factory.createWeavingModel();

    // Match Trace and Java using a fast method
    Util.time("populate javaClass rule", () -> {
      matchRule(weavingModel, "javaClass", traceResource,
                leftGuard, leftKey,
                javaResource,
                ClassDeclaration.class::isInstance,
                (c) -> ((ClassDeclaration) c).getName());
    });

    // Match the rest using ECL
    Util.time("match ECL rules", () -> {
      new EclDelegateMut().createWeavingModel(weavingModel,
                                              resourceURI("/views/java-trace/chain.ecl"),
                                              Arrays.asList(javaResource, umlResource, reqResource));
    });

    // @Correctness: the MoDisco contributing models are *not* merged.
    // If it's not an issue, then we can get rid of EclDelegateMut

    // Save the weaving model
    Util.time("save weaving model", () -> {
      output.getContents().add(weavingModel);
      output.save(null);
    });
  }

  static Resource javaResource;
  static Resource umlResource;
  static Resource reqResource;

  static void createJavaWeavingModel(int size) throws Exception {
    final URI traceInput = resourceURI(String.format("/models/java-trace/%d.xmi", size));
    final URI output = resourceURI(String.format("/views/java-trace/weaving-%d.xmi", size));
    Resource traceResource = new ResourceSetImpl().getResource(traceInput, true);
    createWeavingModel(traceResource, javaResource, umlResource, reqResource,
                       new ResourceSetImpl().createResource(output),
                       trace.Log.class::isInstance,
                       (l) -> ((trace.Log) l).getSource().split("\\.")[0]);
  }

  static void createNeoEMFWeavingModel(int size, Map<Object, Object> options) throws Exception {
    final URI traceInput = BlueprintsURI.createFileURI(new File(String.format("models/neoemf-trace/%d.graphdb", size)));
    final URI output = resourceURI(String.format("/views/neoemf-trace/weaving-%d.xmi", size));
    Resource traceResource = new ResourceSetImpl().createResource(traceInput);
    traceResource.load(options);
    createWeavingModel(traceResource, javaResource, umlResource, reqResource,
                       new ResourceSetImpl().createResource(output),
                       traceneoemf.Log.class::isInstance,
                       (l) -> ((traceneoemf.Log) l).getSource().split("\\.")[0]);
    ((PersistentResource) traceResource).close();
  }

  public static void main(String args[]) throws Exception {
    // Create a weaving model by comparing input models

    // Init EMF
    Util.time("Initialize EMF", () -> {
      Map<String, Object> map = Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap();
      map.put("xmi", new XMIResourceFactoryImpl());
      map.put("uml", new UMLResourceFactoryImpl());
      map.put("reqif", new ReqIF10ResourceFactoryImpl());
      JavaPackage.eINSTANCE.eClass();
      TracePackage.eINSTANCE.eClass();
      ReqIF10Package.eINSTANCE.eClass();
      UMLPackage.eINSTANCE.eClass();
    });

    // Load models
    Util.time("Load contributing models", () -> {
      final URI javaInput = resourceURI("/models/petstore-java.xmi");
      final URI umlInput = resourceURI("/models/petstore-components.uml");
      final URI reqInput = resourceURI("/models/petstore-requirements.reqif");

      // For some reason, the delegation to a the default package registry does not work,
      // and ECL cannot find the packages above.  Have to force it by copying them to
      // the registry of the resource set instead.  We have to match the resource set
      // with the resource, otherwise ECL will be very confused.
      javaResource = new ResourceSetImpl().getResource(javaInput, true);
      javaResource.getResourceSet().getPackageRegistry().put(JavaPackage.eNS_URI, JavaPackage.eINSTANCE);

      umlResource = new ResourceSetImpl().getResource(umlInput, true);
      umlResource.getResourceSet().getPackageRegistry().put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);

      reqResource = new ResourceSetImpl().getResource(reqInput, true);
      reqResource.getResourceSet().getPackageRegistry().put(ReqIF10Package.eNS_URI, ReqIF10Package.eINSTANCE);
    });

    final int[] sizes = {10, 100, 1000, 10000, 100000, 1000000};
    final int warmups = 1;
    final int measures = 5;

    // Create weaving models for Java trace metamodel
    System.out.println("--- Weaving models for Java trace metamodel");
    for (int s : sizes) {
      Util.bench(String.format("Weaving model for Java Trace with %s elements", s),
                 () -> createJavaWeavingModel(s), warmups, measures);
    }

    // Create weaving models for NeoEMF trace metamodel
    TraceneoemfPackage.eINSTANCE.eClass();
    PersistenceBackendFactoryRegistry.register(BlueprintsURI.SCHEME,
                                               BlueprintsPersistenceBackendFactory.getInstance());
    Resource.Factory.Registry.INSTANCE.getProtocolToFactoryMap()
    .put(BlueprintsURI.SCHEME, PersistentResourceFactory.getInstance());
    TraceneoemfPackage.eINSTANCE.eClass();
    Map<Object,Object> options = new HashMap<>();
    options.put(BlueprintsResourceOptions.GRAPH_TYPE,
                BlueprintsNeo4jResourceOptions.GRAPH_TYPE_NEO4J);
    options.put(BlueprintsNeo4jResourceOptions.CACHE_TYPE,
                BlueprintsNeo4jResourceOptions.CacheType.WEAK);

    System.out.println("--- Weaving models for NeoEMF trace metamodel");
    for (int s : sizes) {
      Util.bench(String.format("Weaving model for NeoEMF Trace with %s elements", s),
                 () -> createNeoEMFWeavingModel(s, options), warmups, measures);
    }
  }

  static String here = new File(".").getAbsolutePath();

  static URI resourceURI(String relativePath) {
    return URI.createFileURI(here + relativePath);
  }
}
