import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.function.Function;

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

public class Creator {

  static void createJavaTrace(int size, Resource r) {
    final int iterations = size / 10;

    final Instant start = Instant.now();

    final trace.TraceFactory factory = trace.TraceFactory.eINSTANCE;
    final trace.Trace t = factory.createTrace();
    for (int i=0; i < iterations; ++i) {
      ArrayList<trace.Log> logs = new ArrayList<>();

      {
        trace.Log log = factory.createLog();
        log.setMessage("CaptchaValidateFilter:doFilter()");
        log.setSource("CaptchaValidateFilter.doFilter");
        log.setLevel(trace.LogLevel.INFO);
        trace.Exception e = factory.createException();
        e.setMessage("ServletException");
        log.getExceptions().add(e);
        logs.add(log);
      }

      {
        trace.Log log = factory.createLog();
        log.setMessage("ZOOM - Lat and long  - 2 - 47.21 - 5.27");
        log.setSource("MapBean.mapItems");
        log.setLevel(trace.LogLevel.FINE);
        logs.add(log);
      }

      {
        trace.Log log = factory.createLog();
        log.setMessage("Setting proxy to 212.0.18.42:8000.  Make sure server.policy is updated to allow setting System Properties");
        log.setSource("MapBean.lookUpAddress");
        log.setLevel(trace.LogLevel.INFO);
        logs.add(log);
      }

      {
        trace.Log log = factory.createLog();
        log.setMessage("A &quot;proxyHost&quot; and &quot;proxyPort&quot; isn't set as a web.xml context-param.  A proxy server may be necessary to reach the open internet.");
        log.setSource("MapBean.lookUpAddress");
        log.setLevel(trace.LogLevel.INFO);
        logs.add(log);
      }

      {
        trace.Log log = factory.createLog();
        log.setMessage("No addresses for location - 128.12 42.01");
        log.setSource("MapBean.lookUpAddress");
        log.setLevel(trace.LogLevel.INFO);
        logs.add(log);
      }

      {
        trace.Log log = factory.createLog();
        log.setMessage("Matched 6 locations, taking the first one");
        log.setSource("MapBean.lookUpAddress");
        log.setLevel(trace.LogLevel.INFO);
        logs.add(log);
      }

      {
        trace.Log log = factory.createLog();
        log.setMessage("geocoder.lookup.exception");
        log.setSource("MapBean.lookUpAddress");
        log.setLevel(trace.LogLevel.WARNING);
        trace.Exception e = factory.createException();
        e.setMessage("GeocoderLookupException");
        log.getExceptions().add(e);
        logs.add(log);
      }

      {
        trace.Log log = factory.createLog();
        log.setMessage("image_does_not_exist lib/petstore/banana.jpg");
        log.setSource("ImageAction.service");
        log.setLevel(trace.LogLevel.SEVERE);
        logs.add(log);
      }

      {
        trace.Log log = factory.createLog();
        log.setMessage("search.string Brazilian cat");
        log.setSource("SearchIndex.query");
        log.setLevel(trace.LogLevel.INFO);
        logs.add(log);
      }

      {
        trace.Log log = factory.createLog();
        log.setMessage("search.results 0");
        log.setSource("SearchIndex.query");
        log.setLevel(trace.LogLevel.INFO);
        logs.add(log);
      }

      t.getLogs().addAll(logs);
    }

    r.getContents().add(t);

    final Instant end = Instant.now();
    System.out.printf("(Java) trace model populated with %d elements in %dms\n",
                      iterations * 10,
                      ChronoUnit.MILLIS.between(start, end));
  }

  static void createNeoEMFTrace(int size, Resource r) {
    final int iterations = size / 10;

    final Instant start = Instant.now();

    final traceneoemf.TraceneoemfFactory factory = traceneoemf.TraceneoemfFactory.eINSTANCE;
    final traceneoemf.Trace t = factory.createTrace();
    for (int i=0; i < iterations; ++i) {
      ArrayList<traceneoemf.Log> logs = new ArrayList<>();

      {
        traceneoemf.Log log = factory.createLog();
        log.setMessage("CaptchaValidateFilter:doFilter()");
        log.setSource("CaptchaValidateFilter.doFilter");
        log.setLevel(traceneoemf.LogLevel.INFO);
        traceneoemf.Exception e = factory.createException();
        e.setMessage("ServletException");
        log.getExceptions().add(e);
        logs.add(log);
      }

      {
        traceneoemf.Log log = factory.createLog();
        log.setMessage("ZOOM - Lat and long  - 2 - 47.21 - 5.27");
        log.setSource("MapBean.mapItems");
        log.setLevel(traceneoemf.LogLevel.FINE);
        logs.add(log);
      }

      {
        traceneoemf.Log log = factory.createLog();
        log.setMessage("Setting proxy to 212.0.18.42:8000.  Make sure server.policy is updated to allow setting System Properties");
        log.setSource("MapBean.lookUpAddress");
        log.setLevel(traceneoemf.LogLevel.INFO);
        logs.add(log);
      }

      {
        traceneoemf.Log log = factory.createLog();
        log.setMessage("A &quot;proxyHost&quot; and &quot;proxyPort&quot; isn't set as a web.xml context-param.  A proxy server may be necessary to reach the open internet.");
        log.setSource("MapBean.lookUpAddress");
        log.setLevel(traceneoemf.LogLevel.INFO);
        logs.add(log);
      }

      {
        traceneoemf.Log log = factory.createLog();
        log.setMessage("No addresses for location - 128.12 42.01");
        log.setSource("MapBean.lookUpAddress");
        log.setLevel(traceneoemf.LogLevel.INFO);
        logs.add(log);
      }

      {
        traceneoemf.Log log = factory.createLog();
        log.setMessage("Matched 6 locations, taking the first one");
        log.setSource("MapBean.lookUpAddress");
        log.setLevel(traceneoemf.LogLevel.INFO);
        logs.add(log);
      }

      {
        traceneoemf.Log log = factory.createLog();
        log.setMessage("geocoder.lookup.exception");
        log.setSource("MapBean.lookUpAddress");
        log.setLevel(traceneoemf.LogLevel.WARNING);
        traceneoemf.Exception e = factory.createException();
        e.setMessage("GeocoderLookupException");
        log.getExceptions().add(e);
        logs.add(log);
      }

      {
        traceneoemf.Log log = factory.createLog();
        log.setMessage("image_does_not_exist lib/petstore/banana.jpg");
        log.setSource("ImageAction.service");
        log.setLevel(traceneoemf.LogLevel.SEVERE);
        logs.add(log);
      }

      {
        traceneoemf.Log log = factory.createLog();
        log.setMessage("search.string Brazilian cat");
        log.setSource("SearchIndex.query");
        log.setLevel(traceneoemf.LogLevel.INFO);
        logs.add(log);
      }

      {
        traceneoemf.Log log = factory.createLog();
        log.setMessage("search.results 0");
        log.setSource("SearchIndex.query");
        log.setLevel(traceneoemf.LogLevel.INFO);
        logs.add(log);
      }

      t.getLogs().addAll(logs);
    }

    r.getContents().add(t);

    final Instant end = Instant.now();
    System.out.printf("(NeoEMF) trace model populated with %d elements in %dms\n",
                      iterations * 10,
                      ChronoUnit.MILLIS.between(start, end));
  }

  static void createView(int size, File file, Function<Integer, String> modelFile) throws IOException {
    Properties p = new Properties();
    p.setProperty("viewpoint", "chain.eviewpoint");
    p.setProperty("contributingModels",
                  String.format("../../models/petstore-requirements.reqif,../../models/petstore-components.uml,../../models/petstore-java.xmi,../../models/%s",
                                modelFile.apply(size)));
    p.setProperty("weavingModel", String.format("weaving-%d.xmi", size));
    Writer w = new BufferedWriter(new FileWriter(file));
    p.store(w, null);
    w.close();
  }

  public static void main(String args[]) throws IOException {
    // Init EMF + NeoEMF
    Instant start = Instant.now();

    PersistenceBackendFactoryRegistry.register(BlueprintsURI.SCHEME,
                                               BlueprintsPersistenceBackendFactory.getInstance());
    ResourceSet rs = new ResourceSetImpl();
    rs.getResourceFactoryRegistry().getExtensionToFactoryMap()
    .put("xmi", new XMIResourceFactoryImpl());
    rs.getResourceFactoryRegistry()
    .getProtocolToFactoryMap()
    .put(BlueprintsURI.SCHEME,
         PersistentResourceFactory.getInstance());

    Instant end = Instant.now();
    System.out.printf("EMF and NeoEMF initialized in %dms\n",
                      ChronoUnit.MILLIS.between(start, end));

    // Create trace models
    final int[] sizes = {10, 100, 1000, 10000, 100000};

    for (int s : sizes) {
      start = Instant.now();
      final Resource r = rs.createResource(URI.createFileURI(String.format("models/java-trace/%d.xmi", s)));
      createJavaTrace(s, r);
      r.save(null);
      end = Instant.now();
      System.out.printf("(Java) trace model of size %d created and saved in %dms\n",
                        s, ChronoUnit.MILLIS.between(start, end));
    }

    Map<Object,Object> graphOptions = new HashMap<>();
    graphOptions.put(BlueprintsResourceOptions.GRAPH_TYPE,
                     BlueprintsNeo4jResourceOptions.GRAPH_TYPE_NEO4J);
    graphOptions.put(BlueprintsNeo4jResourceOptions.CACHE_TYPE,
                     BlueprintsNeo4jResourceOptions.CacheType.WEAK);
    for (int s : sizes) {
      start = Instant.now();
      final Resource r = rs.createResource(BlueprintsURI.createFileURI(new File(String.format("models/neoemf-trace/%d.graphdb", s))));
      createNeoEMFTrace(s, r);
      r.save(graphOptions);
      ((PersistentResource) r).close();
      end = Instant.now();
      System.out.printf("(NeoEMF) trace model of size %d created and saved in %dms\n",
                        s, ChronoUnit.MILLIS.between(start, end));
    }

    // Creave view files
    for (int s : sizes) {
      createView(s, new File(String.format("views/java-trace/%d.eview", s)), (si) -> String.format("java-trace/%d.xmi", si));
      createView(s, new File(String.format("views/neoemf-trace/%d.eview", s)), (si) -> String.format("neoemf-trace/%d.graphdb", si));
    }
  }
}
