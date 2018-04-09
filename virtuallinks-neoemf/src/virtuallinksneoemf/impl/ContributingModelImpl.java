/**
 */
package virtuallinksneoemf.impl;

import fr.inria.atlanmod.neoemf.core.DefaultPersistentEObject;

import org.atlanmod.emfviews.virtuallinks.ConcreteElement;
import org.atlanmod.emfviews.virtuallinks.ContributingModel;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import virtuallinksneoemf.VirtuallinksneoemfPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Contributing Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link virtuallinksneoemf.impl.ContributingModelImpl#getURI <em>URI</em>}</li>
 *   <li>{@link virtuallinksneoemf.impl.ContributingModelImpl#getConcreteElements <em>Concrete Elements</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ContributingModelImpl extends DefaultPersistentEObject implements ContributingModel {
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ContributingModelImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return VirtuallinksneoemfPackage.Literals.CONTRIBUTING_MODEL;
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
  public String getURI() {
    return (String)eGet(VirtuallinksneoemfPackage.Literals.CONTRIBUTING_MODEL__URI, true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setURI(String newURI) {
    eSet(VirtuallinksneoemfPackage.Literals.CONTRIBUTING_MODEL__URI, newURI);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  @SuppressWarnings("unchecked")
  public EList<ConcreteElement> getConcreteElements() {
    return (EList<ConcreteElement>)eGet(VirtuallinksneoemfPackage.Literals.CONTRIBUTING_MODEL__CONCRETE_ELEMENTS, true);
  }

} //ContributingModelImpl
