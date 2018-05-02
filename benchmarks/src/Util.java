import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.atlanmod.emfviews.core.View;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import cdobackend.CDOBackend;
import fr.inria.atlanmod.neoemf.data.blueprints.neo4j.option.BlueprintsNeo4jOptionsBuilder;
import fr.inria.atlanmod.neoemf.data.blueprints.util.BlueprintsURI;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

public class Util {
  static Iterable<EObject> asIterable(Resource r) {
    return new Iterable<EObject>() {
      @Override
      public Iterator<EObject> iterator() {
        return r.getAllContents();
      }
    };
  }

  static int resourceSize(Resource r) {
    int size = 0;
    for (EObject o : asIterable(r)) {
      size++;
    }
    return size;
  }

  static String here = new File(".").getAbsolutePath();

  static URI resourceURI(String relativePath) {
    if (relativePath.endsWith(".graphdb")) {
      return BlueprintsURI.createFileURI(new File(here + relativePath));
    } else {
      return URI.createFileURI(here + relativePath);
    }
  }

  static URI resourceURI(String fmt, Object... args) {
    return resourceURI(String.format(fmt, args));
  }

  static void deleteResource(Resource r) throws IOException {
    // Delete the resource from disk
    Files.deleteIfExists(Paths.get(r.getURI().toFileString()));
  }

  static Map<String,Object> loadOptions = BlueprintsNeo4jOptionsBuilder.newBuilder()
      .softCache().directWriteLongListSupport().asMap();
  static Map<String,Object> saveOptions = BlueprintsNeo4jOptionsBuilder.newBuilder()
      .weakCache().directWriteLongListSupport().autocommit().asMap();

  static CDOBackend cdoBackend = new CDOBackend();

  static Resource loadResource(URI uri) throws Exception {
    if (uri.fileExtension().equals("cdo")) {
      Resource cdoResource = cdoBackend.getResource(new File(uri.toFileString()), "res1");
      cdoResource.load(Collections.EMPTY_MAP);
      return cdoResource;
    }

    Resource r = new ResourceSetImpl().createResource(uri);
    if (r instanceof PersistentResource) {
      r.load(loadOptions);
    } else {
      r.load(null);
    }
    return r;
  }

  static void closeCDOBackend() {
    cdoBackend.close();
  }

  static Resource saveResource(URI uri) throws IOException {
    return saveResource(uri, saveOptions);
  }

  static Resource saveResource(URI uri, Map<String, Object> options) throws IOException {
    Resource r = createResource(uri);

    if (r instanceof PersistentResource) {
      // First save to write the options in the database
      r.save(options);
    }

    return r;
  }

  static void delete(File f) throws IOException {
    if (f.isDirectory()) {
      for (File c : f.listFiles())
        delete(c);
    }
    f.delete();
  }

  static Resource createResource(URI uri) throws IOException {
    delete(new File(uri.toFileString()));
    return new ResourceSetImpl().createResource(uri);
  }

  static Resource createResource(String path) throws IOException {
    return createResource(resourceURI(path));
  }

  static Resource createResource(String fmt, Object ...args) throws IOException {
    return createResource(resourceURI(fmt, args));
  }

  static void closeResource(Resource r) {
    if (r instanceof PersistentResource) {
      ((PersistentResource) r).close();
    } else if (r instanceof View) {
      ((View) r).close();
    }
  }

  public interface Thunk {
    void apply() throws Exception;
  }

  static void time(String task, Thunk f) throws Exception {
    Instant start = Instant.now();
    f.apply();
    Instant end = Instant.now();
    Runtime runtime = Runtime.getRuntime();
    long endMemory = runtime.totalMemory() - runtime.freeMemory();
    System.out.printf("%s [%dms] [%dMB]\n", task,
                      ChronoUnit.MILLIS.between(start, end),
                      endMemory / 1000000);
  }

  static void bench(String task, Thunk f,
                    int warmups, int measures) throws Exception {
    System.out.printf("\nBenching %s... (%d warmups/%d measures)\n", task, warmups, measures);

    for (int i=0; i < warmups; ++i) {
      System.out.printf("\n-- Warmup %d\n", i+1);
      time(String.format("-- Warmup %d", i+1), f);
    }

    for (int i=0; i < measures; ++i) {
      System.out.printf("\n== Measure %d\n", i+1);
      time(String.format("== Measure %d", i+1), f);
    }

    System.out.printf("Bench finished: %s\n", task);
  }

  static void bench(String task, Thunk f) throws Exception {
    bench(task, f, 3, 5);
  }

  static <E> Stream<E> asStream(Iterator<E> it, boolean parallel) {
    Iterable<E> iterable = () -> it;
    return StreamSupport.stream(iterable.spliterator(), parallel);
  }

  static <E> Stream<E> asStream(Iterator<E> it) {
    return asStream(it, false);
  }

}
