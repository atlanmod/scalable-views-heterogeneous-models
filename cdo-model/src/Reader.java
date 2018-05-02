import java.io.File;
import java.util.Collections;
import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmt.modisco.java.cdo.java.JavaPackage;

import cdobackend.CDOBackend;

public class Reader {

	public static void main(String[] args) throws Exception {
		JavaPackage.eINSTANCE.eClass();

		CDOBackend backend = new CDOBackend();

		Resource cdoResource = backend.getResource(new File("model/petstore-java.cdo"), "res1");
		cdoResource.load(Collections.emptyMap());

		Iterator<EObject> it = cdoResource.getAllContents();
		int count = 0;
		while(it.hasNext()) {
			it.next();
			count++;
		}

		System.out.println("CDO resource contains " + count + " elements");

		backend.close();
	}

}
