import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.atlanmod.emfviews.core.View;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ocl.LazyExtentMap;

import fr.inria.atlanmod.neoemf.resource.PersistentResource;

public class FastExtentMap implements Map<EClass, Set<EObject>> {

  private EObject root;
  private LazyExtentMap<EClass, EObject> delegate;

  public FastExtentMap(EObject root) {
    this.root = root;
    delegate = new LazyExtentMap<EClass, EObject>(root) {
      @Override
      protected boolean isInstance(EClass cls, EObject element) {
        return cls.isInstance(element);
      }
    };
  }

  @Override
  public Set<EObject> get(Object key) {
    Resource r = root.eResource();

    if (r instanceof View) {
      EClass k = (EClass) key;
      delegate.put(k, new HashSet<>(((View) r).getAllInstances(k)));
    } else if (r instanceof PersistentResource) {
      EClass k = (EClass) key;
      delegate.put(k, new HashSet<>(((PersistentResource) r).getAllInstances(k)));
    }

    return delegate.get(key);
  }

  @Override
  public int size() {
    return delegate.size();
  }

  @Override
  public boolean isEmpty() {
    return delegate.isEmpty();
  }

  @Override
  public boolean containsKey(Object key) {
    return delegate.containsKey(key);
  }

  @Override
  public boolean containsValue(Object value) {
    return delegate.containsValue(value);
  }

  @Override
  public Set<EObject> put(EClass key, Set<EObject> value) {
    return delegate.put(key, value);
  }

  @Override
  public Set<EObject> remove(Object key) {
    return delegate.remove(key);
  }

  @Override
  public void putAll(Map<? extends EClass, ? extends Set<EObject>> m) {
    delegate.putAll(m);
  }

  @Override
  public void clear() {
    delegate.clear();
  }

  @Override
  public Set<EClass> keySet() {
    return delegate.keySet();
  }

  @Override
  public Collection<Set<EObject>> values() {
    return delegate.values();
  }

  @Override
  public Set<Entry<EClass, Set<EObject>>> entrySet() {
    return delegate.entrySet();
  }

}
