/**
 */
package traceneoemf;

import fr.inria.atlanmod.neoemf.core.PersistentEObject;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Trace</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link traceneoemf.Trace#getLogs <em>Logs</em>}</li>
 * </ul>
 *
 * @see traceneoemf.TraceneoemfPackage#getTrace()
 * @model
 * @extends PersistentEObject
 * @generated
 */
public interface Trace extends PersistentEObject {
  /**
   * Returns the value of the '<em><b>Logs</b></em>' containment reference list.
   * The list contents are of type {@link traceneoemf.Log}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Logs</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Logs</em>' containment reference list.
   * @see traceneoemf.TraceneoemfPackage#getTrace_Logs()
   * @model containment="true"
   * @generated
   */
  EList<Log> getLogs();

} // Trace
