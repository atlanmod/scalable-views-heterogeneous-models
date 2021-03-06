/**
 */
package org.eclipse.gmt.modisco.java.cdo.java.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.gmt.modisco.java.cdo.java.JavaPackage;
import org.eclipse.gmt.modisco.java.cdo.java.TypeAccess;
import org.eclipse.gmt.modisco.java.cdo.java.TypeLiteral;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Type Literal</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.cdo.java.impl.TypeLiteralImpl#getType <em>Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TypeLiteralImpl extends ExpressionImpl implements TypeLiteral {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TypeLiteralImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getTypeLiteral();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeAccess getType() {
		return (TypeAccess)eGet(JavaPackage.eINSTANCE.getTypeLiteral_Type(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(TypeAccess newType) {
		eSet(JavaPackage.eINSTANCE.getTypeLiteral_Type(), newType);
	}

} //TypeLiteralImpl
