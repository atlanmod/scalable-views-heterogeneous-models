/**
 */
package virtuallinksneoemf.impl;

import org.atlanmod.emfviews.virtuallinks.Association;
import org.atlanmod.emfviews.virtuallinks.Concept;
import org.atlanmod.emfviews.virtuallinks.VirtualAssociation;
import org.eclipse.emf.ecore.EClass;

import virtuallinksneoemf.VirtuallinksneoemfPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Virtual Association</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link virtuallinksneoemf.impl.VirtualAssociationImpl#getSource <em>Source</em>}</li>
 *   <li>{@link virtuallinksneoemf.impl.VirtualAssociationImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link virtuallinksneoemf.impl.VirtualAssociationImpl#getLowerBound <em>Lower Bound</em>}</li>
 *   <li>{@link virtuallinksneoemf.impl.VirtualAssociationImpl#getUpperBound <em>Upper Bound</em>}</li>
 *   <li>{@link virtuallinksneoemf.impl.VirtualAssociationImpl#getOpposite <em>Opposite</em>}</li>
 *   <li>{@link virtuallinksneoemf.impl.VirtualAssociationImpl#isComposition <em>Composition</em>}</li>
 * </ul>
 *
 * @generated
 */
public class VirtualAssociationImpl extends VirtualLinkImpl implements VirtualAssociation {
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected VirtualAssociationImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return VirtuallinksneoemfPackage.Literals.VIRTUAL_ASSOCIATION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Concept getSource() {
    return (Concept)eGet(VirtuallinksneoemfPackage.Literals.VIRTUAL_ASSOCIATION__SOURCE, true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setSource(Concept newSource) {
    eSet(VirtuallinksneoemfPackage.Literals.VIRTUAL_ASSOCIATION__SOURCE, newSource);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Concept getTarget() {
    return (Concept)eGet(VirtuallinksneoemfPackage.Literals.VIRTUAL_ASSOCIATION__TARGET, true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setTarget(Concept newTarget) {
    eSet(VirtuallinksneoemfPackage.Literals.VIRTUAL_ASSOCIATION__TARGET, newTarget);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public int getLowerBound() {
    return (Integer)eGet(VirtuallinksneoemfPackage.Literals.VIRTUAL_ASSOCIATION__LOWER_BOUND, true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setLowerBound(int newLowerBound) {
    eSet(VirtuallinksneoemfPackage.Literals.VIRTUAL_ASSOCIATION__LOWER_BOUND, newLowerBound);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public int getUpperBound() {
    return (Integer)eGet(VirtuallinksneoemfPackage.Literals.VIRTUAL_ASSOCIATION__UPPER_BOUND, true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setUpperBound(int newUpperBound) {
    eSet(VirtuallinksneoemfPackage.Literals.VIRTUAL_ASSOCIATION__UPPER_BOUND, newUpperBound);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Association getOpposite() {
    return (Association)eGet(VirtuallinksneoemfPackage.Literals.VIRTUAL_ASSOCIATION__OPPOSITE, true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setOpposite(Association newOpposite) {
    eSet(VirtuallinksneoemfPackage.Literals.VIRTUAL_ASSOCIATION__OPPOSITE, newOpposite);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean isComposition() {
    return (Boolean)eGet(VirtuallinksneoemfPackage.Literals.VIRTUAL_ASSOCIATION__COMPOSITION, true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setComposition(boolean newComposition) {
    eSet(VirtuallinksneoemfPackage.Literals.VIRTUAL_ASSOCIATION__COMPOSITION, newComposition);
  }

} //VirtualAssociationImpl
