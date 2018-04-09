/**
 */
package virtuallinksneoemf.impl;

import org.atlanmod.emfviews.virtuallinks.Concept;
import org.atlanmod.emfviews.virtuallinks.VirtualProperty;
import org.eclipse.emf.ecore.EClass;

import virtuallinksneoemf.VirtuallinksneoemfPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Virtual Property</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link virtuallinksneoemf.impl.VirtualPropertyImpl#getParent <em>Parent</em>}</li>
 *   <li>{@link virtuallinksneoemf.impl.VirtualPropertyImpl#getType <em>Type</em>}</li>
 *   <li>{@link virtuallinksneoemf.impl.VirtualPropertyImpl#isOptional <em>Optional</em>}</li>
 * </ul>
 *
 * @generated
 */
public class VirtualPropertyImpl extends VirtualLinkImpl implements VirtualProperty {
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected VirtualPropertyImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return VirtuallinksneoemfPackage.Literals.VIRTUAL_PROPERTY;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Concept getParent() {
    return (Concept)eGet(VirtuallinksneoemfPackage.Literals.VIRTUAL_PROPERTY__PARENT, true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setParent(Concept newParent) {
    eSet(VirtuallinksneoemfPackage.Literals.VIRTUAL_PROPERTY__PARENT, newParent);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getType() {
    return (String)eGet(VirtuallinksneoemfPackage.Literals.VIRTUAL_PROPERTY__TYPE, true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setType(String newType) {
    eSet(VirtuallinksneoemfPackage.Literals.VIRTUAL_PROPERTY__TYPE, newType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean isOptional() {
    return (Boolean)eGet(VirtuallinksneoemfPackage.Literals.VIRTUAL_PROPERTY__OPTIONAL, true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setOptional(boolean newOptional) {
    eSet(VirtuallinksneoemfPackage.Literals.VIRTUAL_PROPERTY__OPTIONAL, newOptional);
  }

} //VirtualPropertyImpl
