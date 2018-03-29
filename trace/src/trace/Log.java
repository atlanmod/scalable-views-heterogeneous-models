/**
 */
package trace;

import java.util.Date;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Log</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link trace.Log#getMessage <em>Message</em>}</li>
 *   <li>{@link trace.Log#getSource <em>Source</em>}</li>
 *   <li>{@link trace.Log#getTimestamp <em>Timestamp</em>}</li>
 *   <li>{@link trace.Log#getLevel <em>Level</em>}</li>
 *   <li>{@link trace.Log#getExceptions <em>Exceptions</em>}</li>
 * </ul>
 *
 * @see trace.TracePackage#getLog()
 * @model
 * @generated
 */
public interface Log extends EObject {
  /**
   * Returns the value of the '<em><b>Message</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Message</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Message</em>' attribute.
   * @see #setMessage(String)
   * @see trace.TracePackage#getLog_Message()
   * @model
   * @generated
   */
  String getMessage();

  /**
   * Sets the value of the '{@link trace.Log#getMessage <em>Message</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Message</em>' attribute.
   * @see #getMessage()
   * @generated
   */
  void setMessage(String value);

  /**
   * Returns the value of the '<em><b>Source</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Source</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Source</em>' attribute.
   * @see #setSource(String)
   * @see trace.TracePackage#getLog_Source()
   * @model
   * @generated
   */
  String getSource();

  /**
   * Sets the value of the '{@link trace.Log#getSource <em>Source</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Source</em>' attribute.
   * @see #getSource()
   * @generated
   */
  void setSource(String value);

  /**
   * Returns the value of the '<em><b>Timestamp</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Timestamp</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Timestamp</em>' attribute.
   * @see #setTimestamp(Date)
   * @see trace.TracePackage#getLog_Timestamp()
   * @model
   * @generated
   */
  Date getTimestamp();

  /**
   * Sets the value of the '{@link trace.Log#getTimestamp <em>Timestamp</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Timestamp</em>' attribute.
   * @see #getTimestamp()
   * @generated
   */
  void setTimestamp(Date value);

  /**
   * Returns the value of the '<em><b>Level</b></em>' attribute.
   * The default value is <code>"INFO"</code>.
   * The literals are from the enumeration {@link trace.LogLevel}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Level</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Level</em>' attribute.
   * @see trace.LogLevel
   * @see #setLevel(LogLevel)
   * @see trace.TracePackage#getLog_Level()
   * @model default="INFO"
   * @generated
   */
  LogLevel getLevel();

  /**
   * Sets the value of the '{@link trace.Log#getLevel <em>Level</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Level</em>' attribute.
   * @see trace.LogLevel
   * @see #getLevel()
   * @generated
   */
  void setLevel(LogLevel value);

  /**
   * Returns the value of the '<em><b>Exceptions</b></em>' containment reference list.
   * The list contents are of type {@link trace.Exception}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Exceptions</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Exceptions</em>' containment reference list.
   * @see trace.TracePackage#getLog_Exceptions()
   * @model containment="true"
   * @generated
   */
  EList<trace.Exception> getExceptions();

} // Log
