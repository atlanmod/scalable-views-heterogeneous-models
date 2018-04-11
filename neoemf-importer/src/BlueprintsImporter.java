import java.io.File;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.blueprints.neo4j.option.BlueprintsNeo4jOptionsBuilder;
import fr.inria.atlanmod.neoemf.data.blueprints.util.BlueprintsURI;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory;

public class BlueprintsImporter {

  public static void main(String[] args) throws Exception {
    PersistenceBackendFactoryRegistry.register(BlueprintsURI.SCHEME, BlueprintsPersistenceBackendFactory.getInstance());

    ResourceSet rSet = new ResourceSetImpl();
    rSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
    rSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(BlueprintsURI.SCHEME, PersistentResourceFactory.getInstance());

    final String modelName = "10";
    final String inputFilename = String.format("/views/neoemf-trace/weaving-%s.xmi", modelName);
    final String outputFilename = String.format("/views/neoemf-trace/weaving-%s.graphdb", modelName);
    final File outputFile = new File(Util.here + outputFilename);

    Util.delete(outputFile);

    try (PersistentResource persistentResource = (PersistentResource) rSet.createResource(BlueprintsURI.createFileURI(outputFile))) {
      Map<String, Object> options = BlueprintsNeo4jOptionsBuilder.newBuilder().weakCache().autocommit().asMap();
      persistentResource.save(options);

      Util.time("Import XMI into GraphDB", () ->{
        Resource xmiResource = rSet.createResource(URI.createURI(Util.here + inputFilename));
        xmiResource.load(null);

        persistentResource.getContents().addAll(EcoreUtil.copyAll(xmiResource.getContents()));
        persistentResource.save(options);
      });
    }
  }
}
