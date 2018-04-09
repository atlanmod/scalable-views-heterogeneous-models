/**
 */
package traceneoemf.impl;

import fr.inria.atlanmod.neoemf.core.DefaultPersistentEObject;

import java.util.Date;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import trace.Log;
import trace.LogLevel;
import traceneoemf.TraceneoemfPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Log</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link traceneoemf.impl.LogImpl#getMessage <em>Message</em>}</li>
 *   <li>{@link traceneoemf.impl.LogImpl#getSource <em>Source</em>}</li>
 *   <li>{@link traceneoemf.impl.LogImpl#getTimestamp <em>Timestamp</em>}</li>
 *   <li>{@link traceneoemf.impl.LogImpl#getLevel <em>Level</em>}</li>
 *   <li>{@link traceneoemf.impl.LogImpl#getExceptions <em>Exceptions</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LogImpl extends DefaultPersistentEObject implements Log {
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected LogImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return TraceneoemfPackage.Literals.LOG;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected int eStaticFeatureCount() {
    return 0;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getMessage() {
    return (String)eGet(TraceneoemfPackage.Literals.LOG__MESSAGE, true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setMessage(String newMessage) {
    eSet(TraceneoemfPackage.Literals.LOG__MESSAGE, newMessage);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getSource() {
    return (String)eGet(TraceneoemfPackage.Literals.LOG__SOURCE, true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setSource(String newSource) {
    eSet(TraceneoemfPackage.Literals.LOG__SOURCE, newSource);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Date getTimestamp() {
    return (Date)eGet(TraceneoemfPackage.Literals.LOG__TIMESTAMP, true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setTimestamp(Date newTimestamp) {
    eSet(TraceneoemfPackage.Literals.LOG__TIMESTAMP, newTimestamp);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public LogLevel getLevel() {
    return (LogLevel)eGet(TraceneoemfPackage.Literals.LOG__LEVEL, true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setLevel(LogLevel newLevel) {
    eSet(TraceneoemfPackage.Literals.LOG__LEVEL, newLevel);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  @SuppressWarnings("unchecked")
  public EList<trace.Exception> getExceptions() {
    return (EList<trace.Exception>)eGet(TraceneoemfPackage.Literals.LOG__EXCEPTIONS, true);
  }

} //LogImpl
