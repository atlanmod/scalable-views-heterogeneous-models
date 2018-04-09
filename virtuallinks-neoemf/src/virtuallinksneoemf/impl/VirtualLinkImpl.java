/**
 */
package virtuallinksneoemf.impl;

import fr.inria.atlanmod.neoemf.core.DefaultPersistentEObject;

import org.atlanmod.emfviews.virtuallinks.VirtualLink;
import org.eclipse.emf.ecore.EClass;

import virtuallinksneoemf.VirtuallinksneoemfPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Virtual Link</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link virtuallinksneoemf.impl.VirtualLinkImpl#getName <em>Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class VirtualLinkImpl extends DefaultPersistentEObject implements VirtualLink {
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected VirtualLinkImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return VirtuallinksneoemfPackage.Literals.VIRTUAL_LINK;
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
  public String getName() {
    return (String)eGet(VirtuallinksneoemfPackage.Literals.VIRTUAL_LINK__NAME, true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setName(String newName) {
    eSet(VirtuallinksneoemfPackage.Literals.VIRTUAL_LINK__NAME, newName);
  }

} //VirtualLinkImpl
