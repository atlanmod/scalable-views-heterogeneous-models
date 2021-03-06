/**
 */
package virtuallinksneoemf.util;

import org.atlanmod.emfviews.virtuallinks.Association;
import org.atlanmod.emfviews.virtuallinks.Concept;
import org.atlanmod.emfviews.virtuallinks.ConcreteAssociation;
import org.atlanmod.emfviews.virtuallinks.ConcreteConcept;
import org.atlanmod.emfviews.virtuallinks.ConcreteElement;
import org.atlanmod.emfviews.virtuallinks.ContributingModel;
import org.atlanmod.emfviews.virtuallinks.Element;
import org.atlanmod.emfviews.virtuallinks.Filter;
import org.atlanmod.emfviews.virtuallinks.VirtualAssociation;
import org.atlanmod.emfviews.virtuallinks.VirtualConcept;
import org.atlanmod.emfviews.virtuallinks.VirtualElement;
import org.atlanmod.emfviews.virtuallinks.VirtualLink;
import org.atlanmod.emfviews.virtuallinks.VirtualProperty;
import org.atlanmod.emfviews.virtuallinks.WeavingModel;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import virtuallinksneoemf.VirtuallinksneoemfPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see virtuallinksneoemf.VirtuallinksneoemfPackage
 * @generated
 */
public class VirtuallinksneoemfAdapterFactory extends AdapterFactoryImpl {
  /**
   * The cached model package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static VirtuallinksneoemfPackage modelPackage;

  /**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public VirtuallinksneoemfAdapterFactory() {
    if (modelPackage == null) {
      modelPackage = VirtuallinksneoemfPackage.eINSTANCE;
    }
  }

  /**
   * Returns whether this factory is applicable for the type of the object.
   * <!-- begin-user-doc -->
   * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
   * <!-- end-user-doc -->
   * @return whether this factory is applicable for the type of the object.
   * @generated
   */
  @Override
  public boolean isFactoryForType(Object object) {
    if (object == modelPackage) {
      return true;
    }
    if (object instanceof EObject) {
      return ((EObject)object).eClass().getEPackage() == modelPackage;
    }
    return false;
  }

  /**
   * The switch that delegates to the <code>createXXX</code> methods.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected VirtuallinksneoemfSwitch<Adapter> modelSwitch =
    new VirtuallinksneoemfSwitch<Adapter>() {
      @Override
      public Adapter caseWeavingModel(WeavingModel object) {
        return createWeavingModelAdapter();
      }
      @Override
      public Adapter caseVirtualLink(VirtualLink object) {
        return createVirtualLinkAdapter();
      }
      @Override
      public Adapter caseVirtualConcept(VirtualConcept object) {
        return createVirtualConceptAdapter();
      }
      @Override
      public Adapter caseVirtualProperty(VirtualProperty object) {
        return createVirtualPropertyAdapter();
      }
      @Override
      public Adapter caseVirtualAssociation(VirtualAssociation object) {
        return createVirtualAssociationAdapter();
      }
      @Override
      public Adapter caseFilter(Filter object) {
        return createFilterAdapter();
      }
      @Override
      public Adapter caseContributingModel(ContributingModel object) {
        return createContributingModelAdapter();
      }
      @Override
      public Adapter caseConcreteElement(ConcreteElement object) {
        return createConcreteElementAdapter();
      }
      @Override
      public Adapter caseConcept(Concept object) {
        return createConceptAdapter();
      }
      @Override
      public Adapter caseAssociation(Association object) {
        return createAssociationAdapter();
      }
      @Override
      public Adapter caseConcreteConcept(ConcreteConcept object) {
        return createConcreteConceptAdapter();
      }
      @Override
      public Adapter caseConcreteAssociation(ConcreteAssociation object) {
        return createConcreteAssociationAdapter();
      }
      @Override
      public Adapter caseElement(Element object) {
        return createElementAdapter();
      }
      @Override
      public Adapter caseVirtualElement(VirtualElement object) {
        return createVirtualElementAdapter();
      }
      @Override
      public Adapter defaultCase(EObject object) {
        return createEObjectAdapter();
      }
    };

  /**
   * Creates an adapter for the <code>target</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param target the object to adapt.
   * @return the adapter for the <code>target</code>.
   * @generated
   */
  @Override
  public Adapter createAdapter(Notifier target) {
    return modelSwitch.doSwitch((EObject)target);
  }


  /**
   * Creates a new adapter for an object of class '{@link virtuallinksneoemf.WeavingModel <em>Weaving Model</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see virtuallinksneoemf.WeavingModel
   * @generated
   */
  public Adapter createWeavingModelAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link virtuallinksneoemf.VirtualLink <em>Virtual Link</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see virtuallinksneoemf.VirtualLink
   * @generated
   */
  public Adapter createVirtualLinkAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link virtuallinksneoemf.VirtualConcept <em>Virtual Concept</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see virtuallinksneoemf.VirtualConcept
   * @generated
   */
  public Adapter createVirtualConceptAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link virtuallinksneoemf.VirtualProperty <em>Virtual Property</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see virtuallinksneoemf.VirtualProperty
   * @generated
   */
  public Adapter createVirtualPropertyAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link virtuallinksneoemf.VirtualAssociation <em>Virtual Association</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see virtuallinksneoemf.VirtualAssociation
   * @generated
   */
  public Adapter createVirtualAssociationAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link virtuallinksneoemf.Filter <em>Filter</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see virtuallinksneoemf.Filter
   * @generated
   */
  public Adapter createFilterAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link virtuallinksneoemf.ContributingModel <em>Contributing Model</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see virtuallinksneoemf.ContributingModel
   * @generated
   */
  public Adapter createContributingModelAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link virtuallinksneoemf.ConcreteElement <em>Concrete Element</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see virtuallinksneoemf.ConcreteElement
   * @generated
   */
  public Adapter createConcreteElementAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link virtuallinksneoemf.Concept <em>Concept</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see virtuallinksneoemf.Concept
   * @generated
   */
  public Adapter createConceptAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link virtuallinksneoemf.Association <em>Association</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see virtuallinksneoemf.Association
   * @generated
   */
  public Adapter createAssociationAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link virtuallinksneoemf.ConcreteConcept <em>Concrete Concept</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see virtuallinksneoemf.ConcreteConcept
   * @generated
   */
  public Adapter createConcreteConceptAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link virtuallinksneoemf.ConcreteAssociation <em>Concrete Association</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see virtuallinksneoemf.ConcreteAssociation
   * @generated
   */
  public Adapter createConcreteAssociationAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link virtuallinksneoemf.Element <em>Element</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see virtuallinksneoemf.Element
   * @generated
   */
  public Adapter createElementAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link virtuallinksneoemf.VirtualElement <em>Virtual Element</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see virtuallinksneoemf.VirtualElement
   * @generated
   */
  public Adapter createVirtualElementAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for the default case.
   * <!-- begin-user-doc -->
   * This default implementation returns null.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @generated
   */
  public Adapter createEObjectAdapter() {
    return null;
  }

} //VirtuallinksneoemfAdapterFactory
