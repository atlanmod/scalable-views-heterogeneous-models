import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.ocl.OCL;
import org.eclipse.ocl.Query;
import org.eclipse.ocl.ecore.EcoreEnvironmentFactory;
import org.eclipse.ocl.helper.OCLHelper;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.internal.resource.UMLResourceFactoryImpl;

import org.atlanmod.emfviews.core.EmfViewsFactory;
import org.atlanmod.emfviews.core.EpsilonResource;
import org.atlanmod.emfviews.virtuallinks.VirtualLinksPackage;

import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.blueprints.util.BlueprintsURI;
import fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory;
import trace.TracePackage;
import traceneoemf.TraceneoemfPackage;
import virtuallinksneoemf.VirtuallinksneoemfPackage;

public class OCLQuery {

  static void setUp() {
    // Init EMF
    {
      Map<String, Object> map = Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap();
      map.put("eviewpoint", new EmfViewsFactory());
      map.put("eview", new EmfViewsFactory());
      map.put("xmi", new XMIResourceFactoryImpl());
      map.put("ecore", new EcoreResourceFactoryImpl());
      map.put("uml", new UMLResourceFactoryImpl());
      Resource.Factory epsRF = new Resource.Factory() {
        @Override
        public Resource createResource(URI uri) {
          return new EpsilonResource(uri);
        }
      };
      map.put("csv", epsRF);

      // Load metamodels
      UMLPackage.eINSTANCE.eClass();
      org.eclipse.gmt.modisco.java.emf.JavaPackage.eINSTANCE.eClass();
      org.eclipse.gmt.modisco.java.cdo.java.JavaPackage.eINSTANCE.eClass();
      TracePackage.eINSTANCE.eClass();
      TraceneoemfPackage.eINSTANCE.eClass();
      VirtualLinksPackage.eINSTANCE.eClass();
      VirtuallinksneoemfPackage.eINSTANCE.eClass();

      PersistenceBackendFactoryRegistry.register(BlueprintsURI.SCHEME,
                                                 BlueprintsPersistenceBackendFactory.getInstance());
      Resource.Factory.Registry.INSTANCE.getProtocolToFactoryMap()
      .put(BlueprintsURI.SCHEME, PersistentResourceFactory.getInstance());
    }
  }

  static Resource resource;
  static Query<EClassifier, EClass, EObject> query;
  static EObject root;

  static void benchQuery(URI uri, String queryString, boolean fastExtentsMap) throws Exception {
    // Need to recreate the query since otherwise it does not get re-evaluated for subsequent resources
    // Init OCL query
    OCL ocl = OCL.newInstance(EcoreEnvironmentFactory.INSTANCE);
    OCLHelper oclHelper = ocl.createOCLHelper();

    Util.time("Load resource", () -> {
      resource = Util.loadResource(uri);
    });

    Util.time("Get root element", () -> {
      root = resource.getContents().get(0);
    });

    Util.time("Create query", () -> {
      // Fetch context from loaded resource since we need VirtualEClass instances here
      // for queries on views to work
      EObject context = root.eClass();
      if (fastExtentsMap) {
        ocl.setExtentMap(new FastExtentMap(root));
      }
      oclHelper.setContext(context);
      query = ocl.createQuery(oclHelper.createQuery(queryString));
    });

    Util.time("Evaluate query", () -> {
      System.out.printf("Result: %s\n", query.evaluate(root));
    });

    Util.time("Unload resource", () -> {
      ocl.dispose();
      Util.closeResource(resource);
    });
  }

  // The NeoEMF Trace EPackage has a different namespace URI
  static String replaceTrace(String query) {
    return query.replace("trace::", "traceneoemf::");
  }

  public static void main(String[] args) throws Exception {
    if (args.length != 5) {
      System.err.println("Usage: OCLQuery SIZES WARMUPS MEASURES QUERY FAST_EXTENTS_MAP");
      System.exit(1);
    }

    setUp();

    // Bench
    final String allInstances = "trace::Log.allInstances()->size()";
    final String reqToTraces = "csv::Row.allInstances()"
        + "->any(r | r.desc.startsWith('Controller'))"
        + ".components->collect(c | c.javaPackages)->collect(p | p.ownedElements)"
        + "->selectByType(java::ClassDeclaration)"
        + "->collect(c | c.traces)"
        + "->size()";
    final String traceToReqs = "trace::Log.allInstances()"
      + "->any(l | l.message.startsWith('CaptchaValidateFilter'))"
      + ".javaClass._'package'.component.requirements->size()";

    final int[] sizes = Util.parseIntArray(args[0]);
    final int warmups = Integer.parseInt(args[1]);
    final int measures = Integer.parseInt(args[2]);
    String query;
    switch (args[3]) {
    case "allInstances":
      query = allInstances;
      break;
    case "reqToTraces":
      query = reqToTraces;
      break;
    case "traceToReqs":
      query = traceToReqs;
      break;
    default:
      query = allInstances;
    }
    final boolean fastExtentsMap = Boolean.parseBoolean(args[3]);

    // The other two queries can only run on views.
    if (query == allInstances) {
      for (int s : sizes) {
        Util.bench(String.format("OCL allInstances query for XMI model of size %d", s), () -> {
          benchQuery(Util.resourceURI("/models/java-trace/%d.xmi", s), query, fastExtentsMap);
        }, warmups, measures);
      }

      for (int s : sizes) {
        Util.bench(String.format("OCL allInstances query for NeoEMF model of size %d", s), () -> {
          benchQuery(Util.resourceURI("/models/neoemf-trace/%d.graphdb", s), replaceTrace(query), fastExtentsMap);
        }, warmups, measures);
      }

      for (int s : sizes) {
        Util.bench(String.format("OCL query for simple view on NeoEMF model of size %d", s), () -> {
          benchQuery(Util.resourceURI("/views/neoemf-trace/trace-%d.eview", s), replaceTrace(query), fastExtentsMap);
        }, warmups, measures);
      }
    }

    for (int s : sizes) {
      Util.bench(String.format("OCL query for full view on XMI trace of size %d", s), () -> {
        benchQuery(Util.resourceURI("/views/java-trace/%d.eview", s), query, fastExtentsMap);
      }, warmups, measures);
    }

    for (int s : sizes) {
      Util.bench(String.format("OCL query for full view on NeoEMF trace of size %d", s), () -> {
        benchQuery(Util.resourceURI("/views/neoemf-trace/%d.eview", s), replaceTrace(query), fastExtentsMap);
      }, warmups, measures);
    }

  }

}
