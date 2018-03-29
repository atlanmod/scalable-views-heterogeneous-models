import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
import traceneoemf.Log;
import traceneoemf.LogLevel;
import traceneoemf.Trace;
import traceneoemf.TraceneoemfFactory;

public class Creator {

  public static void main(String args[]) throws IOException {
    PersistenceBackendFactoryRegistry.register(BlueprintsURI.SCHEME,
                                               BlueprintsPersistenceBackendFactory.getInstance());

    ResourceSet rs = new ResourceSetImpl();
    rs.getResourceFactoryRegistry().getExtensionToFactoryMap()
    .put("xmi", new XMIResourceFactoryImpl());
    rs.getResourceFactoryRegistry()
    .getProtocolToFactoryMap()
    .put(BlueprintsURI.SCHEME,
         PersistentResourceFactory.getInstance());

    final int iterations = 1;
    final String modelName = "10";
    final String outputFilename = String.format("models/neoemf-trace/%s.graphdb", modelName);

    long beginTimer = System.currentTimeMillis();

    Resource graphResource = rs.createResource(BlueprintsURI.createFileURI(new File(outputFilename)));

    Map<Object,Object> graphOptions = new HashMap<>();
    graphOptions.put(BlueprintsResourceOptions.GRAPH_TYPE,
                     BlueprintsNeo4jResourceOptions.GRAPH_TYPE_NEO4J);
    graphOptions.put(BlueprintsNeo4jResourceOptions.CACHE_TYPE,
                     BlueprintsNeo4jResourceOptions.CacheType.WEAK);

    // Register the EPackage as side-effect
    TraceneoemfFactory factory = TraceneoemfFactory.eINSTANCE;
    Trace trace = factory.createTrace();
    for (int i=0; i < iterations; ++i) {
      ArrayList<Log> logs = new ArrayList<>();

      {
        Log log = factory.createLog();
        log.setMessage("CaptchaValidateFilter:doFilter()");
        log.setSource("CaptchaValidateFilter.doFilter");
        log.setLevel(LogLevel.INFO);
        traceneoemf.Exception e = factory.createException();
        e.setMessage("ServletException");
        log.getExceptions().add(e);
        logs.add(log);
      }

      {
        Log log = factory.createLog();
        log.setMessage("ZOOM - Lat and long  - 2 - 47.21 - 5.27");
        log.setSource("MapBean.mapItems");
        log.setLevel(LogLevel.FINE);
        logs.add(log);
      }

      {
        Log log = factory.createLog();
        log.setMessage("Setting proxy to 212.0.18.42:8000.  Make sure server.policy is updated to allow setting System Properties");
        log.setSource("MapBean.lookUpAddress");
        log.setLevel(LogLevel.INFO);
        logs.add(log);
      }

      {
        Log log = factory.createLog();
        log.setMessage("A &quot;proxyHost&quot; and &quot;proxyPort&quot; isn't set as a web.xml context-param.  A proxy server may be necessary to reach the open internet.");
        log.setSource("MapBean.lookUpAddress");
        log.setLevel(LogLevel.INFO);
        logs.add(log);
      }

      {
        Log log = factory.createLog();
        log.setMessage("No addresses for location - 128.12 42.01");
        log.setSource("MapBean.lookUpAddress");
        log.setLevel(LogLevel.INFO);
        logs.add(log);
      }

      {
        Log log = factory.createLog();
        log.setMessage("Matched 6 locations, taking the first one");
        log.setSource("MapBean.lookUpAddress");
        log.setLevel(LogLevel.INFO);
        logs.add(log);
      }

      {
        Log log = factory.createLog();
        log.setMessage("geocoder.lookup.exception");
        log.setSource("MapBean.lookUpAddress");
        log.setLevel(LogLevel.WARNING);
        traceneoemf.Exception e = factory.createException();
        e.setMessage("GeocoderLookupException");
        log.getExceptions().add(e);
        logs.add(log);
      }

      {
        Log log = factory.createLog();
        log.setMessage("image_does_not_exist lib/petstore/banana.jpg");
        log.setSource("ImageAction.service");
        log.setLevel(LogLevel.SEVERE);
        logs.add(log);
      }

      {
        Log log = factory.createLog();
        log.setMessage("search.string Brazilian cat");
        log.setSource("SearchIndex.query");
        log.setLevel(LogLevel.INFO);
        logs.add(log);
      }

      {
        Log log = factory.createLog();
        log.setMessage("search.results 0");
        log.setSource("SearchIndex.query");
        log.setLevel(LogLevel.INFO);
        logs.add(log);
      }

      trace.getLogs().addAll(logs);
    }

    graphResource.getContents().add(trace);
    graphResource.save(graphOptions);

    long endTimer = System.currentTimeMillis();
    NeoLogger.info("Graph Model created in " + ((endTimer - beginTimer)/1000) + " seconds");

    ((PersistentResource) graphResource).close();
  }
}
