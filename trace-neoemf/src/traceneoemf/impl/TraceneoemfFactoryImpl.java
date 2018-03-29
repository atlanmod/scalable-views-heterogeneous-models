/**
 */
package traceneoemf.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import traceneoemf.Log;
import traceneoemf.LogLevel;
import traceneoemf.Trace;
import traceneoemf.TraceneoemfFactory;
import traceneoemf.TraceneoemfPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TraceneoemfFactoryImpl extends EFactoryImpl implements TraceneoemfFactory {
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static TraceneoemfFactory init() {
    try {
      TraceneoemfFactory theTraceneoemfFactory = (TraceneoemfFactory)EPackage.Registry.INSTANCE.getEFactory(TraceneoemfPackage.eNS_URI);
      if (theTraceneoemfFactory != null) {
        return theTraceneoemfFactory;
      }
    }
    catch (Exception exception) {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new TraceneoemfFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TraceneoemfFactoryImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass) {
    switch (eClass.getClassifierID()) {
      case TraceneoemfPackage.TRACE: return (EObject)createTrace();
      case TraceneoemfPackage.LOG: return (EObject)createLog();
      case TraceneoemfPackage.EXCEPTION: return (EObject)createException();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object createFromString(EDataType eDataType, String initialValue) {
    switch (eDataType.getClassifierID()) {
      case TraceneoemfPackage.LOG_LEVEL:
        return createLogLevelFromString(eDataType, initialValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String convertToString(EDataType eDataType, Object instanceValue) {
    switch (eDataType.getClassifierID()) {
      case TraceneoemfPackage.LOG_LEVEL:
        return convertLogLevelToString(eDataType, instanceValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Trace createTrace() {
    TraceImpl trace = new TraceImpl();
    return trace;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Log createLog() {
    LogImpl log = new LogImpl();
    return log;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public traceneoemf.Exception createException() {
    ExceptionImpl exception = new ExceptionImpl();
    return exception;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public LogLevel createLogLevelFromString(EDataType eDataType, String initialValue) {
    LogLevel result = LogLevel.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertLogLevelToString(EDataType eDataType, Object instanceValue) {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TraceneoemfPackage getTraceneoemfPackage() {
    return (TraceneoemfPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static TraceneoemfPackage getPackage() {
    return TraceneoemfPackage.eINSTANCE;
  }

} //TraceneoemfFactoryImpl
