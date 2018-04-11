import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.atlanmod.emfviews.virtuallinks.ConcreteConcept;
import org.atlanmod.emfviews.virtuallinks.ContributingModel;
import org.atlanmod.emfviews.virtuallinks.WeavingModel;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.blueprints.neo4j.option.BlueprintsNeo4jOptionsBuilder;
import fr.inria.atlanmod.neoemf.data.blueprints.util.BlueprintsURI;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory;
import virtuallinksneoemf.VirtuallinksneoemfFactory;

public class BidirectRefBug {

  public static void main(String args[]) throws IOException {
    PersistenceBackendFactoryRegistry.register(BlueprintsURI.SCHEME,
                                               BlueprintsPersistenceBackendFactory.getInstance());
    Resource.Factory.Registry.INSTANCE.getProtocolToFactoryMap()
    .put(BlueprintsURI.SCHEME, PersistentResourceFactory.getInstance());

    {
      // Build weaving model
      VirtuallinksneoemfFactory factory = VirtuallinksneoemfFactory.eINSTANCE;
      WeavingModel wm = factory.createWeavingModel();

      ContributingModel cm = factory.createContributingModel();
      wm.getContributingModels().add(cm);

      ConcreteConcept cc = factory.createConcreteConcept();
      cc.setModel(cm);

      System.out.println("Before saving " + cc.getModel());

      // Save resource
      URI uri = BlueprintsURI.createFileURI(new File("/tmp/foo.graphdb"));
      Map<String,Object> options = BlueprintsNeo4jOptionsBuilder.newBuilder().weakCache().asMap();

      try (PersistentResource r = (PersistentResource) new ResourceSetImpl().createResource(uri)) {
        r.save(options);
        r.getContents().add(wm);
        r.save(options);
      }
    }

    {
      // Load resource
      URI uri = BlueprintsURI.createFileURI(new File("/tmp/foo.graphdb"));
      Map<String,Object> options = BlueprintsNeo4jOptionsBuilder.newBuilder().weakCache().asMap();

      try (PersistentResource r = (PersistentResource) new ResourceSetImpl().createResource(uri)) {
        r.load(options);

        WeavingModel wm = (WeavingModel) r.getContents().get(0);
        System.out.println("After loading " + wm.getContributingModels().get(0).getConcreteElements().get(0).getModel());
      }
    }
  }

}
