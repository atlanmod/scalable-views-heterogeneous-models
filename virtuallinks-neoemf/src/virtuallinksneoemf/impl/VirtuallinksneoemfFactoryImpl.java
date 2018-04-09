/**
 */
package virtuallinksneoemf.impl;

import org.atlanmod.emfviews.virtuallinks.ConcreteAssociation;
import org.atlanmod.emfviews.virtuallinks.ConcreteConcept;
import org.atlanmod.emfviews.virtuallinks.ConcreteElement;
import org.atlanmod.emfviews.virtuallinks.ContributingModel;
import org.atlanmod.emfviews.virtuallinks.Filter;
import org.atlanmod.emfviews.virtuallinks.VirtualAssociation;
import org.atlanmod.emfviews.virtuallinks.VirtualConcept;
import org.atlanmod.emfviews.virtuallinks.VirtualProperty;
import org.atlanmod.emfviews.virtuallinks.WeavingModel;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import virtuallinksneoemf.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class VirtuallinksneoemfFactoryImpl extends EFactoryImpl implements VirtuallinksneoemfFactory {
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static VirtuallinksneoemfFactory init() {
    try {
      VirtuallinksneoemfFactory theVirtuallinksneoemfFactory = (VirtuallinksneoemfFactory)EPackage.Registry.INSTANCE.getEFactory(VirtuallinksneoemfPackage.eNS_URI);
      if (theVirtuallinksneoemfFactory != null) {
        return theVirtuallinksneoemfFactory;
      }
    }
    catch (Exception exception) {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new VirtuallinksneoemfFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public VirtuallinksneoemfFactoryImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass) {
    switch (eClass.getClassifierID()) {
      case VirtuallinksneoemfPackage.WEAVING_MODEL: return createWeavingModel();
      case VirtuallinksneoemfPackage.VIRTUAL_CONCEPT: return createVirtualConcept();
      case VirtuallinksneoemfPackage.VIRTUAL_PROPERTY: return createVirtualProperty();
      case VirtuallinksneoemfPackage.VIRTUAL_ASSOCIATION: return createVirtualAssociation();
      case VirtuallinksneoemfPackage.FILTER: return createFilter();
      case VirtuallinksneoemfPackage.CONTRIBUTING_MODEL: return createContributingModel();
      case VirtuallinksneoemfPackage.CONCRETE_ELEMENT: return createConcreteElement();
      case VirtuallinksneoemfPackage.CONCRETE_CONCEPT: return createConcreteConcept();
      case VirtuallinksneoemfPackage.CONCRETE_ASSOCIATION: return createConcreteAssociation();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public WeavingModel createWeavingModel() {
    WeavingModelImpl weavingModel = new WeavingModelImpl();
    return weavingModel;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public VirtualConcept createVirtualConcept() {
    VirtualConceptImpl virtualConcept = new VirtualConceptImpl();
    return virtualConcept;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public VirtualProperty createVirtualProperty() {
    VirtualPropertyImpl virtualProperty = new VirtualPropertyImpl();
    return virtualProperty;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public VirtualAssociation createVirtualAssociation() {
    VirtualAssociationImpl virtualAssociation = new VirtualAssociationImpl();
    return virtualAssociation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Filter createFilter() {
    FilterImpl filter = new FilterImpl();
    return filter;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public ContributingModel createContributingModel() {
    ContributingModelImpl contributingModel = new ContributingModelImpl();
    return contributingModel;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public ConcreteElement createConcreteElement() {
    ConcreteElementImpl concreteElement = new ConcreteElementImpl();
    return concreteElement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public ConcreteConcept createConcreteConcept() {
    ConcreteConceptImpl concreteConcept = new ConcreteConceptImpl();
    return concreteConcept;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public ConcreteAssociation createConcreteAssociation() {
    ConcreteAssociationImpl concreteAssociation = new ConcreteAssociationImpl();
    return concreteAssociation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public VirtuallinksneoemfPackage getVirtuallinksneoemfPackage() {
    return (VirtuallinksneoemfPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static VirtuallinksneoemfPackage getPackage() {
    return VirtuallinksneoemfPackage.eINSTANCE;
  }

} //VirtuallinksneoemfFactoryImpl
