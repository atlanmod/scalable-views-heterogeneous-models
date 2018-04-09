/**
 */
package virtuallinksneoemf.impl;

import org.atlanmod.emfviews.virtuallinks.ConcreteElement;
import org.atlanmod.emfviews.virtuallinks.Filter;
import org.eclipse.emf.ecore.EClass;

import virtuallinksneoemf.VirtuallinksneoemfPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Filter</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link virtuallinksneoemf.impl.FilterImpl#getTarget <em>Target</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FilterImpl extends VirtualLinkImpl implements Filter {
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected FilterImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return VirtuallinksneoemfPackage.Literals.FILTER;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public ConcreteElement getTarget() {
    return (ConcreteElement)eGet(VirtuallinksneoemfPackage.Literals.FILTER__TARGET, true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setTarget(ConcreteElement newTarget) {
    eSet(VirtuallinksneoemfPackage.Literals.FILTER__TARGET, newTarget);
  }

} //FilterImpl
