/**
 */
package traceneoemf;

import trace.CommonTraceFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see traceneoemf.TraceneoemfPackage
 * @generated
 */
public interface TraceneoemfFactory extends CommonTraceFactory {
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  TraceneoemfFactory eINSTANCE = traceneoemf.impl.TraceneoemfFactoryImpl.init();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  TraceneoemfPackage getTraceneoemfPackage();

} //TraceneoemfFactory
