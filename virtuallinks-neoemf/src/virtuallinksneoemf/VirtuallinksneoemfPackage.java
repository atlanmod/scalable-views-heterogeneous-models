/**
 */
package virtuallinksneoemf;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see virtuallinksneoemf.VirtuallinksneoemfFactory
 * @model kind="package"
 * @generated
 */
public interface VirtuallinksneoemfPackage extends EPackage {
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "virtuallinksneoemf";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.atlanmod.org/emfviews/virtuallinks-neoemf/0.3.0";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "virtualLinks-neoemf";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  VirtuallinksneoemfPackage eINSTANCE = virtuallinksneoemf.impl.VirtuallinksneoemfPackageImpl.init();

  /**
   * The meta object id for the '{@link virtuallinksneoemf.impl.WeavingModelImpl <em>Weaving Model</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see virtuallinksneoemf.impl.WeavingModelImpl
   * @see virtuallinksneoemf.impl.VirtuallinksneoemfPackageImpl#getWeavingModel()
   * @generated
   */
  int WEAVING_MODEL = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WEAVING_MODEL__NAME = 0;

  /**
   * The feature id for the '<em><b>Virtual Links</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WEAVING_MODEL__VIRTUAL_LINKS = 1;

  /**
   * The feature id for the '<em><b>Contributing Models</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WEAVING_MODEL__CONTRIBUTING_MODELS = 2;

  /**
   * The feature id for the '<em><b>Whitelist</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WEAVING_MODEL__WHITELIST = 3;

  /**
   * The feature id for the '<em><b>Virtual Concepts</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WEAVING_MODEL__VIRTUAL_CONCEPTS = 4;

  /**
   * The feature id for the '<em><b>Virtual Properties</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WEAVING_MODEL__VIRTUAL_PROPERTIES = 5;

  /**
   * The feature id for the '<em><b>Virtual Associations</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WEAVING_MODEL__VIRTUAL_ASSOCIATIONS = 6;

  /**
   * The feature id for the '<em><b>Virtual Elements</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WEAVING_MODEL__VIRTUAL_ELEMENTS = 7;

  /**
   * The feature id for the '<em><b>Filters</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WEAVING_MODEL__FILTERS = 8;

  /**
   * The number of structural features of the '<em>Weaving Model</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WEAVING_MODEL_FEATURE_COUNT = 9;

  /**
   * The number of operations of the '<em>Weaving Model</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WEAVING_MODEL_OPERATION_COUNT = 0;

  /**
   * The meta object id for the '{@link virtuallinksneoemf.impl.VirtualLinkImpl <em>Virtual Link</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see virtuallinksneoemf.impl.VirtualLinkImpl
   * @see virtuallinksneoemf.impl.VirtuallinksneoemfPackageImpl#getVirtualLink()
   * @generated
   */
  int VIRTUAL_LINK = 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VIRTUAL_LINK__NAME = 0;

  /**
   * The number of structural features of the '<em>Virtual Link</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VIRTUAL_LINK_FEATURE_COUNT = 1;

  /**
   * The number of operations of the '<em>Virtual Link</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VIRTUAL_LINK_OPERATION_COUNT = 0;

  /**
   * The meta object id for the '{@link virtuallinksneoemf.impl.VirtualConceptImpl <em>Virtual Concept</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see virtuallinksneoemf.impl.VirtualConceptImpl
   * @see virtuallinksneoemf.impl.VirtuallinksneoemfPackageImpl#getVirtualConcept()
   * @generated
   */
  int VIRTUAL_CONCEPT = 2;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VIRTUAL_CONCEPT__NAME = VIRTUAL_LINK__NAME;

  /**
   * The feature id for the '<em><b>Super Concepts</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VIRTUAL_CONCEPT__SUPER_CONCEPTS = VIRTUAL_LINK_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Sub Concepts</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VIRTUAL_CONCEPT__SUB_CONCEPTS = VIRTUAL_LINK_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Virtual Concept</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VIRTUAL_CONCEPT_FEATURE_COUNT = VIRTUAL_LINK_FEATURE_COUNT + 2;

  /**
   * The number of operations of the '<em>Virtual Concept</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VIRTUAL_CONCEPT_OPERATION_COUNT = VIRTUAL_LINK_OPERATION_COUNT + 0;

  /**
   * The meta object id for the '{@link virtuallinksneoemf.impl.VirtualPropertyImpl <em>Virtual Property</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see virtuallinksneoemf.impl.VirtualPropertyImpl
   * @see virtuallinksneoemf.impl.VirtuallinksneoemfPackageImpl#getVirtualProperty()
   * @generated
   */
  int VIRTUAL_PROPERTY = 3;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VIRTUAL_PROPERTY__NAME = VIRTUAL_LINK__NAME;

  /**
   * The feature id for the '<em><b>Parent</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VIRTUAL_PROPERTY__PARENT = VIRTUAL_LINK_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VIRTUAL_PROPERTY__TYPE = VIRTUAL_LINK_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Optional</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VIRTUAL_PROPERTY__OPTIONAL = VIRTUAL_LINK_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Virtual Property</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VIRTUAL_PROPERTY_FEATURE_COUNT = VIRTUAL_LINK_FEATURE_COUNT + 3;

  /**
   * The number of operations of the '<em>Virtual Property</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VIRTUAL_PROPERTY_OPERATION_COUNT = VIRTUAL_LINK_OPERATION_COUNT + 0;

  /**
   * The meta object id for the '{@link virtuallinksneoemf.impl.VirtualAssociationImpl <em>Virtual Association</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see virtuallinksneoemf.impl.VirtualAssociationImpl
   * @see virtuallinksneoemf.impl.VirtuallinksneoemfPackageImpl#getVirtualAssociation()
   * @generated
   */
  int VIRTUAL_ASSOCIATION = 4;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VIRTUAL_ASSOCIATION__NAME = VIRTUAL_LINK__NAME;

  /**
   * The feature id for the '<em><b>Source</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VIRTUAL_ASSOCIATION__SOURCE = VIRTUAL_LINK_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Target</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VIRTUAL_ASSOCIATION__TARGET = VIRTUAL_LINK_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Lower Bound</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VIRTUAL_ASSOCIATION__LOWER_BOUND = VIRTUAL_LINK_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Upper Bound</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VIRTUAL_ASSOCIATION__UPPER_BOUND = VIRTUAL_LINK_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Opposite</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VIRTUAL_ASSOCIATION__OPPOSITE = VIRTUAL_LINK_FEATURE_COUNT + 4;

  /**
   * The feature id for the '<em><b>Composition</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VIRTUAL_ASSOCIATION__COMPOSITION = VIRTUAL_LINK_FEATURE_COUNT + 5;

  /**
   * The number of structural features of the '<em>Virtual Association</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VIRTUAL_ASSOCIATION_FEATURE_COUNT = VIRTUAL_LINK_FEATURE_COUNT + 6;

  /**
   * The number of operations of the '<em>Virtual Association</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VIRTUAL_ASSOCIATION_OPERATION_COUNT = VIRTUAL_LINK_OPERATION_COUNT + 0;

  /**
   * The meta object id for the '{@link virtuallinksneoemf.impl.FilterImpl <em>Filter</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see virtuallinksneoemf.impl.FilterImpl
   * @see virtuallinksneoemf.impl.VirtuallinksneoemfPackageImpl#getFilter()
   * @generated
   */
  int FILTER = 5;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FILTER__NAME = VIRTUAL_LINK__NAME;

  /**
   * The feature id for the '<em><b>Target</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FILTER__TARGET = VIRTUAL_LINK_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Filter</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FILTER_FEATURE_COUNT = VIRTUAL_LINK_FEATURE_COUNT + 1;

  /**
   * The number of operations of the '<em>Filter</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FILTER_OPERATION_COUNT = VIRTUAL_LINK_OPERATION_COUNT + 0;

  /**
   * The meta object id for the '{@link virtuallinksneoemf.impl.ContributingModelImpl <em>Contributing Model</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see virtuallinksneoemf.impl.ContributingModelImpl
   * @see virtuallinksneoemf.impl.VirtuallinksneoemfPackageImpl#getContributingModel()
   * @generated
   */
  int CONTRIBUTING_MODEL = 6;

  /**
   * The feature id for the '<em><b>URI</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTRIBUTING_MODEL__URI = 0;

  /**
   * The feature id for the '<em><b>Concrete Elements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTRIBUTING_MODEL__CONCRETE_ELEMENTS = 1;

  /**
   * The number of structural features of the '<em>Contributing Model</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTRIBUTING_MODEL_FEATURE_COUNT = 2;

  /**
   * The number of operations of the '<em>Contributing Model</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTRIBUTING_MODEL_OPERATION_COUNT = 0;

  /**
   * The meta object id for the '{@link virtuallinksneoemf.impl.ElementImpl <em>Element</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see virtuallinksneoemf.impl.ElementImpl
   * @see virtuallinksneoemf.impl.VirtuallinksneoemfPackageImpl#getElement()
   * @generated
   */
  int ELEMENT = 12;

  /**
   * The number of structural features of the '<em>Element</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT_FEATURE_COUNT = 0;

  /**
   * The number of operations of the '<em>Element</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ELEMENT_OPERATION_COUNT = 0;

  /**
   * The meta object id for the '{@link virtuallinksneoemf.impl.ConcreteElementImpl <em>Concrete Element</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see virtuallinksneoemf.impl.ConcreteElementImpl
   * @see virtuallinksneoemf.impl.VirtuallinksneoemfPackageImpl#getConcreteElement()
   * @generated
   */
  int CONCRETE_ELEMENT = 7;

  /**
   * The feature id for the '<em><b>Model</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONCRETE_ELEMENT__MODEL = ELEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Path</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONCRETE_ELEMENT__PATH = ELEMENT_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Concrete Element</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONCRETE_ELEMENT_FEATURE_COUNT = ELEMENT_FEATURE_COUNT + 2;

  /**
   * The number of operations of the '<em>Concrete Element</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONCRETE_ELEMENT_OPERATION_COUNT = ELEMENT_OPERATION_COUNT + 0;

  /**
   * The meta object id for the '{@link virtuallinksneoemf.impl.ConceptImpl <em>Concept</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see virtuallinksneoemf.impl.ConceptImpl
   * @see virtuallinksneoemf.impl.VirtuallinksneoemfPackageImpl#getConcept()
   * @generated
   */
  int CONCEPT = 8;

  /**
   * The number of structural features of the '<em>Concept</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONCEPT_FEATURE_COUNT = ELEMENT_FEATURE_COUNT + 0;

  /**
   * The number of operations of the '<em>Concept</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONCEPT_OPERATION_COUNT = ELEMENT_OPERATION_COUNT + 0;

  /**
   * The meta object id for the '{@link virtuallinksneoemf.impl.AssociationImpl <em>Association</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see virtuallinksneoemf.impl.AssociationImpl
   * @see virtuallinksneoemf.impl.VirtuallinksneoemfPackageImpl#getAssociation()
   * @generated
   */
  int ASSOCIATION = 9;

  /**
   * The number of structural features of the '<em>Association</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSOCIATION_FEATURE_COUNT = ELEMENT_FEATURE_COUNT + 0;

  /**
   * The number of operations of the '<em>Association</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ASSOCIATION_OPERATION_COUNT = ELEMENT_OPERATION_COUNT + 0;

  /**
   * The meta object id for the '{@link virtuallinksneoemf.impl.ConcreteConceptImpl <em>Concrete Concept</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see virtuallinksneoemf.impl.ConcreteConceptImpl
   * @see virtuallinksneoemf.impl.VirtuallinksneoemfPackageImpl#getConcreteConcept()
   * @generated
   */
  int CONCRETE_CONCEPT = 10;

  /**
   * The feature id for the '<em><b>Model</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONCRETE_CONCEPT__MODEL = CONCRETE_ELEMENT__MODEL;

  /**
   * The feature id for the '<em><b>Path</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONCRETE_CONCEPT__PATH = CONCRETE_ELEMENT__PATH;

  /**
   * The number of structural features of the '<em>Concrete Concept</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONCRETE_CONCEPT_FEATURE_COUNT = CONCRETE_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The number of operations of the '<em>Concrete Concept</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONCRETE_CONCEPT_OPERATION_COUNT = CONCRETE_ELEMENT_OPERATION_COUNT + 0;

  /**
   * The meta object id for the '{@link virtuallinksneoemf.impl.ConcreteAssociationImpl <em>Concrete Association</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see virtuallinksneoemf.impl.ConcreteAssociationImpl
   * @see virtuallinksneoemf.impl.VirtuallinksneoemfPackageImpl#getConcreteAssociation()
   * @generated
   */
  int CONCRETE_ASSOCIATION = 11;

  /**
   * The feature id for the '<em><b>Model</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONCRETE_ASSOCIATION__MODEL = CONCRETE_ELEMENT__MODEL;

  /**
   * The feature id for the '<em><b>Path</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONCRETE_ASSOCIATION__PATH = CONCRETE_ELEMENT__PATH;

  /**
   * The number of structural features of the '<em>Concrete Association</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONCRETE_ASSOCIATION_FEATURE_COUNT = CONCRETE_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The number of operations of the '<em>Concrete Association</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONCRETE_ASSOCIATION_OPERATION_COUNT = CONCRETE_ELEMENT_OPERATION_COUNT + 0;

  /**
   * The meta object id for the '{@link virtuallinksneoemf.impl.VirtualElementImpl <em>Virtual Element</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see virtuallinksneoemf.impl.VirtualElementImpl
   * @see virtuallinksneoemf.impl.VirtuallinksneoemfPackageImpl#getVirtualElement()
   * @generated
   */
  int VIRTUAL_ELEMENT = 13;

  /**
   * The number of structural features of the '<em>Virtual Element</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VIRTUAL_ELEMENT_FEATURE_COUNT = ELEMENT_FEATURE_COUNT + 0;

  /**
   * The number of operations of the '<em>Virtual Element</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VIRTUAL_ELEMENT_OPERATION_COUNT = ELEMENT_OPERATION_COUNT + 0;


  /**
   * Returns the meta object for class '{@link virtuallinksneoemf.WeavingModel <em>Weaving Model</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Weaving Model</em>'.
   * @see virtuallinksneoemf.WeavingModel
   * @generated
   */
  EClass getWeavingModel();

  /**
   * Returns the meta object for the attribute '{@link virtuallinksneoemf.WeavingModel#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see virtuallinksneoemf.WeavingModel#getName()
   * @see #getWeavingModel()
   * @generated
   */
  EAttribute getWeavingModel_Name();

  /**
   * Returns the meta object for the containment reference list '{@link virtuallinksneoemf.WeavingModel#getVirtualLinks <em>Virtual Links</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Virtual Links</em>'.
   * @see virtuallinksneoemf.WeavingModel#getVirtualLinks()
   * @see #getWeavingModel()
   * @generated
   */
  EReference getWeavingModel_VirtualLinks();

  /**
   * Returns the meta object for the containment reference list '{@link virtuallinksneoemf.WeavingModel#getContributingModels <em>Contributing Models</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Contributing Models</em>'.
   * @see virtuallinksneoemf.WeavingModel#getContributingModels()
   * @see #getWeavingModel()
   * @generated
   */
  EReference getWeavingModel_ContributingModels();

  /**
   * Returns the meta object for the attribute '{@link virtuallinksneoemf.WeavingModel#isWhitelist <em>Whitelist</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Whitelist</em>'.
   * @see virtuallinksneoemf.WeavingModel#isWhitelist()
   * @see #getWeavingModel()
   * @generated
   */
  EAttribute getWeavingModel_Whitelist();

  /**
   * Returns the meta object for the reference list '{@link virtuallinksneoemf.WeavingModel#getVirtualConcepts <em>Virtual Concepts</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Virtual Concepts</em>'.
   * @see virtuallinksneoemf.WeavingModel#getVirtualConcepts()
   * @see #getWeavingModel()
   * @generated
   */
  EReference getWeavingModel_VirtualConcepts();

  /**
   * Returns the meta object for the reference list '{@link virtuallinksneoemf.WeavingModel#getVirtualProperties <em>Virtual Properties</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Virtual Properties</em>'.
   * @see virtuallinksneoemf.WeavingModel#getVirtualProperties()
   * @see #getWeavingModel()
   * @generated
   */
  EReference getWeavingModel_VirtualProperties();

  /**
   * Returns the meta object for the reference list '{@link virtuallinksneoemf.WeavingModel#getVirtualAssociations <em>Virtual Associations</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Virtual Associations</em>'.
   * @see virtuallinksneoemf.WeavingModel#getVirtualAssociations()
   * @see #getWeavingModel()
   * @generated
   */
  EReference getWeavingModel_VirtualAssociations();

  /**
   * Returns the meta object for the reference list '{@link virtuallinksneoemf.WeavingModel#getVirtualElements <em>Virtual Elements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Virtual Elements</em>'.
   * @see virtuallinksneoemf.WeavingModel#getVirtualElements()
   * @see #getWeavingModel()
   * @generated
   */
  EReference getWeavingModel_VirtualElements();

  /**
   * Returns the meta object for the reference list '{@link virtuallinksneoemf.WeavingModel#getFilters <em>Filters</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Filters</em>'.
   * @see virtuallinksneoemf.WeavingModel#getFilters()
   * @see #getWeavingModel()
   * @generated
   */
  EReference getWeavingModel_Filters();

  /**
   * Returns the meta object for class '{@link virtuallinksneoemf.VirtualLink <em>Virtual Link</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Virtual Link</em>'.
   * @see virtuallinksneoemf.VirtualLink
   * @generated
   */
  EClass getVirtualLink();

  /**
   * Returns the meta object for the attribute '{@link virtuallinksneoemf.VirtualLink#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see virtuallinksneoemf.VirtualLink#getName()
   * @see #getVirtualLink()
   * @generated
   */
  EAttribute getVirtualLink_Name();

  /**
   * Returns the meta object for class '{@link virtuallinksneoemf.VirtualConcept <em>Virtual Concept</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Virtual Concept</em>'.
   * @see virtuallinksneoemf.VirtualConcept
   * @generated
   */
  EClass getVirtualConcept();

  /**
   * Returns the meta object for the reference list '{@link virtuallinksneoemf.VirtualConcept#getSuperConcepts <em>Super Concepts</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Super Concepts</em>'.
   * @see virtuallinksneoemf.VirtualConcept#getSuperConcepts()
   * @see #getVirtualConcept()
   * @generated
   */
  EReference getVirtualConcept_SuperConcepts();

  /**
   * Returns the meta object for the reference list '{@link virtuallinksneoemf.VirtualConcept#getSubConcepts <em>Sub Concepts</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Sub Concepts</em>'.
   * @see virtuallinksneoemf.VirtualConcept#getSubConcepts()
   * @see #getVirtualConcept()
   * @generated
   */
  EReference getVirtualConcept_SubConcepts();

  /**
   * Returns the meta object for class '{@link virtuallinksneoemf.VirtualProperty <em>Virtual Property</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Virtual Property</em>'.
   * @see virtuallinksneoemf.VirtualProperty
   * @generated
   */
  EClass getVirtualProperty();

  /**
   * Returns the meta object for the reference '{@link virtuallinksneoemf.VirtualProperty#getParent <em>Parent</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Parent</em>'.
   * @see virtuallinksneoemf.VirtualProperty#getParent()
   * @see #getVirtualProperty()
   * @generated
   */
  EReference getVirtualProperty_Parent();

  /**
   * Returns the meta object for the attribute '{@link virtuallinksneoemf.VirtualProperty#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Type</em>'.
   * @see virtuallinksneoemf.VirtualProperty#getType()
   * @see #getVirtualProperty()
   * @generated
   */
  EAttribute getVirtualProperty_Type();

  /**
   * Returns the meta object for the attribute '{@link virtuallinksneoemf.VirtualProperty#isOptional <em>Optional</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Optional</em>'.
   * @see virtuallinksneoemf.VirtualProperty#isOptional()
   * @see #getVirtualProperty()
   * @generated
   */
  EAttribute getVirtualProperty_Optional();

  /**
   * Returns the meta object for class '{@link virtuallinksneoemf.VirtualAssociation <em>Virtual Association</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Virtual Association</em>'.
   * @see virtuallinksneoemf.VirtualAssociation
   * @generated
   */
  EClass getVirtualAssociation();

  /**
   * Returns the meta object for the reference '{@link virtuallinksneoemf.VirtualAssociation#getSource <em>Source</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Source</em>'.
   * @see virtuallinksneoemf.VirtualAssociation#getSource()
   * @see #getVirtualAssociation()
   * @generated
   */
  EReference getVirtualAssociation_Source();

  /**
   * Returns the meta object for the reference '{@link virtuallinksneoemf.VirtualAssociation#getTarget <em>Target</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Target</em>'.
   * @see virtuallinksneoemf.VirtualAssociation#getTarget()
   * @see #getVirtualAssociation()
   * @generated
   */
  EReference getVirtualAssociation_Target();

  /**
   * Returns the meta object for the attribute '{@link virtuallinksneoemf.VirtualAssociation#getLowerBound <em>Lower Bound</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Lower Bound</em>'.
   * @see virtuallinksneoemf.VirtualAssociation#getLowerBound()
   * @see #getVirtualAssociation()
   * @generated
   */
  EAttribute getVirtualAssociation_LowerBound();

  /**
   * Returns the meta object for the attribute '{@link virtuallinksneoemf.VirtualAssociation#getUpperBound <em>Upper Bound</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Upper Bound</em>'.
   * @see virtuallinksneoemf.VirtualAssociation#getUpperBound()
   * @see #getVirtualAssociation()
   * @generated
   */
  EAttribute getVirtualAssociation_UpperBound();

  /**
   * Returns the meta object for the reference '{@link virtuallinksneoemf.VirtualAssociation#getOpposite <em>Opposite</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Opposite</em>'.
   * @see virtuallinksneoemf.VirtualAssociation#getOpposite()
   * @see #getVirtualAssociation()
   * @generated
   */
  EReference getVirtualAssociation_Opposite();

  /**
   * Returns the meta object for the attribute '{@link virtuallinksneoemf.VirtualAssociation#isComposition <em>Composition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Composition</em>'.
   * @see virtuallinksneoemf.VirtualAssociation#isComposition()
   * @see #getVirtualAssociation()
   * @generated
   */
  EAttribute getVirtualAssociation_Composition();

  /**
   * Returns the meta object for class '{@link virtuallinksneoemf.Filter <em>Filter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Filter</em>'.
   * @see virtuallinksneoemf.Filter
   * @generated
   */
  EClass getFilter();

  /**
   * Returns the meta object for the reference '{@link virtuallinksneoemf.Filter#getTarget <em>Target</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Target</em>'.
   * @see virtuallinksneoemf.Filter#getTarget()
   * @see #getFilter()
   * @generated
   */
  EReference getFilter_Target();

  /**
   * Returns the meta object for class '{@link virtuallinksneoemf.ContributingModel <em>Contributing Model</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Contributing Model</em>'.
   * @see virtuallinksneoemf.ContributingModel
   * @generated
   */
  EClass getContributingModel();

  /**
   * Returns the meta object for the attribute '{@link virtuallinksneoemf.ContributingModel#getURI <em>URI</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>URI</em>'.
   * @see virtuallinksneoemf.ContributingModel#getURI()
   * @see #getContributingModel()
   * @generated
   */
  EAttribute getContributingModel_URI();

  /**
   * Returns the meta object for the containment reference list '{@link virtuallinksneoemf.ContributingModel#getConcreteElements <em>Concrete Elements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Concrete Elements</em>'.
   * @see virtuallinksneoemf.ContributingModel#getConcreteElements()
   * @see #getContributingModel()
   * @generated
   */
  EReference getContributingModel_ConcreteElements();

  /**
   * Returns the meta object for class '{@link virtuallinksneoemf.ConcreteElement <em>Concrete Element</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Concrete Element</em>'.
   * @see virtuallinksneoemf.ConcreteElement
   * @generated
   */
  EClass getConcreteElement();

  /**
   * Returns the meta object for the container reference '{@link virtuallinksneoemf.ConcreteElement#getModel <em>Model</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the container reference '<em>Model</em>'.
   * @see virtuallinksneoemf.ConcreteElement#getModel()
   * @see #getConcreteElement()
   * @generated
   */
  EReference getConcreteElement_Model();

  /**
   * Returns the meta object for the attribute '{@link virtuallinksneoemf.ConcreteElement#getPath <em>Path</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Path</em>'.
   * @see virtuallinksneoemf.ConcreteElement#getPath()
   * @see #getConcreteElement()
   * @generated
   */
  EAttribute getConcreteElement_Path();

  /**
   * Returns the meta object for class '{@link virtuallinksneoemf.Concept <em>Concept</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Concept</em>'.
   * @see virtuallinksneoemf.Concept
   * @generated
   */
  EClass getConcept();

  /**
   * Returns the meta object for class '{@link virtuallinksneoemf.Association <em>Association</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Association</em>'.
   * @see virtuallinksneoemf.Association
   * @generated
   */
  EClass getAssociation();

  /**
   * Returns the meta object for class '{@link virtuallinksneoemf.ConcreteConcept <em>Concrete Concept</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Concrete Concept</em>'.
   * @see virtuallinksneoemf.ConcreteConcept
   * @generated
   */
  EClass getConcreteConcept();

  /**
   * Returns the meta object for class '{@link virtuallinksneoemf.ConcreteAssociation <em>Concrete Association</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Concrete Association</em>'.
   * @see virtuallinksneoemf.ConcreteAssociation
   * @generated
   */
  EClass getConcreteAssociation();

  /**
   * Returns the meta object for class '{@link virtuallinksneoemf.Element <em>Element</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Element</em>'.
   * @see virtuallinksneoemf.Element
   * @generated
   */
  EClass getElement();

  /**
   * Returns the meta object for class '{@link virtuallinksneoemf.VirtualElement <em>Virtual Element</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Virtual Element</em>'.
   * @see virtuallinksneoemf.VirtualElement
   * @generated
   */
  EClass getVirtualElement();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  VirtuallinksneoemfFactory getVirtuallinksneoemfFactory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each operation of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals {
    /**
     * The meta object literal for the '{@link virtuallinksneoemf.impl.WeavingModelImpl <em>Weaving Model</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see virtuallinksneoemf.impl.WeavingModelImpl
     * @see virtuallinksneoemf.impl.VirtuallinksneoemfPackageImpl#getWeavingModel()
     * @generated
     */
    EClass WEAVING_MODEL = eINSTANCE.getWeavingModel();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute WEAVING_MODEL__NAME = eINSTANCE.getWeavingModel_Name();

    /**
     * The meta object literal for the '<em><b>Virtual Links</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference WEAVING_MODEL__VIRTUAL_LINKS = eINSTANCE.getWeavingModel_VirtualLinks();

    /**
     * The meta object literal for the '<em><b>Contributing Models</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference WEAVING_MODEL__CONTRIBUTING_MODELS = eINSTANCE.getWeavingModel_ContributingModels();

    /**
     * The meta object literal for the '<em><b>Whitelist</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute WEAVING_MODEL__WHITELIST = eINSTANCE.getWeavingModel_Whitelist();

    /**
     * The meta object literal for the '<em><b>Virtual Concepts</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference WEAVING_MODEL__VIRTUAL_CONCEPTS = eINSTANCE.getWeavingModel_VirtualConcepts();

    /**
     * The meta object literal for the '<em><b>Virtual Properties</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference WEAVING_MODEL__VIRTUAL_PROPERTIES = eINSTANCE.getWeavingModel_VirtualProperties();

    /**
     * The meta object literal for the '<em><b>Virtual Associations</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference WEAVING_MODEL__VIRTUAL_ASSOCIATIONS = eINSTANCE.getWeavingModel_VirtualAssociations();

    /**
     * The meta object literal for the '<em><b>Virtual Elements</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference WEAVING_MODEL__VIRTUAL_ELEMENTS = eINSTANCE.getWeavingModel_VirtualElements();

    /**
     * The meta object literal for the '<em><b>Filters</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference WEAVING_MODEL__FILTERS = eINSTANCE.getWeavingModel_Filters();

    /**
     * The meta object literal for the '{@link virtuallinksneoemf.impl.VirtualLinkImpl <em>Virtual Link</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see virtuallinksneoemf.impl.VirtualLinkImpl
     * @see virtuallinksneoemf.impl.VirtuallinksneoemfPackageImpl#getVirtualLink()
     * @generated
     */
    EClass VIRTUAL_LINK = eINSTANCE.getVirtualLink();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute VIRTUAL_LINK__NAME = eINSTANCE.getVirtualLink_Name();

    /**
     * The meta object literal for the '{@link virtuallinksneoemf.impl.VirtualConceptImpl <em>Virtual Concept</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see virtuallinksneoemf.impl.VirtualConceptImpl
     * @see virtuallinksneoemf.impl.VirtuallinksneoemfPackageImpl#getVirtualConcept()
     * @generated
     */
    EClass VIRTUAL_CONCEPT = eINSTANCE.getVirtualConcept();

    /**
     * The meta object literal for the '<em><b>Super Concepts</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference VIRTUAL_CONCEPT__SUPER_CONCEPTS = eINSTANCE.getVirtualConcept_SuperConcepts();

    /**
     * The meta object literal for the '<em><b>Sub Concepts</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference VIRTUAL_CONCEPT__SUB_CONCEPTS = eINSTANCE.getVirtualConcept_SubConcepts();

    /**
     * The meta object literal for the '{@link virtuallinksneoemf.impl.VirtualPropertyImpl <em>Virtual Property</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see virtuallinksneoemf.impl.VirtualPropertyImpl
     * @see virtuallinksneoemf.impl.VirtuallinksneoemfPackageImpl#getVirtualProperty()
     * @generated
     */
    EClass VIRTUAL_PROPERTY = eINSTANCE.getVirtualProperty();

    /**
     * The meta object literal for the '<em><b>Parent</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference VIRTUAL_PROPERTY__PARENT = eINSTANCE.getVirtualProperty_Parent();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute VIRTUAL_PROPERTY__TYPE = eINSTANCE.getVirtualProperty_Type();

    /**
     * The meta object literal for the '<em><b>Optional</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute VIRTUAL_PROPERTY__OPTIONAL = eINSTANCE.getVirtualProperty_Optional();

    /**
     * The meta object literal for the '{@link virtuallinksneoemf.impl.VirtualAssociationImpl <em>Virtual Association</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see virtuallinksneoemf.impl.VirtualAssociationImpl
     * @see virtuallinksneoemf.impl.VirtuallinksneoemfPackageImpl#getVirtualAssociation()
     * @generated
     */
    EClass VIRTUAL_ASSOCIATION = eINSTANCE.getVirtualAssociation();

    /**
     * The meta object literal for the '<em><b>Source</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference VIRTUAL_ASSOCIATION__SOURCE = eINSTANCE.getVirtualAssociation_Source();

    /**
     * The meta object literal for the '<em><b>Target</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference VIRTUAL_ASSOCIATION__TARGET = eINSTANCE.getVirtualAssociation_Target();

    /**
     * The meta object literal for the '<em><b>Lower Bound</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute VIRTUAL_ASSOCIATION__LOWER_BOUND = eINSTANCE.getVirtualAssociation_LowerBound();

    /**
     * The meta object literal for the '<em><b>Upper Bound</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute VIRTUAL_ASSOCIATION__UPPER_BOUND = eINSTANCE.getVirtualAssociation_UpperBound();

    /**
     * The meta object literal for the '<em><b>Opposite</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference VIRTUAL_ASSOCIATION__OPPOSITE = eINSTANCE.getVirtualAssociation_Opposite();

    /**
     * The meta object literal for the '<em><b>Composition</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute VIRTUAL_ASSOCIATION__COMPOSITION = eINSTANCE.getVirtualAssociation_Composition();

    /**
     * The meta object literal for the '{@link virtuallinksneoemf.impl.FilterImpl <em>Filter</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see virtuallinksneoemf.impl.FilterImpl
     * @see virtuallinksneoemf.impl.VirtuallinksneoemfPackageImpl#getFilter()
     * @generated
     */
    EClass FILTER = eINSTANCE.getFilter();

    /**
     * The meta object literal for the '<em><b>Target</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FILTER__TARGET = eINSTANCE.getFilter_Target();

    /**
     * The meta object literal for the '{@link virtuallinksneoemf.impl.ContributingModelImpl <em>Contributing Model</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see virtuallinksneoemf.impl.ContributingModelImpl
     * @see virtuallinksneoemf.impl.VirtuallinksneoemfPackageImpl#getContributingModel()
     * @generated
     */
    EClass CONTRIBUTING_MODEL = eINSTANCE.getContributingModel();

    /**
     * The meta object literal for the '<em><b>URI</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CONTRIBUTING_MODEL__URI = eINSTANCE.getContributingModel_URI();

    /**
     * The meta object literal for the '<em><b>Concrete Elements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONTRIBUTING_MODEL__CONCRETE_ELEMENTS = eINSTANCE.getContributingModel_ConcreteElements();

    /**
     * The meta object literal for the '{@link virtuallinksneoemf.impl.ConcreteElementImpl <em>Concrete Element</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see virtuallinksneoemf.impl.ConcreteElementImpl
     * @see virtuallinksneoemf.impl.VirtuallinksneoemfPackageImpl#getConcreteElement()
     * @generated
     */
    EClass CONCRETE_ELEMENT = eINSTANCE.getConcreteElement();

    /**
     * The meta object literal for the '<em><b>Model</b></em>' container reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONCRETE_ELEMENT__MODEL = eINSTANCE.getConcreteElement_Model();

    /**
     * The meta object literal for the '<em><b>Path</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CONCRETE_ELEMENT__PATH = eINSTANCE.getConcreteElement_Path();

    /**
     * The meta object literal for the '{@link virtuallinksneoemf.impl.ConceptImpl <em>Concept</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see virtuallinksneoemf.impl.ConceptImpl
     * @see virtuallinksneoemf.impl.VirtuallinksneoemfPackageImpl#getConcept()
     * @generated
     */
    EClass CONCEPT = eINSTANCE.getConcept();

    /**
     * The meta object literal for the '{@link virtuallinksneoemf.impl.AssociationImpl <em>Association</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see virtuallinksneoemf.impl.AssociationImpl
     * @see virtuallinksneoemf.impl.VirtuallinksneoemfPackageImpl#getAssociation()
     * @generated
     */
    EClass ASSOCIATION = eINSTANCE.getAssociation();

    /**
     * The meta object literal for the '{@link virtuallinksneoemf.impl.ConcreteConceptImpl <em>Concrete Concept</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see virtuallinksneoemf.impl.ConcreteConceptImpl
     * @see virtuallinksneoemf.impl.VirtuallinksneoemfPackageImpl#getConcreteConcept()
     * @generated
     */
    EClass CONCRETE_CONCEPT = eINSTANCE.getConcreteConcept();

    /**
     * The meta object literal for the '{@link virtuallinksneoemf.impl.ConcreteAssociationImpl <em>Concrete Association</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see virtuallinksneoemf.impl.ConcreteAssociationImpl
     * @see virtuallinksneoemf.impl.VirtuallinksneoemfPackageImpl#getConcreteAssociation()
     * @generated
     */
    EClass CONCRETE_ASSOCIATION = eINSTANCE.getConcreteAssociation();

    /**
     * The meta object literal for the '{@link virtuallinksneoemf.impl.ElementImpl <em>Element</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see virtuallinksneoemf.impl.ElementImpl
     * @see virtuallinksneoemf.impl.VirtuallinksneoemfPackageImpl#getElement()
     * @generated
     */
    EClass ELEMENT = eINSTANCE.getElement();

    /**
     * The meta object literal for the '{@link virtuallinksneoemf.impl.VirtualElementImpl <em>Virtual Element</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see virtuallinksneoemf.impl.VirtualElementImpl
     * @see virtuallinksneoemf.impl.VirtuallinksneoemfPackageImpl#getVirtualElement()
     * @generated
     */
    EClass VIRTUAL_ELEMENT = eINSTANCE.getVirtualElement();

  }

} //VirtuallinksneoemfPackage
