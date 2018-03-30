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
}
