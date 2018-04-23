import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.m2m.atl.emftvm.impl.resource.EMFTVMResourceFactoryImpl;
import org.eclipse.ocl.pivot.Class;
import org.eclipse.ocl.pivot.evaluation.ModelManager;
import org.eclipse.ocl.pivot.internal.evaluation.PivotModelManager;
import org.eclipse.ocl.pivot.internal.prettyprint.EssentialOCLPrettyPrintVisitor;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.Query;
import org.eclipse.ocl.xtext.essentialocl.EssentialOCLStandaloneSetup;
import org.eclipse.rmf.reqif10.ReqIF10Package;
import org.eclipse.rmf.reqif10.serialization.ReqIF10ResourceFactoryImpl;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.internal.resource.UMLResourceFactoryImpl;

import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.blueprints.util.BlueprintsURI;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory;
import trace.TracePackage;
import traceneoemf.TraceneoemfPackage;
import virtuallinksneoemf.VirtuallinksneoemfPackage;

public class PivotOCLQuery {

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
  static Query query;

  static void benchQuery(URI uri) throws Exception {
    // Need to recreate the query since otherwise it does not get re-evaluated for subsequent resources
    // Init OCL query
    EssentialOCLStandaloneSetup.doSetup();
    OCL ocl = OCL.newInstance(OCL.CLASS_PATH);
/*
    ModelManager oldMM = ocl.getModelManager();

    ocl.setModelManager(new ModelManager() {

      @Override
      public @NonNull Set<@NonNull ? extends Object> get(@NonNull Class arg0) {
        if (oldMM != null) {
          Resource r = arg0.getESObject().eResource();
          if (r instanceof PersistentResource) {
            return new HashSet<>(((PersistentResource) r).getAllInstances((EClass) arg0.getESObject()));
          } else {
            return oldMM.get(arg0);
          }
        } else {
          return new HashSet<>();
        }
      }

    });
*/
    Util.time("Load resource", () -> {
      resource = Util.loadResource(uri);
    });

    Util.time("Create query", () -> {
      // Fetch context from loaded resource since we need VirtualEClass instances here
      // for queries on views to work
      query = ocl.createQuery(ocl.createQuery(resource.getContents().get(0).eClass(),
          "Log.allInstances()->size()"));
    });

    Util.time("Evaluate query", () -> {
      Object res = query.evaluateEcore(resource.getContents().get(0));
      System.out.printf("Result size: %s\n", res);
    });
    Util.time("Unload resource", () -> { Util.closeResource(resource); ocl.dispose(); });
  }

  public static void main(String[] args) throws Exception {
    setUp();

    // Bench
    final int[] sizes = {10, 100, 1000, 10000};
/*
    for (int s : sizes) {
      Util.time(String.format("OCL allInstances query for XMI model of size %d", s), () -> {
        benchQuery(Util.resourceURI("/models/java-trace/%d.xmi", s));
      });
    }
*/
    for (int s : sizes) {
      Util.time(String.format("OCL allInstances query for NeoEMF model of size %d", s), () -> {
        benchQuery(Util.resourceURI("/models/neoemf-trace/%d.graphdb", s));
      });
    }
/*
    for (int s : sizes) {
      Util.time(String.format("OCL allInstances query for view on NeoEMF model of size %d", s), () -> {
        benchQuery(Util.resourceURI("/views/neoemf-trace/trace-%d.eview", s));
      });
    }
*/
  }

}
