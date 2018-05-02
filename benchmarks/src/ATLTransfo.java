import java.util.Map;

import org.atlanmod.emfviews.core.EmfViewsFactory;
import org.atlanmod.emfviews.virtuallinks.VirtualLinksPackage;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
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

import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.blueprints.util.BlueprintsURI;
import fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory;
import trace.TracePackage;
import traceneoemf.TraceneoemfPackage;
import virtuallinksneoemf.VirtuallinksneoemfPackage;

public class ATLTransfo {

  static ExecEnv env;
  static Model targetM;
  static int targetMsize;

  static int runATL(Resource sourceMetamodel, Resource sourceModel) throws Exception {
    Util.time("Initialize ATL", () -> {

      EmftvmFactory factory = EmftvmFactory.eINSTANCE;
      env = factory.createExecEnv();

      ResourceSet rs = new ResourceSetImpl();

      Metamodel sourceMM = factory.createMetamodel();
      sourceMM.setResource(sourceMetamodel);
      env.registerMetaModel("Viewpoint", sourceMM);

      Metamodel targetMM = factory.createMetamodel();
      targetMM.setResource(rs.getResource(Util.resourceURI("/metamodels/table.ecore"), true));
      env.registerMetaModel("Table", targetMM);

      // Load models
      Model sourceM= factory.createModel();
      sourceM.setResource(sourceModel);
      env.registerInputModel("IN", sourceM);

      targetM = factory.createModel();
      // We don't save the resource so the path does not matter
      targetM.setResource(rs.createResource(Util.resourceURI("foo.xmi")));
      env.registerOutputModel("OUT", targetM);

      // Run the transformation
      DefaultModuleResolver mr =
          new DefaultModuleResolver(Util.resourceURI("/transformations/").toString(),
                                    new ResourceSetImpl());

      env.loadModule(mr, "report_with_using");
    });

    Util.time("Run transformation", () -> {
      env.run(null);
    });

    // Return the number of elements, to make sure it did something
    Util.time("Compute size of target model", () -> {
      targetMsize = Util.resourceSize(targetM.getResource());
    });

    return targetMsize;
  }

  static void benchATL(URI metamodelURI, URI modelURI) throws Exception {
    Util.time("Load resources", () -> {
      metamodel = Util.loadResource(metamodelURI);
      model = Util.loadResource(modelURI);
    });

    System.out.printf("Output model size: %d\n", runATL(metamodel, model));

    Util.time("Unload resources", () -> {
      Util.closeResource(metamodel);
      Util.closeResource(model);
    });
  }

  static Resource metamodel;
  static Resource model;

  public static void main(String args[]) throws Exception {

    // Initialize EMF
    Util.time("Initialize EMF", () -> {
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
    });

    final int[] sizes = {10, 100, 1000, 10000, 100000, 1000000};
    final int warmups = 0;
    final int measures = 1;

    // XMI Trace / XMI weaving model
    for (int s : sizes) {
      Util.bench(String.format("Run transformation on XMI trace / XMI weaving model of size %d", s), () -> {
        benchATL(Util.resourceURI("/views/java-trace/chain.eviewpoint"),
                 Util.resourceURI("/views/java-trace/%d.eview", s));
      }, warmups, measures);
    }

    // NeoEMF Trace / NeoEMF weaving model
    for (int s : sizes) {
      Util.bench(String.format("Run transformation on NeoEMF trace / NeoEMF weaving model of size %d", s), () -> {
        benchATL(Util.resourceURI("/views/neoemf-trace/chain.eviewpoint"),
                 Util.resourceURI("/views/neoemf-trace/%d.eview", s));
      }, warmups, measures);
    }

  }
}
