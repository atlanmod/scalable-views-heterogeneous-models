package benchmarks;

import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.blueprints.BlueprintsPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.blueprints.neo4j.option.BlueprintsNeo4jResourceOptions;
import fr.inria.atlanmod.neoemf.data.blueprints.option.BlueprintsResourceOptions;
import fr.inria.atlanmod.neoemf.data.blueprints.util.BlueprintsURI;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.resource.PersistentResourceFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.atlanmod.emfviews.core.View;
import org.atlanmod.emfviews.virtuallinks.VirtualLinksPackage;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import traceneoemf.TraceneoemfPackage;

public class Benchmark {

  static String here = new File(".").getAbsolutePath();

  static URI resourceURI(String relativePath) {
    return URI.createFileURI(here + relativePath);
  }

  public static void main(String args[]) throws IOException {
    TraceneoemfPackage.eINSTANCE.eClass();
    VirtualLinksPackage.eINSTANCE.eClass();

    Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap()
    .put("xmi", new XMIResourceFactoryImpl());

    PersistenceBackendFactoryRegistry.register(BlueprintsURI.SCHEME,
                                               BlueprintsPersistenceBackendFactory.getInstance());
    Resource.Factory.Registry.INSTANCE.getProtocolToFactoryMap()
    .put(BlueprintsURI.SCHEME, PersistentResourceFactory.getInstance());

    benchView();
    benchNeoEMF();
  }

  static void benchView() throws IOException {
    View v;

    {
      long beginTimer = System.currentTimeMillis();

      v = new View(resourceURI("/views/1M.eview"));
      v.load(null);

      long endTimer = System.currentTimeMillis();

      System.out.printf("View created in %ds\n", (endTimer - beginTimer)/1000);
    }

    {
      long beginTimer = System.currentTimeMillis();

      //int count = v.getContents().get(0).eContents().size();
      int count = 0;
      TreeIterator<EObject> it = v.getAllContents();
      while (it.hasNext()) {
        count++;
        it.next();
      }

      System.out.printf("Model elements: %d\n", count);

      long endTimer = System.currentTimeMillis();

      System.out.printf("Content enumerated in %ds\n", (endTimer - beginTimer)/1000);
    }

    v.unload();
  }

  static void benchNeoEMF() throws IOException {
    Resource r;
    {
      long beginTimer = System.currentTimeMillis();

      r = new ResourceSetImpl()
          .createResource(BlueprintsURI.createFileURI(new File("../neoemf-importer/models/1M.graphdb")));

      Map<Object,Object> options = new HashMap<>();
      options.put(BlueprintsResourceOptions.GRAPH_TYPE,
                  BlueprintsNeo4jResourceOptions.GRAPH_TYPE_NEO4J);
      options.put(BlueprintsNeo4jResourceOptions.CACHE_TYPE,
                  BlueprintsNeo4jResourceOptions.CacheType.WEAK);
      r.load(options);

      long endTimer = System.currentTimeMillis();

      System.out.printf("NeoEMF resource loaded in %ds\n", (endTimer - beginTimer)/1000);
    }

    {
      long beginTimer = System.currentTimeMillis();

      //int count = r.getContents().get(0).eContents().size();
      int count = 0;
      TreeIterator<EObject> it = r.getAllContents();
      while (it.hasNext()) {
        count++;
        it.next();
      }

      System.out.printf("Model elements: %d\n", count);

      long endTimer = System.currentTimeMillis();

      System.out.printf("Content enumerated in %ds\n", (endTimer - beginTimer)/1000);
    }

    ((PersistentResource) r).close();
  }

}
