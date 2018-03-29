import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.atlanmod.emfviews.virtuallinks.ConcreteConcept;
import org.atlanmod.emfviews.virtuallinks.ContributingModel;
import org.atlanmod.emfviews.virtuallinks.VirtualAssociation;
import org.atlanmod.emfviews.virtuallinks.VirtualLink;
import org.atlanmod.emfviews.virtuallinks.VirtualLinksFactory;
import org.atlanmod.emfviews.virtuallinks.WeavingModel;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.emf.JavaPackage;

import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.blueprints.neo4j.option.BlueprintsNeo4jResourceOptions;
import fr.inria.atlanmod.neoemf.data.blueprints.option.BlueprintsResourceOptions;
import fr.inria.atlanmod.neoemf.data.blueprints.util.BlueprintsURI;
import fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory;
import traceneoemf.Log;
import traceneoemf.TraceneoemfPackage;

public class BuildWeavingModelNeoEMF {

  public static void main(String args[]) throws IOException {
    // Create a weaving model by comparing input models
    PersistenceBackendFactoryRegistry.register(BlueprintsURI.SCHEME,
                                               BlueprintsPersistenceBackendFactory.getInstance());

    final String inputModel = "1M";
    final URI leftInput = BlueprintsURI.createFileURI(new File(String.format("models/%s.graphdb", inputModel)));
    final URI rightInput = resourceURI("/java-models/java.xmi");
    final URI output = resourceURI(String.format("/models/weaving-%s.xmi", inputModel));

    long beginTimer = System.currentTimeMillis();

    // Load input models
    Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
    .put("xmi", new XMIResourceFactoryImpl());

    Resource.Factory.Registry.INSTANCE.getProtocolToFactoryMap()
    .put(BlueprintsURI.SCHEME, PersistentResourceFactory.getInstance());
    JavaPackage.eINSTANCE.eClass();
    TraceneoemfPackage.eINSTANCE.eClass();

    Resource left = new ResourceSetImpl().createResource(leftInput);
    Map<Object,Object> options = new HashMap<>();
    options.put(BlueprintsResourceOptions.GRAPH_TYPE,
                BlueprintsNeo4jResourceOptions.GRAPH_TYPE_NEO4J);
    options.put(BlueprintsNeo4jResourceOptions.CACHE_TYPE,
                BlueprintsNeo4jResourceOptions.CacheType.WEAK);
    left.load(options);
    Resource right = new ResourceSetImpl().getResource(rightInput, true);

    // Pre-process right model in a HashMap for faster lookup
    Map<String, List<EObject>> map = new HashMap<>();

    {
      Iterator<EObject> it = right.getAllContents();
      while (it.hasNext()) {
        EObject e = it.next();
        if (e instanceof ClassDeclaration) {
          String key = ((ClassDeclaration) e).getName();
          map.computeIfAbsent(key, (s) -> new ArrayList<>()).add(e);
        }
      }
    }

    // Build the weaving model
    VirtualLinksFactory factory = VirtualLinksFactory.eINSTANCE;
    WeavingModel weavingModel = factory.createWeavingModel();
    EList<VirtualLink> links = weavingModel.getVirtualLinks();
    Map<String, ContributingModel> modelsByURI = new HashMap<>();

    Iterator<EObject> it = left.getAllContents();
    while (it.hasNext()) {
      EObject l = it.next();
      if (l instanceof Log) {
        String key = ((Log) l).getSource().split("\\.")[0];
        for (EObject r : map.get(key)) {
          VirtualAssociation vAsso = factory.createVirtualAssociation();
          vAsso.setName("javaClass");
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

