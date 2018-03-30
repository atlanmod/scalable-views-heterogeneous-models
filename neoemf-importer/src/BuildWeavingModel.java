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

  static void createWeavingModel(Resource traceResource, Resource javaResource, Resource umlResource,
                                 Resource reqResource, Resource output,
                                 Function<EObject, Boolean> leftGuard,
                                 Function<EObject, String> leftKey) throws Exception {
    VirtualLinksFactory factory = VirtualLinksFactory.eINSTANCE;
    WeavingModel weavingModel = factory.createWeavingModel();

    // Match Trace and Java using a fast method
    Instant start = Instant.now();
    matchRule(weavingModel, "javaClass", traceResource,
              leftGuard, leftKey,
              javaResource,
              ClassDeclaration.class::isInstance,
              (c) -> ((ClassDeclaration) c).getName());
    Instant end = Instant.now();
    System.out.printf("javaClass rule populated in %dms\n",
                      ChronoUnit.MILLIS.between(start, end));

    // Match the rest using ECL
    start = Instant.now();
    new EclDelegateMut().createWeavingModel(weavingModel,
                                            resourceURI("/views/java-trace/chain.ecl"),
                                            Arrays.asList(javaResource, umlResource, reqResource));
    end = Instant.now();
    System.out.printf("ECL rules matched in %dms\n",
                      ChronoUnit.MILLIS.between(start, end));

    // @Correctness: the MoDisco contributing models are *not* merged.
    // If it's not an issue, then we can get rid of EclDelegateMut

    // Save the weaving model
    start = Instant.now();
    output.getContents().add(weavingModel);
    output.save(null);
    end = Instant.now();

    System.out.printf("Weaving model saved in %dms\n",
                      ChronoUnit.MILLIS.between(start, end));
  }

  public static void main(String args[]) throws Exception {
    // Create a weaving model by comparing input models


    // Init EMF
    {
      Instant start = Instant.now();
      Map<String, Object> map = Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap();
      map.put("xmi", new XMIResourceFactoryImpl());
      map.put("uml", new UMLResourceFactoryImpl());
      map.put("reqif", new ReqIF10ResourceFactoryImpl());
      JavaPackage.eINSTANCE.eClass();
      TracePackage.eINSTANCE.eClass();
      TraceneoemfPackage.eINSTANCE.eClass();
      ReqIF10Package.eINSTANCE.eClass();
      UMLPackage.eINSTANCE.eClass();

      Instant end = Instant.now();
      System.out.printf("EMF initialized in %dms\n", ChronoUnit.MILLIS.between(start, end));
    }

    // Load models
    Instant start = Instant.now();
    final URI javaInput = resourceURI("/models/petstore-java.xmi");
    final URI umlInput = resourceURI("/models/petstore-components.uml");
    final URI reqInput = resourceURI("/models/petstore-requirements.reqif");

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
    Instant end = Instant.now();
    System.out.printf("Contributing models loaded in %dms\n",
                      ChronoUnit.MILLIS.between(start, end));

    final int[] sizes = {10, 100, 1000, 10000, 100000};

    // Create weaving models for Java trace metamodel
    /*
    System.out.println("--- Weaving models for Java trace metamodel");
    for (int s : sizes) {
      start = Instant.now();
      final URI traceInput = resourceURI(String.format("/models/java-trace/%d.xmi", s));
      final URI output = resourceURI(String.format("/views/java-trace/weaving-%d.xmi", s));
      Resource traceResource = new ResourceSetImpl().getResource(traceInput, true);
      createWeavingModel(traceResource, javaResource, umlResource, reqResource,
                         new ResourceSetImpl().createResource(output));
      end = Instant.now();
      System.out.printf("Weaving model for %s created in %dms\n",
                        traceInput.toString(),
                        ChronoUnit.MILLIS.between(start, end));
    }*/

    // Create weaving models for NeoEMF trace metamodel
    PersistenceBackendFactoryRegistry.register(BlueprintsURI.SCHEME,
                                               BlueprintsPersistenceBackendFactory.getInstance());
    Resource.Factory.Registry.INSTANCE.getProtocolToFactoryMap()
    .put(BlueprintsURI.SCHEME, PersistentResourceFactory.getInstance());
    TraceneoemfPackage.eINSTANCE.eClass();

    System.out.println("--- Weaving models for NeoEMF trace metamodel");
    Map<Object,Object> options = new HashMap<>();
    options.put(BlueprintsResourceOptions.GRAPH_TYPE,
                BlueprintsNeo4jResourceOptions.GRAPH_TYPE_NEO4J);
    options.put(BlueprintsNeo4jResourceOptions.CACHE_TYPE,
                BlueprintsNeo4jResourceOptions.CacheType.WEAK);
    for (int s : sizes) {
      start = Instant.now();
      final URI traceInput = BlueprintsURI.createFileURI(new File(String.format("models/neoemf-trace/%d.graphdb", s)));
      final URI output = resourceURI(String.format("/views/neoemf-trace/weaving-%d.xmi", s));
      Resource traceResource = new ResourceSetImpl().createResource(traceInput);
      traceResource.load(options);
      createWeavingModel(traceResource, javaResource, umlResource, reqResource,
                         new ResourceSetImpl().createResource(output),
                         traceneoemf.Log.class::isInstance,
                         (l) -> ((traceneoemf.Log) l).getSource().split("\\.")[0]);
      end = Instant.now();
      System.out.printf("Weaving model for %s created in %dms\n",
                        traceInput.toString(),
                        ChronoUnit.MILLIS.between(start, end));
      ((PersistentResource) traceResource).close();
    }
  }

  static String here = new File(".").getAbsolutePath();

  static URI resourceURI(String relativePath) {
    return URI.createFileURI(here + relativePath);
  }
}
