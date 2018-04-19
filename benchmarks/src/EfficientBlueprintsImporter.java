import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsPersistenceBackend;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.blueprints.io.BlueprintsHandlerFactory;
import fr.inria.atlanmod.neoemf.data.blueprints.neo4j.option.BlueprintsNeo4jOptionsBuilder;
import fr.inria.atlanmod.neoemf.data.blueprints.util.BlueprintsURI;
import fr.inria.atlanmod.neoemf.io.Importer;
import fr.inria.atlanmod.neoemf.io.persistence.PersistenceHandler;
import fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory;
import virtuallinksneoemf.VirtuallinksneoemfPackage;

public class EfficientBlueprintsImporter {
  public static void main(String[] args) throws Exception {
    // Init EMF + NeoEMF
    {
      VirtuallinksneoemfPackage.eINSTANCE.eClass();
      PersistenceBackendFactoryRegistry.register(BlueprintsURI.SCHEME,
                                                 BlueprintsPersistenceBackendFactory.getInstance());

      Map<String, Object> map = Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap();
      map.put("xmi", new XMIResourceFactoryImpl());
      Resource.Factory.Registry.INSTANCE.getProtocolToFactoryMap()
      .put(BlueprintsURI.SCHEME, PersistentResourceFactory.getInstance());
    }

    final String modelName = "10";
    final String inputFilename = String.format("/views/neoemf-trace/weaving-%s.xmi", modelName);
    final String outputFilename = String.format("/views/neoemf-trace/weaving-%s.graphdb", modelName);
    final File outputFile = new File(Util.here + outputFilename);

    Map<String, Object> options = BlueprintsNeo4jOptionsBuilder.newBuilder().asMap();
    Util.delete(outputFile);

    PersistenceBackendFactory factory = BlueprintsPersistenceBackendFactory.getInstance();
    try (PersistenceBackend backend = factory.createPersistentBackend(outputFile, options)) {
        PersistenceHandler handler = BlueprintsHandlerFactory.createPersistenceHandler((BlueprintsPersistenceBackend) backend, false);

        Util.time("Import XMI into GraphDB", () -> {
          Importer.fromXmi(new FileInputStream(new File(Util.here + inputFilename)), handler);
        });
    }
  }
}
