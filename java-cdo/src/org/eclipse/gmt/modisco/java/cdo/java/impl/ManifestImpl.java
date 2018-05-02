/**
 */
package org.eclipse.gmt.modisco.java.cdo.java.impl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.internal.cdo.CDOObjectImpl;

import org.eclipse.gmt.modisco.java.cdo.java.JavaPackage;
import org.eclipse.gmt.modisco.java.cdo.java.Manifest;
import org.eclipse.gmt.modisco.java.cdo.java.ManifestAttribute;
import org.eclipse.gmt.modisco.java.cdo.java.ManifestEntry;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Manifest</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gmt.modisco.java.cdo.java.impl.ManifestImpl#getMainAttributes <em>Main Attributes</em>}</li>
 *   <li>{@link org.eclipse.gmt.modisco.java.cdo.java.impl.ManifestImpl#getEntryAttributes <em>Entry Attributes</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ManifestImpl extends CDOObjectImpl implements Manifest {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ManifestImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JavaPackage.eINSTANCE.getManifest();
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
	@SuppressWarnings("unchecked")
	public EList<ManifestAttribute> getMainAttributes() {
		return (EList<ManifestAttribute>)eGet(JavaPackage.eINSTANCE.getManifest_MainAttributes(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<ManifestEntry> getEntryAttributes() {
		return (EList<ManifestEntry>)eGet(JavaPackage.eINSTANCE.getManifest_EntryAttributes(), true);
	}

} //ManifestImpl
