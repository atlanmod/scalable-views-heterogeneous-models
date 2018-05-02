/**
 */
package org.eclipse.gmt.modisco.java.cdo.java.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.gmt.modisco.java.cdo.java.ASTNode;
import org.eclipse.gmt.modisco.java.cdo.java.AbstractMethodDeclaration;
import org.eclipse.gmt.modisco.java.cdo.java.AbstractMethodInvocation;
import org.eclipse.gmt.modisco.java.cdo.java.AbstractTypeDeclaration;
import org.eclipse.gmt.modisco.java.cdo.java.AbstractTypeQualifiedExpression;
import org.eclipse.gmt.modisco.java.cdo.java.AbstractVariablesContainer;
import org.eclipse.gmt.modisco.java.cdo.java.Annotation;
import org.eclipse.gmt.modisco.java.cdo.java.AnnotationMemberValuePair;
import org.eclipse.gmt.modisco.java.cdo.java.AnnotationTypeDeclaration;
import org.eclipse.gmt.modisco.java.cdo.java.AnnotationTypeMemberDeclaration;
import org.eclipse.gmt.modisco.java.cdo.java.AnonymousClassDeclaration;
import org.eclipse.gmt.modisco.java.cdo.java.Archive;
import org.eclipse.gmt.modisco.java.cdo.java.ArrayAccess;
import org.eclipse.gmt.modisco.java.cdo.java.ArrayCreation;
import org.eclipse.gmt.modisco.java.cdo.java.ArrayInitializer;
import org.eclipse.gmt.modisco.java.cdo.java.ArrayLengthAccess;
import org.eclipse.gmt.modisco.java.cdo.java.ArrayType;
import org.eclipse.gmt.modisco.java.cdo.java.AssertStatement;
import org.eclipse.gmt.modisco.java.cdo.java.Assignment;
import org.eclipse.gmt.modisco.java.cdo.java.Block;
import org.eclipse.gmt.modisco.java.cdo.java.BlockComment;
import org.eclipse.gmt.modisco.java.cdo.java.BodyDeclaration;
import org.eclipse.gmt.modisco.java.cdo.java.BooleanLiteral;
import org.eclipse.gmt.modisco.java.cdo.java.BreakStatement;
import org.eclipse.gmt.modisco.java.cdo.java.CastExpression;
import org.eclipse.gmt.modisco.java.cdo.java.CatchClause;
import org.eclipse.gmt.modisco.java.cdo.java.CharacterLiteral;
import org.eclipse.gmt.modisco.java.cdo.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.cdo.java.ClassFile;
import org.eclipse.gmt.modisco.java.cdo.java.ClassInstanceCreation;
import org.eclipse.gmt.modisco.java.cdo.java.Comment;
import org.eclipse.gmt.modisco.java.cdo.java.CompilationUnit;
import org.eclipse.gmt.modisco.java.cdo.java.ConditionalExpression;
import org.eclipse.gmt.modisco.java.cdo.java.ConstructorDeclaration;
import org.eclipse.gmt.modisco.java.cdo.java.ConstructorInvocation;
import org.eclipse.gmt.modisco.java.cdo.java.ContinueStatement;
import org.eclipse.gmt.modisco.java.cdo.java.DoStatement;
import org.eclipse.gmt.modisco.java.cdo.java.EmptyStatement;
import org.eclipse.gmt.modisco.java.cdo.java.EnhancedForStatement;
import org.eclipse.gmt.modisco.java.cdo.java.EnumConstantDeclaration;
import org.eclipse.gmt.modisco.java.cdo.java.EnumDeclaration;
import org.eclipse.gmt.modisco.java.cdo.java.Expression;
import org.eclipse.gmt.modisco.java.cdo.java.ExpressionStatement;
import org.eclipse.gmt.modisco.java.cdo.java.FieldAccess;
import org.eclipse.gmt.modisco.java.cdo.java.FieldDeclaration;
import org.eclipse.gmt.modisco.java.cdo.java.ForStatement;
import org.eclipse.gmt.modisco.java.cdo.java.IfStatement;
import org.eclipse.gmt.modisco.java.cdo.java.ImportDeclaration;
import org.eclipse.gmt.modisco.java.cdo.java.InfixExpression;
import org.eclipse.gmt.modisco.java.cdo.java.Initializer;
import org.eclipse.gmt.modisco.java.cdo.java.InstanceofExpression;
import org.eclipse.gmt.modisco.java.cdo.java.InterfaceDeclaration;
import org.eclipse.gmt.modisco.java.cdo.java.JavaPackage;
import org.eclipse.gmt.modisco.java.cdo.java.Javadoc;
import org.eclipse.gmt.modisco.java.cdo.java.LabeledStatement;
import org.eclipse.gmt.modisco.java.cdo.java.LineComment;
import org.eclipse.gmt.modisco.java.cdo.java.Manifest;
import org.eclipse.gmt.modisco.java.cdo.java.ManifestAttribute;
import org.eclipse.gmt.modisco.java.cdo.java.ManifestEntry;
import org.eclipse.gmt.modisco.java.cdo.java.MemberRef;
import org.eclipse.gmt.modisco.java.cdo.java.MethodDeclaration;
import org.eclipse.gmt.modisco.java.cdo.java.MethodInvocation;
import org.eclipse.gmt.modisco.java.cdo.java.MethodRef;
import org.eclipse.gmt.modisco.java.cdo.java.MethodRefParameter;
import org.eclipse.gmt.modisco.java.cdo.java.Model;
import org.eclipse.gmt.modisco.java.cdo.java.Modifier;
import org.eclipse.gmt.modisco.java.cdo.java.NamedElement;
import org.eclipse.gmt.modisco.java.cdo.java.NamespaceAccess;
import org.eclipse.gmt.modisco.java.cdo.java.NullLiteral;
import org.eclipse.gmt.modisco.java.cdo.java.NumberLiteral;
import org.eclipse.gmt.modisco.java.cdo.java.PackageAccess;
import org.eclipse.gmt.modisco.java.cdo.java.ParameterizedType;
import org.eclipse.gmt.modisco.java.cdo.java.ParenthesizedExpression;
import org.eclipse.gmt.modisco.java.cdo.java.PostfixExpression;
import org.eclipse.gmt.modisco.java.cdo.java.PrefixExpression;
import org.eclipse.gmt.modisco.java.cdo.java.PrimitiveType;
import org.eclipse.gmt.modisco.java.cdo.java.PrimitiveTypeBoolean;
import org.eclipse.gmt.modisco.java.cdo.java.PrimitiveTypeByte;
import org.eclipse.gmt.modisco.java.cdo.java.PrimitiveTypeChar;
import org.eclipse.gmt.modisco.java.cdo.java.PrimitiveTypeDouble;
import org.eclipse.gmt.modisco.java.cdo.java.PrimitiveTypeFloat;
import org.eclipse.gmt.modisco.java.cdo.java.PrimitiveTypeInt;
import org.eclipse.gmt.modisco.java.cdo.java.PrimitiveTypeLong;
import org.eclipse.gmt.modisco.java.cdo.java.PrimitiveTypeShort;
import org.eclipse.gmt.modisco.java.cdo.java.PrimitiveTypeVoid;
import org.eclipse.gmt.modisco.java.cdo.java.ReturnStatement;
import org.eclipse.gmt.modisco.java.cdo.java.SingleVariableAccess;
import org.eclipse.gmt.modisco.java.cdo.java.SingleVariableDeclaration;
import org.eclipse.gmt.modisco.java.cdo.java.Statement;
import org.eclipse.gmt.modisco.java.cdo.java.StringLiteral;
import org.eclipse.gmt.modisco.java.cdo.java.SuperConstructorInvocation;
import org.eclipse.gmt.modisco.java.cdo.java.SuperFieldAccess;
import org.eclipse.gmt.modisco.java.cdo.java.SuperMethodInvocation;
import org.eclipse.gmt.modisco.java.cdo.java.SwitchCase;
import org.eclipse.gmt.modisco.java.cdo.java.SwitchStatement;
import org.eclipse.gmt.modisco.java.cdo.java.SynchronizedStatement;
import org.eclipse.gmt.modisco.java.cdo.java.TagElement;
import org.eclipse.gmt.modisco.java.cdo.java.TextElement;
import org.eclipse.gmt.modisco.java.cdo.java.ThisExpression;
import org.eclipse.gmt.modisco.java.cdo.java.ThrowStatement;
import org.eclipse.gmt.modisco.java.cdo.java.TryStatement;
import org.eclipse.gmt.modisco.java.cdo.java.Type;
import org.eclipse.gmt.modisco.java.cdo.java.TypeAccess;
import org.eclipse.gmt.modisco.java.cdo.java.TypeDeclaration;
import org.eclipse.gmt.modisco.java.cdo.java.TypeDeclarationStatement;
import org.eclipse.gmt.modisco.java.cdo.java.TypeLiteral;
import org.eclipse.gmt.modisco.java.cdo.java.TypeParameter;
import org.eclipse.gmt.modisco.java.cdo.java.UnresolvedAnnotationDeclaration;
import org.eclipse.gmt.modisco.java.cdo.java.UnresolvedAnnotationTypeMemberDeclaration;
import org.eclipse.gmt.modisco.java.cdo.java.UnresolvedClassDeclaration;
import org.eclipse.gmt.modisco.java.cdo.java.UnresolvedEnumDeclaration;
import org.eclipse.gmt.modisco.java.cdo.java.UnresolvedInterfaceDeclaration;
import org.eclipse.gmt.modisco.java.cdo.java.UnresolvedItem;
import org.eclipse.gmt.modisco.java.cdo.java.UnresolvedItemAccess;
import org.eclipse.gmt.modisco.java.cdo.java.UnresolvedLabeledStatement;
import org.eclipse.gmt.modisco.java.cdo.java.UnresolvedMethodDeclaration;
import org.eclipse.gmt.modisco.java.cdo.java.UnresolvedSingleVariableDeclaration;
import org.eclipse.gmt.modisco.java.cdo.java.UnresolvedType;
import org.eclipse.gmt.modisco.java.cdo.java.UnresolvedTypeDeclaration;
import org.eclipse.gmt.modisco.java.cdo.java.UnresolvedVariableDeclarationFragment;
import org.eclipse.gmt.modisco.java.cdo.java.VariableDeclaration;
import org.eclipse.gmt.modisco.java.cdo.java.VariableDeclarationExpression;
import org.eclipse.gmt.modisco.java.cdo.java.VariableDeclarationFragment;
import org.eclipse.gmt.modisco.java.cdo.java.VariableDeclarationStatement;
import org.eclipse.gmt.modisco.java.cdo.java.WhileStatement;
import org.eclipse.gmt.modisco.java.cdo.java.WildCardType;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.gmt.modisco.java.cdo.java.JavaPackage
 * @generated
 */
public class JavaAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static JavaPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public JavaAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = JavaPackage.eINSTANCE;
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
	protected JavaSwitch<Adapter> modelSwitch =
		new JavaSwitch<Adapter>() {
			@Override
			public Adapter caseAbstractMethodDeclaration(AbstractMethodDeclaration object) {
				return createAbstractMethodDeclarationAdapter();
			}
			@Override
			public Adapter caseAbstractMethodInvocation(AbstractMethodInvocation object) {
				return createAbstractMethodInvocationAdapter();
			}
			@Override
			public Adapter caseAbstractTypeDeclaration(AbstractTypeDeclaration object) {
				return createAbstractTypeDeclarationAdapter();
			}
			@Override
			public Adapter caseAbstractTypeQualifiedExpression(AbstractTypeQualifiedExpression object) {
				return createAbstractTypeQualifiedExpressionAdapter();
			}
			@Override
			public Adapter caseAbstractVariablesContainer(AbstractVariablesContainer object) {
				return createAbstractVariablesContainerAdapter();
			}
			@Override
			public Adapter caseAnnotation(Annotation object) {
				return createAnnotationAdapter();
			}
			@Override
			public Adapter caseArchive(Archive object) {
				return createArchiveAdapter();
			}
			@Override
			public Adapter caseAssertStatement(AssertStatement object) {
				return createAssertStatementAdapter();
			}
			@Override
			public Adapter caseASTNode(ASTNode object) {
				return createASTNodeAdapter();
			}
			@Override
			public Adapter caseAnnotationMemberValuePair(AnnotationMemberValuePair object) {
				return createAnnotationMemberValuePairAdapter();
			}
			@Override
			public Adapter caseAnnotationTypeDeclaration(AnnotationTypeDeclaration object) {
				return createAnnotationTypeDeclarationAdapter();
			}
			@Override
			public Adapter caseAnnotationTypeMemberDeclaration(AnnotationTypeMemberDeclaration object) {
				return createAnnotationTypeMemberDeclarationAdapter();
			}
			@Override
			public Adapter caseAnonymousClassDeclaration(AnonymousClassDeclaration object) {
				return createAnonymousClassDeclarationAdapter();
			}
			@Override
			public Adapter caseArrayAccess(ArrayAccess object) {
				return createArrayAccessAdapter();
			}
			@Override
			public Adapter caseArrayCreation(ArrayCreation object) {
				return createArrayCreationAdapter();
			}
			@Override
			public Adapter caseArrayInitializer(ArrayInitializer object) {
				return createArrayInitializerAdapter();
			}
			@Override
			public Adapter caseArrayLengthAccess(ArrayLengthAccess object) {
				return createArrayLengthAccessAdapter();
			}
			@Override
			public Adapter caseArrayType(ArrayType object) {
				return createArrayTypeAdapter();
			}
			@Override
			public Adapter caseAssignment(Assignment object) {
				return createAssignmentAdapter();
			}
			@Override
			public Adapter caseBodyDeclaration(BodyDeclaration object) {
				return createBodyDeclarationAdapter();
			}
			@Override
			public Adapter caseBooleanLiteral(BooleanLiteral object) {
				return createBooleanLiteralAdapter();
			}
			@Override
			public Adapter caseBlockComment(BlockComment object) {
				return createBlockCommentAdapter();
			}
			@Override
			public Adapter caseBlock(Block object) {
				return createBlockAdapter();
			}
			@Override
			public Adapter caseBreakStatement(BreakStatement object) {
				return createBreakStatementAdapter();
			}
			@Override
			public Adapter caseCastExpression(CastExpression object) {
				return createCastExpressionAdapter();
			}
			@Override
			public Adapter caseCatchClause(CatchClause object) {
				return createCatchClauseAdapter();
			}
			@Override
			public Adapter caseCharacterLiteral(CharacterLiteral object) {
				return createCharacterLiteralAdapter();
			}
			@Override
			public Adapter caseClassFile(ClassFile object) {
				return createClassFileAdapter();
			}
			@Override
			public Adapter caseClassInstanceCreation(ClassInstanceCreation object) {
				return createClassInstanceCreationAdapter();
			}
			@Override
			public Adapter caseConstructorDeclaration(ConstructorDeclaration object) {
				return createConstructorDeclarationAdapter();
			}
			@Override
			public Adapter caseConditionalExpression(ConditionalExpression object) {
				return createConditionalExpressionAdapter();
			}
			@Override
			public Adapter caseConstructorInvocation(ConstructorInvocation object) {
				return createConstructorInvocationAdapter();
			}
			@Override
			public Adapter caseClassDeclaration(ClassDeclaration object) {
				return createClassDeclarationAdapter();
			}
			@Override
			public Adapter caseComment(Comment object) {
				return createCommentAdapter();
			}
			@Override
			public Adapter caseCompilationUnit(CompilationUnit object) {
				return createCompilationUnitAdapter();
			}
			@Override
			public Adapter caseContinueStatement(ContinueStatement object) {
				return createContinueStatementAdapter();
			}
			@Override
			public Adapter caseDoStatement(DoStatement object) {
				return createDoStatementAdapter();
			}
			@Override
			public Adapter caseEmptyStatement(EmptyStatement object) {
				return createEmptyStatementAdapter();
			}
			@Override
			public Adapter caseEnhancedForStatement(EnhancedForStatement object) {
				return createEnhancedForStatementAdapter();
			}
			@Override
			public Adapter caseEnumConstantDeclaration(EnumConstantDeclaration object) {
				return createEnumConstantDeclarationAdapter();
			}
			@Override
			public Adapter caseEnumDeclaration(EnumDeclaration object) {
				return createEnumDeclarationAdapter();
			}
			@Override
			public Adapter caseExpression(Expression object) {
				return createExpressionAdapter();
			}
			@Override
			public Adapter caseExpressionStatement(ExpressionStatement object) {
				return createExpressionStatementAdapter();
			}
			@Override
			public Adapter caseFieldAccess(FieldAccess object) {
				return createFieldAccessAdapter();
			}
			@Override
			public Adapter caseFieldDeclaration(FieldDeclaration object) {
				return createFieldDeclarationAdapter();
			}
			@Override
			public Adapter caseForStatement(ForStatement object) {
				return createForStatementAdapter();
			}
			@Override
			public Adapter caseIfStatement(IfStatement object) {
				return createIfStatementAdapter();
			}
			@Override
			public Adapter caseImportDeclaration(ImportDeclaration object) {
				return createImportDeclarationAdapter();
			}
			@Override
			public Adapter caseInfixExpression(InfixExpression object) {
				return createInfixExpressionAdapter();
			}
			@Override
			public Adapter caseInitializer(Initializer object) {
				return createInitializerAdapter();
			}
			@Override
			public Adapter caseInstanceofExpression(InstanceofExpression object) {
				return createInstanceofExpressionAdapter();
			}
			@Override
			public Adapter caseInterfaceDeclaration(InterfaceDeclaration object) {
				return createInterfaceDeclarationAdapter();
			}
			@Override
			public Adapter caseJavadoc(Javadoc object) {
				return createJavadocAdapter();
			}
			@Override
			public Adapter caseLabeledStatement(LabeledStatement object) {
				return createLabeledStatementAdapter();
			}
			@Override
			public Adapter caseLineComment(LineComment object) {
				return createLineCommentAdapter();
			}
			@Override
			public Adapter caseManifest(Manifest object) {
				return createManifestAdapter();
			}
			@Override
			public Adapter caseManifestAttribute(ManifestAttribute object) {
				return createManifestAttributeAdapter();
			}
			@Override
			public Adapter caseManifestEntry(ManifestEntry object) {
				return createManifestEntryAdapter();
			}
			@Override
			public Adapter caseMemberRef(MemberRef object) {
				return createMemberRefAdapter();
			}
			@Override
			public Adapter caseMethodDeclaration(MethodDeclaration object) {
				return createMethodDeclarationAdapter();
			}
			@Override
			public Adapter caseMethodInvocation(MethodInvocation object) {
				return createMethodInvocationAdapter();
			}
			@Override
			public Adapter caseMethodRef(MethodRef object) {
				return createMethodRefAdapter();
			}
			@Override
			public Adapter caseMethodRefParameter(MethodRefParameter object) {
				return createMethodRefParameterAdapter();
			}
			@Override
			public Adapter caseModel(Model object) {
				return createModelAdapter();
			}
			@Override
			public Adapter caseModifier(Modifier object) {
				return createModifierAdapter();
			}
			@Override
			public Adapter caseNamedElement(NamedElement object) {
				return createNamedElementAdapter();
			}
			@Override
			public Adapter caseNamespaceAccess(NamespaceAccess object) {
				return createNamespaceAccessAdapter();
			}
			@Override
			public Adapter caseNumberLiteral(NumberLiteral object) {
				return createNumberLiteralAdapter();
			}
			@Override
			public Adapter caseNullLiteral(NullLiteral object) {
				return createNullLiteralAdapter();
			}
			@Override
			public Adapter casePackage(org.eclipse.gmt.modisco.java.cdo.java.Package object) {
				return createPackageAdapter();
			}
			@Override
			public Adapter casePackageAccess(PackageAccess object) {
				return createPackageAccessAdapter();
			}
			@Override
			public Adapter caseParameterizedType(ParameterizedType object) {
				return createParameterizedTypeAdapter();
			}
			@Override
			public Adapter caseParenthesizedExpression(ParenthesizedExpression object) {
				return createParenthesizedExpressionAdapter();
			}
			@Override
			public Adapter casePostfixExpression(PostfixExpression object) {
				return createPostfixExpressionAdapter();
			}
			@Override
			public Adapter casePrefixExpression(PrefixExpression object) {
				return createPrefixExpressionAdapter();
			}
			@Override
			public Adapter casePrimitiveType(PrimitiveType object) {
				return createPrimitiveTypeAdapter();
			}
			@Override
			public Adapter casePrimitiveTypeBoolean(PrimitiveTypeBoolean object) {
				return createPrimitiveTypeBooleanAdapter();
			}
			@Override
			public Adapter casePrimitiveTypeByte(PrimitiveTypeByte object) {
				return createPrimitiveTypeByteAdapter();
			}
			@Override
			public Adapter casePrimitiveTypeChar(PrimitiveTypeChar object) {
				return createPrimitiveTypeCharAdapter();
			}
			@Override
			public Adapter casePrimitiveTypeDouble(PrimitiveTypeDouble object) {
				return createPrimitiveTypeDoubleAdapter();
			}
			@Override
			public Adapter casePrimitiveTypeShort(PrimitiveTypeShort object) {
				return createPrimitiveTypeShortAdapter();
			}
			@Override
			public Adapter casePrimitiveTypeFloat(PrimitiveTypeFloat object) {
				return createPrimitiveTypeFloatAdapter();
			}
			@Override
			public Adapter casePrimitiveTypeInt(PrimitiveTypeInt object) {
				return createPrimitiveTypeIntAdapter();
			}
			@Override
			public Adapter casePrimitiveTypeLong(PrimitiveTypeLong object) {
				return createPrimitiveTypeLongAdapter();
			}
			@Override
			public Adapter casePrimitiveTypeVoid(PrimitiveTypeVoid object) {
				return createPrimitiveTypeVoidAdapter();
			}
			@Override
			public Adapter caseReturnStatement(ReturnStatement object) {
				return createReturnStatementAdapter();
			}
			@Override
			public Adapter caseSingleVariableAccess(SingleVariableAccess object) {
				return createSingleVariableAccessAdapter();
			}
			@Override
			public Adapter caseSingleVariableDeclaration(SingleVariableDeclaration object) {
				return createSingleVariableDeclarationAdapter();
			}
			@Override
			public Adapter caseStatement(Statement object) {
				return createStatementAdapter();
			}
			@Override
			public Adapter caseStringLiteral(StringLiteral object) {
				return createStringLiteralAdapter();
			}
			@Override
			public Adapter caseSuperConstructorInvocation(SuperConstructorInvocation object) {
				return createSuperConstructorInvocationAdapter();
			}
			@Override
			public Adapter caseSuperFieldAccess(SuperFieldAccess object) {
				return createSuperFieldAccessAdapter();
			}
			@Override
			public Adapter caseSuperMethodInvocation(SuperMethodInvocation object) {
				return createSuperMethodInvocationAdapter();
			}
			@Override
			public Adapter caseSwitchCase(SwitchCase object) {
				return createSwitchCaseAdapter();
			}
			@Override
			public Adapter caseSwitchStatement(SwitchStatement object) {
				return createSwitchStatementAdapter();
			}
			@Override
			public Adapter caseSynchronizedStatement(SynchronizedStatement object) {
				return createSynchronizedStatementAdapter();
			}
			@Override
			public Adapter caseTagElement(TagElement object) {
				return createTagElementAdapter();
			}
			@Override
			public Adapter caseTextElement(TextElement object) {
				return createTextElementAdapter();
			}
			@Override
			public Adapter caseThisExpression(ThisExpression object) {
				return createThisExpressionAdapter();
			}
			@Override
			public Adapter caseThrowStatement(ThrowStatement object) {
				return createThrowStatementAdapter();
			}
			@Override
			public Adapter caseTryStatement(TryStatement object) {
				return createTryStatementAdapter();
			}
			@Override
			public Adapter caseType(Type object) {
				return createTypeAdapter();
			}
			@Override
			public Adapter caseTypeAccess(TypeAccess object) {
				return createTypeAccessAdapter();
			}
			@Override
			public Adapter caseTypeDeclaration(TypeDeclaration object) {
				return createTypeDeclarationAdapter();
			}
			@Override
			public Adapter caseTypeDeclarationStatement(TypeDeclarationStatement object) {
				return createTypeDeclarationStatementAdapter();
			}
			@Override
			public Adapter caseTypeLiteral(TypeLiteral object) {
				return createTypeLiteralAdapter();
			}
			@Override
			public Adapter caseTypeParameter(TypeParameter object) {
				return createTypeParameterAdapter();
			}
			@Override
			public Adapter caseUnresolvedItem(UnresolvedItem object) {
				return createUnresolvedItemAdapter();
			}
			@Override
			public Adapter caseUnresolvedItemAccess(UnresolvedItemAccess object) {
				return createUnresolvedItemAccessAdapter();
			}
			@Override
			public Adapter caseUnresolvedAnnotationDeclaration(UnresolvedAnnotationDeclaration object) {
				return createUnresolvedAnnotationDeclarationAdapter();
			}
			@Override
			public Adapter caseUnresolvedAnnotationTypeMemberDeclaration(UnresolvedAnnotationTypeMemberDeclaration object) {
				return createUnresolvedAnnotationTypeMemberDeclarationAdapter();
			}
			@Override
			public Adapter caseUnresolvedClassDeclaration(UnresolvedClassDeclaration object) {
				return createUnresolvedClassDeclarationAdapter();
			}
			@Override
			public Adapter caseUnresolvedEnumDeclaration(UnresolvedEnumDeclaration object) {
				return createUnresolvedEnumDeclarationAdapter();
			}
			@Override
			public Adapter caseUnresolvedInterfaceDeclaration(UnresolvedInterfaceDeclaration object) {
				return createUnresolvedInterfaceDeclarationAdapter();
			}
			@Override
			public Adapter caseUnresolvedLabeledStatement(UnresolvedLabeledStatement object) {
				return createUnresolvedLabeledStatementAdapter();
			}
			@Override
			public Adapter caseUnresolvedMethodDeclaration(UnresolvedMethodDeclaration object) {
				return createUnresolvedMethodDeclarationAdapter();
			}
			@Override
			public Adapter caseUnresolvedSingleVariableDeclaration(UnresolvedSingleVariableDeclaration object) {
				return createUnresolvedSingleVariableDeclarationAdapter();
			}
			@Override
			public Adapter caseUnresolvedType(UnresolvedType object) {
				return createUnresolvedTypeAdapter();
			}
			@Override
			public Adapter caseUnresolvedTypeDeclaration(UnresolvedTypeDeclaration object) {
				return createUnresolvedTypeDeclarationAdapter();
			}
			@Override
			public Adapter caseUnresolvedVariableDeclarationFragment(UnresolvedVariableDeclarationFragment object) {
				return createUnresolvedVariableDeclarationFragmentAdapter();
			}
			@Override
			public Adapter caseVariableDeclaration(VariableDeclaration object) {
				return createVariableDeclarationAdapter();
			}
			@Override
			public Adapter caseVariableDeclarationExpression(VariableDeclarationExpression object) {
				return createVariableDeclarationExpressionAdapter();
			}
			@Override
			public Adapter caseVariableDeclarationFragment(VariableDeclarationFragment object) {
				return createVariableDeclarationFragmentAdapter();
			}
			@Override
			public Adapter caseVariableDeclarationStatement(VariableDeclarationStatement object) {
				return createVariableDeclarationStatementAdapter();
			}
			@Override
			public Adapter caseWildCardType(WildCardType object) {
				return createWildCardTypeAdapter();
			}
			@Override
			public Adapter caseWhileStatement(WhileStatement object) {
				return createWhileStatementAdapter();
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
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.AbstractMethodDeclaration <em>Abstract Method Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.AbstractMethodDeclaration
	 * @generated
	 */
	public Adapter createAbstractMethodDeclarationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.AbstractMethodInvocation <em>Abstract Method Invocation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.AbstractMethodInvocation
	 * @generated
	 */
	public Adapter createAbstractMethodInvocationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.AbstractTypeDeclaration <em>Abstract Type Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.AbstractTypeDeclaration
	 * @generated
	 */
	public Adapter createAbstractTypeDeclarationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.AbstractTypeQualifiedExpression <em>Abstract Type Qualified Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.AbstractTypeQualifiedExpression
	 * @generated
	 */
	public Adapter createAbstractTypeQualifiedExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.AbstractVariablesContainer <em>Abstract Variables Container</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.AbstractVariablesContainer
	 * @generated
	 */
	public Adapter createAbstractVariablesContainerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.Annotation <em>Annotation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.Annotation
	 * @generated
	 */
	public Adapter createAnnotationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.Archive <em>Archive</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.Archive
	 * @generated
	 */
	public Adapter createArchiveAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.AssertStatement <em>Assert Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.AssertStatement
	 * @generated
	 */
	public Adapter createAssertStatementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.ASTNode <em>AST Node</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.ASTNode
	 * @generated
	 */
	public Adapter createASTNodeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.AnnotationMemberValuePair <em>Annotation Member Value Pair</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.AnnotationMemberValuePair
	 * @generated
	 */
	public Adapter createAnnotationMemberValuePairAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.AnnotationTypeDeclaration <em>Annotation Type Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.AnnotationTypeDeclaration
	 * @generated
	 */
	public Adapter createAnnotationTypeDeclarationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.AnnotationTypeMemberDeclaration <em>Annotation Type Member Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.AnnotationTypeMemberDeclaration
	 * @generated
	 */
	public Adapter createAnnotationTypeMemberDeclarationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.AnonymousClassDeclaration <em>Anonymous Class Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.AnonymousClassDeclaration
	 * @generated
	 */
	public Adapter createAnonymousClassDeclarationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.ArrayAccess <em>Array Access</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.ArrayAccess
	 * @generated
	 */
	public Adapter createArrayAccessAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.ArrayCreation <em>Array Creation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.ArrayCreation
	 * @generated
	 */
	public Adapter createArrayCreationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.ArrayInitializer <em>Array Initializer</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.ArrayInitializer
	 * @generated
	 */
	public Adapter createArrayInitializerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.ArrayLengthAccess <em>Array Length Access</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.ArrayLengthAccess
	 * @generated
	 */
	public Adapter createArrayLengthAccessAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.ArrayType <em>Array Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.ArrayType
	 * @generated
	 */
	public Adapter createArrayTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.Assignment <em>Assignment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.Assignment
	 * @generated
	 */
	public Adapter createAssignmentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.BodyDeclaration <em>Body Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.BodyDeclaration
	 * @generated
	 */
	public Adapter createBodyDeclarationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.BooleanLiteral <em>Boolean Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.BooleanLiteral
	 * @generated
	 */
	public Adapter createBooleanLiteralAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.BlockComment <em>Block Comment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.BlockComment
	 * @generated
	 */
	public Adapter createBlockCommentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.Block <em>Block</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.Block
	 * @generated
	 */
	public Adapter createBlockAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.BreakStatement <em>Break Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.BreakStatement
	 * @generated
	 */
	public Adapter createBreakStatementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.CastExpression <em>Cast Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.CastExpression
	 * @generated
	 */
	public Adapter createCastExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.CatchClause <em>Catch Clause</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.CatchClause
	 * @generated
	 */
	public Adapter createCatchClauseAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.CharacterLiteral <em>Character Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.CharacterLiteral
	 * @generated
	 */
	public Adapter createCharacterLiteralAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.ClassFile <em>Class File</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.ClassFile
	 * @generated
	 */
	public Adapter createClassFileAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.ClassInstanceCreation <em>Class Instance Creation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.ClassInstanceCreation
	 * @generated
	 */
	public Adapter createClassInstanceCreationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.ConstructorDeclaration <em>Constructor Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.ConstructorDeclaration
	 * @generated
	 */
	public Adapter createConstructorDeclarationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.ConditionalExpression <em>Conditional Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.ConditionalExpression
	 * @generated
	 */
	public Adapter createConditionalExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.ConstructorInvocation <em>Constructor Invocation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.ConstructorInvocation
	 * @generated
	 */
	public Adapter createConstructorInvocationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.ClassDeclaration <em>Class Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.ClassDeclaration
	 * @generated
	 */
	public Adapter createClassDeclarationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.Comment <em>Comment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.Comment
	 * @generated
	 */
	public Adapter createCommentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.CompilationUnit <em>Compilation Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.CompilationUnit
	 * @generated
	 */
	public Adapter createCompilationUnitAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.ContinueStatement <em>Continue Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.ContinueStatement
	 * @generated
	 */
	public Adapter createContinueStatementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.DoStatement <em>Do Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.DoStatement
	 * @generated
	 */
	public Adapter createDoStatementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.EmptyStatement <em>Empty Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.EmptyStatement
	 * @generated
	 */
	public Adapter createEmptyStatementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.EnhancedForStatement <em>Enhanced For Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.EnhancedForStatement
	 * @generated
	 */
	public Adapter createEnhancedForStatementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.EnumConstantDeclaration <em>Enum Constant Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.EnumConstantDeclaration
	 * @generated
	 */
	public Adapter createEnumConstantDeclarationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.EnumDeclaration <em>Enum Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.EnumDeclaration
	 * @generated
	 */
	public Adapter createEnumDeclarationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.Expression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.Expression
	 * @generated
	 */
	public Adapter createExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.ExpressionStatement <em>Expression Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.ExpressionStatement
	 * @generated
	 */
	public Adapter createExpressionStatementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.FieldAccess <em>Field Access</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.FieldAccess
	 * @generated
	 */
	public Adapter createFieldAccessAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.FieldDeclaration <em>Field Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.FieldDeclaration
	 * @generated
	 */
	public Adapter createFieldDeclarationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.ForStatement <em>For Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.ForStatement
	 * @generated
	 */
	public Adapter createForStatementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.IfStatement <em>If Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.IfStatement
	 * @generated
	 */
	public Adapter createIfStatementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.ImportDeclaration <em>Import Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.ImportDeclaration
	 * @generated
	 */
	public Adapter createImportDeclarationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.InfixExpression <em>Infix Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.InfixExpression
	 * @generated
	 */
	public Adapter createInfixExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.Initializer <em>Initializer</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.Initializer
	 * @generated
	 */
	public Adapter createInitializerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.InstanceofExpression <em>Instanceof Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.InstanceofExpression
	 * @generated
	 */
	public Adapter createInstanceofExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.InterfaceDeclaration <em>Interface Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.InterfaceDeclaration
	 * @generated
	 */
	public Adapter createInterfaceDeclarationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.Javadoc <em>Javadoc</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.Javadoc
	 * @generated
	 */
	public Adapter createJavadocAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.LabeledStatement <em>Labeled Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.LabeledStatement
	 * @generated
	 */
	public Adapter createLabeledStatementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.LineComment <em>Line Comment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.LineComment
	 * @generated
	 */
	public Adapter createLineCommentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.Manifest <em>Manifest</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.Manifest
	 * @generated
	 */
	public Adapter createManifestAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.ManifestAttribute <em>Manifest Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.ManifestAttribute
	 * @generated
	 */
	public Adapter createManifestAttributeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.ManifestEntry <em>Manifest Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.ManifestEntry
	 * @generated
	 */
	public Adapter createManifestEntryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.MemberRef <em>Member Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.MemberRef
	 * @generated
	 */
	public Adapter createMemberRefAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.MethodDeclaration <em>Method Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.MethodDeclaration
	 * @generated
	 */
	public Adapter createMethodDeclarationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.MethodInvocation <em>Method Invocation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.MethodInvocation
	 * @generated
	 */
	public Adapter createMethodInvocationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.MethodRef <em>Method Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.MethodRef
	 * @generated
	 */
	public Adapter createMethodRefAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.MethodRefParameter <em>Method Ref Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.MethodRefParameter
	 * @generated
	 */
	public Adapter createMethodRefParameterAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.Model <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.Model
	 * @generated
	 */
	public Adapter createModelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.Modifier <em>Modifier</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.Modifier
	 * @generated
	 */
	public Adapter createModifierAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.NamedElement <em>Named Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.NamedElement
	 * @generated
	 */
	public Adapter createNamedElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.NamespaceAccess <em>Namespace Access</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.NamespaceAccess
	 * @generated
	 */
	public Adapter createNamespaceAccessAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.NumberLiteral <em>Number Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.NumberLiteral
	 * @generated
	 */
	public Adapter createNumberLiteralAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.NullLiteral <em>Null Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.NullLiteral
	 * @generated
	 */
	public Adapter createNullLiteralAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.Package <em>Package</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.Package
	 * @generated
	 */
	public Adapter createPackageAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.PackageAccess <em>Package Access</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.PackageAccess
	 * @generated
	 */
	public Adapter createPackageAccessAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.ParameterizedType <em>Parameterized Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.ParameterizedType
	 * @generated
	 */
	public Adapter createParameterizedTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.ParenthesizedExpression <em>Parenthesized Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.ParenthesizedExpression
	 * @generated
	 */
	public Adapter createParenthesizedExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.PostfixExpression <em>Postfix Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.PostfixExpression
	 * @generated
	 */
	public Adapter createPostfixExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.PrefixExpression <em>Prefix Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.PrefixExpression
	 * @generated
	 */
	public Adapter createPrefixExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.PrimitiveType <em>Primitive Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.PrimitiveType
	 * @generated
	 */
	public Adapter createPrimitiveTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.PrimitiveTypeBoolean <em>Primitive Type Boolean</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.PrimitiveTypeBoolean
	 * @generated
	 */
	public Adapter createPrimitiveTypeBooleanAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.PrimitiveTypeByte <em>Primitive Type Byte</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.PrimitiveTypeByte
	 * @generated
	 */
	public Adapter createPrimitiveTypeByteAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.PrimitiveTypeChar <em>Primitive Type Char</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.PrimitiveTypeChar
	 * @generated
	 */
	public Adapter createPrimitiveTypeCharAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.PrimitiveTypeDouble <em>Primitive Type Double</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.PrimitiveTypeDouble
	 * @generated
	 */
	public Adapter createPrimitiveTypeDoubleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.PrimitiveTypeShort <em>Primitive Type Short</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.PrimitiveTypeShort
	 * @generated
	 */
	public Adapter createPrimitiveTypeShortAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.PrimitiveTypeFloat <em>Primitive Type Float</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.PrimitiveTypeFloat
	 * @generated
	 */
	public Adapter createPrimitiveTypeFloatAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.PrimitiveTypeInt <em>Primitive Type Int</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.PrimitiveTypeInt
	 * @generated
	 */
	public Adapter createPrimitiveTypeIntAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.PrimitiveTypeLong <em>Primitive Type Long</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.PrimitiveTypeLong
	 * @generated
	 */
	public Adapter createPrimitiveTypeLongAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.PrimitiveTypeVoid <em>Primitive Type Void</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.PrimitiveTypeVoid
	 * @generated
	 */
	public Adapter createPrimitiveTypeVoidAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.ReturnStatement <em>Return Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.ReturnStatement
	 * @generated
	 */
	public Adapter createReturnStatementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.SingleVariableAccess <em>Single Variable Access</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.SingleVariableAccess
	 * @generated
	 */
	public Adapter createSingleVariableAccessAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.SingleVariableDeclaration <em>Single Variable Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.SingleVariableDeclaration
	 * @generated
	 */
	public Adapter createSingleVariableDeclarationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.Statement <em>Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.Statement
	 * @generated
	 */
	public Adapter createStatementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.StringLiteral <em>String Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.StringLiteral
	 * @generated
	 */
	public Adapter createStringLiteralAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.SuperConstructorInvocation <em>Super Constructor Invocation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.SuperConstructorInvocation
	 * @generated
	 */
	public Adapter createSuperConstructorInvocationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.SuperFieldAccess <em>Super Field Access</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.SuperFieldAccess
	 * @generated
	 */
	public Adapter createSuperFieldAccessAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.SuperMethodInvocation <em>Super Method Invocation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.SuperMethodInvocation
	 * @generated
	 */
	public Adapter createSuperMethodInvocationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.SwitchCase <em>Switch Case</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.SwitchCase
	 * @generated
	 */
	public Adapter createSwitchCaseAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.SwitchStatement <em>Switch Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.SwitchStatement
	 * @generated
	 */
	public Adapter createSwitchStatementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.SynchronizedStatement <em>Synchronized Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.SynchronizedStatement
	 * @generated
	 */
	public Adapter createSynchronizedStatementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.TagElement <em>Tag Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.TagElement
	 * @generated
	 */
	public Adapter createTagElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.TextElement <em>Text Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.TextElement
	 * @generated
	 */
	public Adapter createTextElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.ThisExpression <em>This Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.ThisExpression
	 * @generated
	 */
	public Adapter createThisExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.ThrowStatement <em>Throw Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.ThrowStatement
	 * @generated
	 */
	public Adapter createThrowStatementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.TryStatement <em>Try Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.TryStatement
	 * @generated
	 */
	public Adapter createTryStatementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.Type <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.Type
	 * @generated
	 */
	public Adapter createTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.TypeAccess <em>Type Access</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.TypeAccess
	 * @generated
	 */
	public Adapter createTypeAccessAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.TypeDeclaration <em>Type Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.TypeDeclaration
	 * @generated
	 */
	public Adapter createTypeDeclarationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.TypeDeclarationStatement <em>Type Declaration Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.TypeDeclarationStatement
	 * @generated
	 */
	public Adapter createTypeDeclarationStatementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.TypeLiteral <em>Type Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.TypeLiteral
	 * @generated
	 */
	public Adapter createTypeLiteralAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.TypeParameter <em>Type Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.TypeParameter
	 * @generated
	 */
	public Adapter createTypeParameterAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.UnresolvedItem <em>Unresolved Item</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.UnresolvedItem
	 * @generated
	 */
	public Adapter createUnresolvedItemAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.UnresolvedItemAccess <em>Unresolved Item Access</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.UnresolvedItemAccess
	 * @generated
	 */
	public Adapter createUnresolvedItemAccessAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.UnresolvedAnnotationDeclaration <em>Unresolved Annotation Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.UnresolvedAnnotationDeclaration
	 * @generated
	 */
	public Adapter createUnresolvedAnnotationDeclarationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.UnresolvedAnnotationTypeMemberDeclaration <em>Unresolved Annotation Type Member Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.UnresolvedAnnotationTypeMemberDeclaration
	 * @generated
	 */
	public Adapter createUnresolvedAnnotationTypeMemberDeclarationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.UnresolvedClassDeclaration <em>Unresolved Class Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.UnresolvedClassDeclaration
	 * @generated
	 */
	public Adapter createUnresolvedClassDeclarationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.UnresolvedEnumDeclaration <em>Unresolved Enum Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.UnresolvedEnumDeclaration
	 * @generated
	 */
	public Adapter createUnresolvedEnumDeclarationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.UnresolvedInterfaceDeclaration <em>Unresolved Interface Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.UnresolvedInterfaceDeclaration
	 * @generated
	 */
	public Adapter createUnresolvedInterfaceDeclarationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.UnresolvedLabeledStatement <em>Unresolved Labeled Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.UnresolvedLabeledStatement
	 * @generated
	 */
	public Adapter createUnresolvedLabeledStatementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.UnresolvedMethodDeclaration <em>Unresolved Method Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.UnresolvedMethodDeclaration
	 * @generated
	 */
	public Adapter createUnresolvedMethodDeclarationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.UnresolvedSingleVariableDeclaration <em>Unresolved Single Variable Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.UnresolvedSingleVariableDeclaration
	 * @generated
	 */
	public Adapter createUnresolvedSingleVariableDeclarationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.UnresolvedType <em>Unresolved Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.UnresolvedType
	 * @generated
	 */
	public Adapter createUnresolvedTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.UnresolvedTypeDeclaration <em>Unresolved Type Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.UnresolvedTypeDeclaration
	 * @generated
	 */
	public Adapter createUnresolvedTypeDeclarationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.UnresolvedVariableDeclarationFragment <em>Unresolved Variable Declaration Fragment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.UnresolvedVariableDeclarationFragment
	 * @generated
	 */
	public Adapter createUnresolvedVariableDeclarationFragmentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.VariableDeclaration <em>Variable Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.VariableDeclaration
	 * @generated
	 */
	public Adapter createVariableDeclarationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.VariableDeclarationExpression <em>Variable Declaration Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.VariableDeclarationExpression
	 * @generated
	 */
	public Adapter createVariableDeclarationExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.VariableDeclarationFragment <em>Variable Declaration Fragment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.VariableDeclarationFragment
	 * @generated
	 */
	public Adapter createVariableDeclarationFragmentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.VariableDeclarationStatement <em>Variable Declaration Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.VariableDeclarationStatement
	 * @generated
	 */
	public Adapter createVariableDeclarationStatementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.WildCardType <em>Wild Card Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.WildCardType
	 * @generated
	 */
	public Adapter createWildCardTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.gmt.modisco.java.cdo.java.WhileStatement <em>While Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.gmt.modisco.java.cdo.java.WhileStatement
	 * @generated
	 */
	public Adapter createWhileStatementAdapter() {
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

} //JavaAdapterFactory
