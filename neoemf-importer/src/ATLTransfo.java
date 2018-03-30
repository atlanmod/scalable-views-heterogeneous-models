import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;

import org.atlanmod.emfviews.core.EmfViewsFactory;
import org.atlanmod.emfviews.virtuallinks.VirtualLinksPackage;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.gmt.modisco.java.emf.JavaPackage;
import org.eclipse.m2m.atl.emftvm.EmftvmFactory;
import org.eclipse.m2m.atl.emftvm.ExecEnv;
import org.eclipse.m2m.atl.emftvm.Metamodel;
import org.eclipse.m2m.atl.emftvm.Model;
import org.eclipse.m2m.atl.emftvm.impl.resource.EMFTVMResourceFactoryImpl;
import org.eclipse.m2m.atl.emftvm.util.DefaultModuleResolver;
import org.eclipse.rmf.reqif10.ReqIF10Package;
import org.eclipse.rmf.reqif10.serialization.ReqIF10ResourceFactoryImpl;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.internal.resource.UMLResourceFactoryImpl;

import trace.TracePackage;

public class ATLTransfo {

  static String here = new File(".").getAbsolutePath();

  static URI resourceURI(String relativePath) {
    return URI.createFileURI(here + relativePath);
  }

  static int runATL(Resource sourceMetamodel, Resource sourceModel) {
    System.out.println("Initialize ATL transformation...");
    Instant start = Instant.now();

    EmftvmFactory factory = EmftvmFactory.eINSTANCE;
    ExecEnv env = factory.createExecEnv();

    ResourceSet rs = new ResourceSetImpl();

    Metamodel sourceMM = factory.createMetamodel();
    sourceMM.setResource(sourceMetamodel);
    env.registerMetaModel("Viewpoint", sourceMM);

    Metamodel targetMM = factory.createMetamodel();
    targetMM.setResource(rs.getResource(resourceURI("/metamodels/table.ecore"), true));
    env.registerMetaModel("Table", targetMM);

    // Load models
    Model sourceM= factory.createModel();
    sourceM.setResource(sourceModel);
    env.registerInputModel("IN", sourceM);

    Model targetM = factory.createModel();
    // We don't save the resource so the path does not matter
    targetM.setResource(rs.createResource(resourceURI("foo.xmi")));
    env.registerOutputModel("OUT", targetM);

    // Run the transformation
    DefaultModuleResolver mr =
        new DefaultModuleResolver(resourceURI("/transformations/").toString(),
                                  new ResourceSetImpl());

    env.loadModule(mr, "report");

    Instant end = Instant.now();
    System.out.printf("ATL initialized in %dms\n",
                      ChronoUnit.MILLIS.between(start, end));

    System.out.println("Running ATL transformation...");
    start = Instant.now();
    env.run(null);
    end = Instant.now();
    System.out.printf("Transformation ran in %dms\n",
                      ChronoUnit.MILLIS.between(start, end));

    // Return the number of elements, to make sure it did something
    return Util.resourceSize(targetM.getResource());
  }

  public static void main(String args[]) {

    System.out.println("Initializing EMF...");

    Instant start = Instant.now();

    // Initialize EMF
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
      VirtualLinksPackage.eINSTANCE.eClass();
    }

    Instant end = Instant.now();
    System.out.printf("EMF initialized in %dms\n",
                      ChronoUnit.MILLIS.between(start, end));

    // Load resources
    System.out.println("Loading resources...");
    start = Instant.now();

    ResourceSet rs = new ResourceSetImpl();
    Resource metamodel = rs.getResource(resourceURI("/views/java-trace/chain.eviewpoint"), true);
    Resource model = rs.getResource(resourceURI("/views/java-trace/10.eview"), true);

    end = Instant.now();
    System.out.printf("Resources loaded in %dms\n",
                      ChronoUnit.MILLIS.between(start, end));

    // Run the transformation
    runATL(metamodel, model);

    System.out.println("ATL transformation finished\n");
  }
}
