import java.io.File;
import java.io.IOException;
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

  public static void main(String args[]) throws IOException {
    Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
    .put("eviewpoint", new EmfViewsFactory());
    Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
    .put("eview", new EmfViewsFactory());
    Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
    .put("xmi", new XMIResourceFactoryImpl());
    Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
    .put("ecore", new EcoreResourceFactoryImpl());
    Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
    .put("reqif", new ReqIF10ResourceFactoryImpl());
    Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
    .put("uml", new UMLResourceFactoryImpl());
    Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
    .put("emftvm", new EMFTVMResourceFactoryImpl());

    EmftvmFactory factory = EmftvmFactory.eINSTANCE;
    ExecEnv env = factory.createExecEnv();

    ResourceSet rs = new ResourceSetImpl();

    // Load metamodels
    ReqIF10Package.eINSTANCE.eClass();
    UMLPackage.eINSTANCE.eClass();
    JavaPackage.eINSTANCE.eClass();
    TracePackage.eINSTANCE.eClass();
    VirtualLinksPackage.eINSTANCE.eClass();

    Metamodel sourceMM = factory.createMetamodel();
    sourceMM.setResource(rs.getResource(resourceURI("/views/java-trace/chain.eviewpoint"), true));
    env.registerMetaModel("Viewpoint", sourceMM);

    Metamodel targetMM = factory.createMetamodel();
    targetMM.setResource(rs.getResource(resourceURI("/metamodels/table.ecore"), true));
    env.registerMetaModel("Table", targetMM);

    // Load models
    Model sourceModel = factory.createModel();
    sourceModel.setResource(rs.getResource(resourceURI("/views/java-trace/10.eview"), true));
    env.registerInputModel("IN", sourceModel);

    Model targetModel = factory.createModel();
    // The URI does not actually matter here, as we save the resource to a String
    targetModel.setResource(rs.createResource(resourceURI("/transformations/report-out.xmi")));
    env.registerOutputModel("OUT", targetModel);

    // Run the transformation
    DefaultModuleResolver mr =
        new DefaultModuleResolver(resourceURI("/transformations/").toString(),
                                  new ResourceSetImpl());

    env.loadModule(mr, "report");
    env.run(null);

    // Save it
    targetModel.getResource().save(null);

    System.out.printf("ATL transformation done, saved in %s\n", targetModel.getResource().getURI());
  }
}
