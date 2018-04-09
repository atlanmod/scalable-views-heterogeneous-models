/**
 */
package virtuallinksneoemf.impl;

import org.atlanmod.emfviews.virtuallinks.ConcreteElement;
import org.atlanmod.emfviews.virtuallinks.ContributingModel;
import org.eclipse.emf.ecore.EClass;

import virtuallinksneoemf.VirtuallinksneoemfPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Concrete Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link virtuallinksneoemf.impl.ConcreteElementImpl#getModel <em>Model</em>}</li>
 *   <li>{@link virtuallinksneoemf.impl.ConcreteElementImpl#getPath <em>Path</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ConcreteElementImpl extends ElementImpl implements ConcreteElement {
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ConcreteElementImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return VirtuallinksneoemfPackage.Literals.CONCRETE_ELEMENT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public ContributingModel getModel() {
    return (ContributingModel)eGet(VirtuallinksneoemfPackage.Literals.CONCRETE_ELEMENT__MODEL, true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setModel(ContributingModel newModel) {
    eSet(VirtuallinksneoemfPackage.Literals.CONCRETE_ELEMENT__MODEL, newModel);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getPath() {
    return (String)eGet(VirtuallinksneoemfPackage.Literals.CONCRETE_ELEMENT__PATH, true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setPath(String newPath) {
    eSet(VirtuallinksneoemfPackage.Literals.CONCRETE_ELEMENT__PATH, newPath);
  }

} //ConcreteElementImpl
