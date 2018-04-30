/**
 */
package virtuallinksneoemf.impl;

import fr.inria.atlanmod.neoemf.core.DefaultPersistentEObject;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.atlanmod.emfviews.virtuallinks.ContributingModel;
import org.atlanmod.emfviews.virtuallinks.Filter;
import org.atlanmod.emfviews.virtuallinks.VirtualAssociation;
import org.atlanmod.emfviews.virtuallinks.VirtualConcept;
import org.atlanmod.emfviews.virtuallinks.VirtualElement;
import org.atlanmod.emfviews.virtuallinks.VirtualLink;
import org.atlanmod.emfviews.virtuallinks.VirtualProperty;
import org.atlanmod.emfviews.virtuallinks.WeavingModel;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import virtuallinksneoemf.VirtuallinksneoemfPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Weaving Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link virtuallinksneoemf.impl.WeavingModelImpl#getName <em>Name</em>}</li>
 *   <li>{@link virtuallinksneoemf.impl.WeavingModelImpl#getVirtualLinks <em>Virtual Links</em>}</li>
 *   <li>{@link virtuallinksneoemf.impl.WeavingModelImpl#getContributingModels <em>Contributing Models</em>}</li>
 *   <li>{@link virtuallinksneoemf.impl.WeavingModelImpl#isWhitelist <em>Whitelist</em>}</li>
 *   <li>{@link virtuallinksneoemf.impl.WeavingModelImpl#getVirtualConcepts <em>Virtual Concepts</em>}</li>
 *   <li>{@link virtuallinksneoemf.impl.WeavingModelImpl#getVirtualProperties <em>Virtual Properties</em>}</li>
 *   <li>{@link virtuallinksneoemf.impl.WeavingModelImpl#getVirtualAssociations <em>Virtual Associations</em>}</li>
 *   <li>{@link virtuallinksneoemf.impl.WeavingModelImpl#getVirtualElements <em>Virtual Elements</em>}</li>
 *   <li>{@link virtuallinksneoemf.impl.WeavingModelImpl#getFilters <em>Filters</em>}</li>
 * </ul>
 *
 * @generated
 */
public class WeavingModelImpl extends DefaultPersistentEObject implements WeavingModel {
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected WeavingModelImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return VirtuallinksneoemfPackage.Literals.WEAVING_MODEL;
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
    return (String)eGet(VirtuallinksneoemfPackage.Literals.WEAVING_MODEL__NAME, true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setName(String newName) {
    eSet(VirtuallinksneoemfPackage.Literals.WEAVING_MODEL__NAME, newName);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  @SuppressWarnings("unchecked")
  public EList<VirtualLink> getVirtualLinks() {
    return (EList<VirtualLink>)eGet(VirtuallinksneoemfPackage.Literals.WEAVING_MODEL__VIRTUAL_LINKS, true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  @SuppressWarnings("unchecked")
  public EList<ContributingModel> getContributingModels() {
    return (EList<ContributingModel>)eGet(VirtuallinksneoemfPackage.Literals.WEAVING_MODEL__CONTRIBUTING_MODELS, true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean isWhitelist() {
    return (Boolean)eGet(VirtuallinksneoemfPackage.Literals.WEAVING_MODEL__WHITELIST, true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setWhitelist(boolean newWhitelist) {
    eSet(VirtuallinksneoemfPackage.Literals.WEAVING_MODEL__WHITELIST, newWhitelist);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  @SuppressWarnings("unchecked")
  public EList<VirtualConcept> getVirtualConcepts() {
    return (EList<VirtualConcept>)eGet(VirtuallinksneoemfPackage.Literals.WEAVING_MODEL__VIRTUAL_CONCEPTS, true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  @SuppressWarnings("unchecked")
  public EList<VirtualProperty> getVirtualProperties() {
    return (EList<VirtualProperty>)eGet(VirtuallinksneoemfPackage.Literals.WEAVING_MODEL__VIRTUAL_PROPERTIES, true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  @Override
  @SuppressWarnings("unchecked")
  public EList<VirtualAssociation> getVirtualAssociations() {
    return (EList<VirtualAssociation>)(EList<?>) ((PersistentResource) eResource())
        .getAllInstances(VirtuallinksneoemfPackage.Literals.VIRTUAL_ASSOCIATION);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  @SuppressWarnings("unchecked")
  public EList<VirtualElement> getVirtualElements() {
    return (EList<VirtualElement>)eGet(VirtuallinksneoemfPackage.Literals.WEAVING_MODEL__VIRTUAL_ELEMENTS, true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  @SuppressWarnings("unchecked")
  public EList<Filter> getFilters() {
    return (EList<Filter>)eGet(VirtuallinksneoemfPackage.Literals.WEAVING_MODEL__FILTERS, true);
  }

} //WeavingModelImpl
