/**
 */
package org.eclipse.gmt.modisco.java.cdo.java.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.gmt.modisco.java.cdo.java.ConditionalExpression;
import org.eclipse.gmt.modisco.java.cdo.java.Expression;
import org.eclipse.gmt.modisco.java.cdo.java.JavaPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Conditional Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.cdo.java.impl.ConditionalExpressionImpl#getElseExpression <em>Else Expression</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.cdo.java.impl.ConditionalExpressionImpl#getExpression <em>Expression</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.cdo.java.impl.ConditionalExpressionImpl#getThenExpression <em>Then Expression</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ConditionalExpressionImpl extends ExpressionImpl implements ConditionalExpression {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConditionalExpressionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getConditionalExpression();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getElseExpression() {
		return (Expression)eGet(JavaPackage.eINSTANCE.getConditionalExpression_ElseExpression(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setElseExpression(Expression newElseExpression) {
		eSet(JavaPackage.eINSTANCE.getConditionalExpression_ElseExpression(), newElseExpression);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getExpression() {
		return (Expression)eGet(JavaPackage.eINSTANCE.getConditionalExpression_Expression(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExpression(Expression newExpression) {
		eSet(JavaPackage.eINSTANCE.getConditionalExpression_Expression(), newExpression);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Expression getThenExpression() {
		return (Expression)eGet(JavaPackage.eINSTANCE.getConditionalExpression_ThenExpression(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setThenExpression(Expression newThenExpression) {
		eSet(JavaPackage.eINSTANCE.getConditionalExpression_ThenExpression(), newThenExpression);
	}

} //ConditionalExpressionImpl
