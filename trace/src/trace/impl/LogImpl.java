/**
 */
package trace.impl;

import java.util.Collection;
import java.util.Date;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import trace.Log;
import trace.LogLevel;
import trace.TracePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Log</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link trace.impl.LogImpl#getMessage <em>Message</em>}</li>
 *   <li>{@link trace.impl.LogImpl#getSource <em>Source</em>}</li>
 *   <li>{@link trace.impl.LogImpl#getTimestamp <em>Timestamp</em>}</li>
 *   <li>{@link trace.impl.LogImpl#getLevel <em>Level</em>}</li>
 *   <li>{@link trace.impl.LogImpl#getExceptions <em>Exceptions</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LogImpl extends MinimalEObjectImpl.Container implements Log {
  /**
   * The default value of the '{@link #getMessage() <em>Message</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMessage()
   * @generated
   * @ordered
   */
  protected static final String MESSAGE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getMessage() <em>Message</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMessage()
   * @generated
   * @ordered
   */
  protected String message = MESSAGE_EDEFAULT;

  /**
   * The default value of the '{@link #getSource() <em>Source</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSource()
   * @generated
   * @ordered
   */
  protected static final String SOURCE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getSource() <em>Source</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSource()
   * @generated
   * @ordered
   */
  protected String source = SOURCE_EDEFAULT;

  /**
   * The default value of the '{@link #getTimestamp() <em>Timestamp</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTimestamp()
   * @generated
   * @ordered
   */
  protected static final Date TIMESTAMP_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getTimestamp() <em>Timestamp</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTimestamp()
   * @generated
   * @ordered
   */
  protected Date timestamp = TIMESTAMP_EDEFAULT;

  /**
   * The default value of the '{@link #getLevel() <em>Level</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLevel()
   * @generated
   * @ordered
   */
  protected static final LogLevel LEVEL_EDEFAULT = LogLevel.INFO;

  /**
   * The cached value of the '{@link #getLevel() <em>Level</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLevel()
   * @generated
   * @ordered
   */
  protected LogLevel level = LEVEL_EDEFAULT;

  /**
   * The cached value of the '{@link #getExceptions() <em>Exceptions</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExceptions()
   * @generated
   * @ordered
   */
  protected EList<trace.Exception> exceptions;

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
    return TracePackage.Literals.LOG;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getMessage() {
    return message;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMessage(String newMessage) {
    String oldMessage = message;
    message = newMessage;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, TracePackage.LOG__MESSAGE, oldMessage, message));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getSource() {
    return source;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSource(String newSource) {
    String oldSource = source;
    source = newSource;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, TracePackage.LOG__SOURCE, oldSource, source));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Date getTimestamp() {
    return timestamp;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTimestamp(Date newTimestamp) {
    Date oldTimestamp = timestamp;
    timestamp = newTimestamp;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, TracePackage.LOG__TIMESTAMP, oldTimestamp, timestamp));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public LogLevel getLevel() {
    return level;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLevel(LogLevel newLevel) {
    LogLevel oldLevel = level;
    level = newLevel == null ? LEVEL_EDEFAULT : newLevel;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, TracePackage.LOG__LEVEL, oldLevel, level));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<trace.Exception> getExceptions() {
    if (exceptions == null) {
      exceptions = new EObjectContainmentEList<trace.Exception>(trace.Exception.class, this, TracePackage.LOG__EXCEPTIONS);
    }
    return exceptions;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case TracePackage.LOG__EXCEPTIONS:
        return ((InternalEList<?>)getExceptions()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case TracePackage.LOG__MESSAGE:
        return getMessage();
      case TracePackage.LOG__SOURCE:
        return getSource();
      case TracePackage.LOG__TIMESTAMP:
        return getTimestamp();
      case TracePackage.LOG__LEVEL:
        return getLevel();
      case TracePackage.LOG__EXCEPTIONS:
        return getExceptions();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue) {
    switch (featureID) {
      case TracePackage.LOG__MESSAGE:
        setMessage((String)newValue);
        return;
      case TracePackage.LOG__SOURCE:
        setSource((String)newValue);
        return;
      case TracePackage.LOG__TIMESTAMP:
        setTimestamp((Date)newValue);
        return;
      case TracePackage.LOG__LEVEL:
        setLevel((LogLevel)newValue);
        return;
      case TracePackage.LOG__EXCEPTIONS:
        getExceptions().clear();
        getExceptions().addAll((Collection<? extends trace.Exception>)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID) {
    switch (featureID) {
      case TracePackage.LOG__MESSAGE:
        setMessage(MESSAGE_EDEFAULT);
        return;
      case TracePackage.LOG__SOURCE:
        setSource(SOURCE_EDEFAULT);
        return;
      case TracePackage.LOG__TIMESTAMP:
        setTimestamp(TIMESTAMP_EDEFAULT);
        return;
      case TracePackage.LOG__LEVEL:
        setLevel(LEVEL_EDEFAULT);
        return;
      case TracePackage.LOG__EXCEPTIONS:
        getExceptions().clear();
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID) {
    switch (featureID) {
      case TracePackage.LOG__MESSAGE:
        return MESSAGE_EDEFAULT == null ? message != null : !MESSAGE_EDEFAULT.equals(message);
      case TracePackage.LOG__SOURCE:
        return SOURCE_EDEFAULT == null ? source != null : !SOURCE_EDEFAULT.equals(source);
      case TracePackage.LOG__TIMESTAMP:
        return TIMESTAMP_EDEFAULT == null ? timestamp != null : !TIMESTAMP_EDEFAULT.equals(timestamp);
      case TracePackage.LOG__LEVEL:
        return level != LEVEL_EDEFAULT;
      case TracePackage.LOG__EXCEPTIONS:
        return exceptions != null && !exceptions.isEmpty();
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString() {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (message: ");
    result.append(message);
    result.append(", source: ");
    result.append(source);
    result.append(", timestamp: ");
    result.append(timestamp);
    result.append(", level: ");
    result.append(level);
    result.append(')');
    return result.toString();
  }

} //LogImpl
