import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.blueprints.neo4j.option.BlueprintsNeo4jResourceOptions;
import fr.inria.atlanmod.neoemf.data.blueprints.option.BlueprintsResourceOptions;
import fr.inria.atlanmod.neoemf.data.blueprints.util.BlueprintsURI;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory;
import fr.inria.atlanmod.neoemf.util.logging.NeoLogger;
import trace.TracePackage;
import traceneoemf.TraceneoemfPackage;

public class Importer {
  public static void main(String[] args) throws IOException {
    // Register the EPackage as side-effect
    TracePackage.eINSTANCE.eClass();
    TraceneoemfPackage.eINSTANCE.eClass();

    PersistenceBackendFactoryRegistry.register(BlueprintsURI.SCHEME,
                                               BlueprintsPersistenceBackendFactory.getInstance());

    ResourceSet rs = new ResourceSetImpl();
    rs.getResourceFactoryRegistry().getExtensionToFactoryMap()
    .put("xmi", new XMIResourceFactoryImpl());
    rs.getResourceFactoryRegistry()
    .getProtocolToFactoryMap()
    .put(BlueprintsURI.SCHEME,
         PersistentResourceFactory.getInstance());

    String modelName = "10";
    String inputFilename = String.format("models/java-trace/%s.xmi", modelName);
    String outputFilename = String.format("models/neoemf-trace/%s.graphdb", modelName);

    long beginTimer = System.currentTimeMillis();

    Resource graphResource = rs.createResource(BlueprintsURI.createFileURI(new File(outputFilename)));

    Map<Object,Object> graphOptions = new HashMap<>();
    graphOptions.put(BlueprintsResourceOptions.GRAPH_TYPE,
                     BlueprintsNeo4jResourceOptions.GRAPH_TYPE_NEO4J);
    graphOptions.put(BlueprintsNeo4jResourceOptions.CACHE_TYPE,
                     BlueprintsNeo4jResourceOptions.CacheType.WEAK);
    graphResource.save(graphOptions);

    Resource xmiResource = rs.createResource(URI.createURI(inputFilename));
    xmiResource.load(null);
    graphResource.getContents().addAll(xmiResource.getContents());
    graphResource.save(graphOptions);

    long endTimer = System.currentTimeMillis();
    NeoLogger.info("Graph Model created in " + ((endTimer - beginTimer)/1000) + " seconds");

    ((PersistentResource) graphResource).close();
  }
}
