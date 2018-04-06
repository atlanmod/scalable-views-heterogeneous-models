import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;
import java.util.function.Function;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.blueprints.neo4j.option.BlueprintsNeo4jOptionsBuilder;
import fr.inria.atlanmod.neoemf.data.blueprints.util.BlueprintsURI;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory;

public class Creator {

  static void createJavaTrace(int size, Resource r) throws Exception {
    final int iterations = size / 10;

    Util.time("Populate Java trace", () -> {

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
    });
  }

  static void createNeoEMFTrace(int size, Resource r) throws Exception {
    final int iterations = size / 10;

    Util.time("Populate NeoEMF trace", () -> {

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
    });
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

  public static void main(String args[]) throws Exception {
    // Init EMF + NeoEMF
    {
      PersistenceBackendFactoryRegistry.register(BlueprintsURI.SCHEME,
                                                 BlueprintsPersistenceBackendFactory.getInstance());
      Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
      .put("xmi", new XMIResourceFactoryImpl());
      Resource.Factory.Registry.INSTANCE.getProtocolToFactoryMap()
      .put(BlueprintsURI.SCHEME,
           PersistentResourceFactory.getInstance());
    }

    // Create trace models
    final int[] sizes = {10, 100, 1000, 10000, 100000, 1000000};

    for (int s : sizes) {
      Util.time(String.format("Create Java trace model of size %d", s), () -> {
        final Resource r = Util.createResource("/models/java-trace/%d.xmi", s);
        createJavaTrace(s, r);
        r.save(null);
      });
    }

    Map<String,Object> graphOptions = BlueprintsNeo4jOptionsBuilder.newBuilder().weakCache().autocommit().asMap();
    for (int s : sizes) {
      Util.time(String.format("Create NeoEMF trace model of size %d", s), () -> {
        final Resource r = Util.createResource("/models/neoemf-trace/%d.graphdb", s);
        // Save the resource once to set the options
        r.save(graphOptions);
        createNeoEMFTrace(s, r);
        r.save(graphOptions);
        ((PersistentResource) r).close();
      });
    }

    // Creave view files
    for (int s : sizes) {
      createView(s, new File(String.format("views/java-trace/%d.eview", s)), (si) -> String.format("java-trace/%d.xmi", si));
      createView(s, new File(String.format("views/neoemf-trace/%d.eview", s)), (si) -> String.format("neoemf-trace/%d.graphdb", si));
    }
  }
}
