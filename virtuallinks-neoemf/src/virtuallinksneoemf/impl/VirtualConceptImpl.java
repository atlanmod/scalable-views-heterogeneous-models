/**
 */
package virtuallinksneoemf.impl;

import org.atlanmod.emfviews.virtuallinks.Concept;
import org.atlanmod.emfviews.virtuallinks.VirtualConcept;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import virtuallinksneoemf.VirtuallinksneoemfPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Virtual Concept</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link virtuallinksneoemf.impl.VirtualConceptImpl#getSuperConcepts <em>Super Concepts</em>}</li>
 *   <li>{@link virtuallinksneoemf.impl.VirtualConceptImpl#getSubConcepts <em>Sub Concepts</em>}</li>
 * </ul>
 *
 * @generated
 */
public class VirtualConceptImpl extends VirtualLinkImpl implements VirtualConcept {
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected VirtualConceptImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return VirtuallinksneoemfPackage.Literals.VIRTUAL_CONCEPT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  @SuppressWarnings("unchecked")
  public EList<Concept> getSuperConcepts() {
    return (EList<Concept>)eGet(VirtuallinksneoemfPackage.Literals.VIRTUAL_CONCEPT__SUPER_CONCEPTS, true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  @SuppressWarnings("unchecked")
  public EList<Concept> getSubConcepts() {
    return (EList<Concept>)eGet(VirtuallinksneoemfPackage.Literals.VIRTUAL_CONCEPT__SUB_CONCEPTS, true);
  }

} //VirtualConceptImpl
