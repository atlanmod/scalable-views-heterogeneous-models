import java.util.Map;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
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

public class LoadView {

  static Resource view;
  static void loadAndCount(URI uri) throws Exception {
    Util.time("Load view", () -> {
      view = Util.loadResource(uri);
    });

    Util.time("Get first element", () -> {
      view.getContents().get(0);
    });

    Util.time("Get all elements", () -> {
      int count = 0;
      TreeIterator<EObject> it = view.getAllContents();
      while (it.hasNext()) {
        count++;
        it.next();
      }
      System.out.printf("View has %d elements\n", count);
    });

    Util.time("Unload view", () -> {
      Util.closeResource(view);
    });
  }

  public static void main(String[] args) throws Exception {
    if (args.length != 3) {
      System.err.println("Usage: LoadView SIZES WARMUPS MEASURES");
      System.exit(1);
    }

    // Initialize EMF
    Util.time("Initialize EMF", () -> {
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
    });

    final int[] sizes = Util.parseIntArray(args[0]);
    final int warmups = Integer.parseInt(args[1]);
    final int measures = Integer.parseInt(args[2]);

    // Test on XMI trace
    for (int s : sizes) {
      Util.bench(String.format("Load XMI trace of size %d", s), () -> {
        loadAndCount(Util.resourceURI("/models/java-trace/%d.xmi", s));
      }, warmups, measures);
    }

    // Test on view with XMI trace
    for (int s : sizes) {
      Util.bench(String.format("Load view with XMI trace of size %d", s), () -> {
        loadAndCount(Util.resourceURI("/views/java-trace/%d.eview", s));
      }, warmups, measures);
    }

    // Test on NeoEMF trace
    for (int s : sizes) {
      Util.bench(String.format("Load NeoEMF trace of size %d", s), () -> {
        loadAndCount(Util.resourceURI("/models/neoemf-trace/%d.graphdb", s));
      }, warmups, measures);
    }

    // Test on view with NeoEMF trace
    for (int s : sizes) {
      Util.bench(String.format("Load view with NeoEMF trace of size %d", s), () -> {
        loadAndCount(Util.resourceURI("/views/neoemf-trace/%d.eview", s));
      }, warmups, measures);
    }

  }
}
