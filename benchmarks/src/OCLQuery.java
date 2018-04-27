import java.util.Map;

import org.atlanmod.emfviews.core.EmfViewsFactory;
import org.atlanmod.emfviews.virtuallinks.VirtualLinksPackage;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.gmt.modisco.java.emf.JavaPackage;
import org.eclipse.m2m.atl.emftvm.impl.resource.EMFTVMResourceFactoryImpl;
import org.eclipse.ocl.OCL;
import org.eclipse.ocl.Query;
import org.eclipse.ocl.ecore.EcoreEnvironmentFactory;
import org.eclipse.ocl.helper.OCLHelper;
import org.eclipse.rmf.reqif10.ReqIF10Package;
import org.eclipse.rmf.reqif10.serialization.ReqIF10ResourceFactoryImpl;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.internal.resource.UMLResourceFactoryImpl;

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
      map.put("reqif", new ReqIF10ResourceFactoryImpl());
      map.put("uml", new UMLResourceFactoryImpl());
      map.put("emftvm", new EMFTVMResourceFactoryImpl());

      // Load metamodels
      ReqIF10Package.eINSTANCE.eClass();
      UMLPackage.eINSTANCE.eClass();
      JavaPackage.eINSTANCE.eClass();
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

  static void benchQuery(URI uri, String queryString) throws Exception {
    // Need to recreate the query since otherwise it does not get re-evaluated for subsequent resources
    // Init OCL query
    OCL ocl = OCL.newInstance(EcoreEnvironmentFactory.INSTANCE);
    OCLHelper oclHelper = ocl.createOCLHelper();

    Util.time("Load resource", () -> {
      resource = Util.loadResource(uri);
    });

    Util.time("Create query", () -> {
      // Fetch context from loaded resource since we need VirtualEClass instances here
      // for queries on views to work
      EObject root = resource.getContents().get(0);
      EObject context = root.eClass();
      ocl.setExtentMap(new FastExtentMap(root));
      oclHelper.setContext(context);
      oclHelper.setValidating(false);
      query = ocl.createQuery(oclHelper.createQuery(queryString));
    });

    Util.time("Evaluate query", () -> {
      Object res = query.evaluate(resource.getContents().get(0));
      System.out.printf("Result: %s\n", res);
    });
    Util.time("Unload resource", () -> { Util.closeResource(resource); ocl.dispose(); });
  }

  public static void main(String[] args) throws Exception {
    setUp();

    // Bench
    final String allInstances = "Log.allInstances()->size()";
    final String reqToTraces = "reqif10::SpecObject.allInstances()"
        + "->any(r | r.values->selectByType(reqif10::AttributeValueString)->exists(v | v.theValue.startsWith('Controller')))"
        + ".components->collect(c | c.javaPackages)->collect(p | p.ownedElements)"
        + "->selectByType(java::ClassDeclaration)"
        + "->collect(c | c.traces)"
        + "->size()";
    final String traceToReqs = "trace::Log.allInstances()"
        + "->any(l | l.message.startsWith('CaptchaValidateFilter'))"
        + ".javaClass._'package'.component.requirements->size()";

    final int[] sizes = {10, 100, 1000, 10000, 100000, 1000000};
/*
    for (int s : sizes) {
      Util.time(String.format("OCL allInstances query for XMI model of size %d", s), () -> {
        benchQuery(Util.resourceURI("/models/java-trace/%d.xmi", s));
      });
    }

    for (int s : sizes) {
      Util.time(String.format("OCL allInstances query for NeoEMF model of size %d", s), () -> {
        benchQuery(Util.resourceURI("/models/neoemf-trace/%d.graphdb", s));
      });
    }


    for (int s : sizes) {
      Util.time(String.format("OCL query for view on NeoEMF model of size %d", s), () -> {
        benchQuery(Util.resourceURI("/views/neoemf-trace/trace-%d.eview", s), allInstances);
      });
    }

    for (int s : sizes) {
      Util.time(String.format("OCL query for view on XMI trace/ XMI weaving model of size %d", s), () -> {
        benchQuery(Util.resourceURI("/views/java-trace/%d.eview", s), reqToTraces);
      });
    }
*/
    for (int s : sizes) {
      Util.time(String.format("OCL query for view on NeoEMF trace / NeoEMF weaving model of size %d", s), () -> {
        benchQuery(Util.resourceURI("/views/neoemf-trace/neoemf-weaving-%d.eview", s), reqToTraces);
      });
    }


  }

}
