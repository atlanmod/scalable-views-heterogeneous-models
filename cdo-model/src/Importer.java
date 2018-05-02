import java.io.File;
import java.util.Collections;
import java.util.Iterator;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.gmt.modisco.java.cdo.java.JavaPackage;

import cdobackend.CDOBackend;

public class Importer {

	public static void main(String[] args) throws Exception {
		JavaPackage.eINSTANCE.eClass();

		ResourceSet rSet = new ResourceSetImpl();
		rSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
		Resource xmiResource = rSet.getResource(URI.createURI("model/petstore-java.xmi"), true);
		Iterator<EObject> it = xmiResource.getAllContents();
		int count = 0;
		while(it.hasNext()) {
			it.next();
			count++;
		}
		System.out.println("Input model contains " + count + " elements");

		CDOBackend backend = new CDOBackend();
		Resource cdoResource = backend.createResource(new File("model/petstore-java.cdo"), "res1");
		cdoResource.getContents().addAll(EcoreUtil.copyAll(xmiResource.getContents()));

		cdoResource.save(Collections.emptyMap());

		it = cdoResource.getAllContents();
		count = 0;
		while(it.hasNext()) {
			it.next();
			count++;
		}
		System.out.println("CDO model contains " + count + " elements");

		backend.close();
	}

}
