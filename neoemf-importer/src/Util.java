import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

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
    System.out.printf("Benching %s... %d/%d\n", task, warmups, measures);

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
}
