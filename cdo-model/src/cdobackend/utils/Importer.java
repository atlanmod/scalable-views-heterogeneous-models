package cdobackend.utils;
import java.io.File;
import java.util.Collections;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
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

  public static void importCDO(URI javaModel, URI cdoModel) throws Exception {
    System.out.println("### Import CDO Model");

    JavaPackage.eINSTANCE.eClass();

    ResourceSet rSet = new ResourceSetImpl();
    rSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi",
      new XMIResourceFactoryImpl());
    Resource xmiResource = rSet.getResource(javaModel, true);
    Iterator<EObject> it = xmiResource.getAllContents();
    int count = 0;
    while (it.hasNext()) {
      it.next();
      count++;
    }
    System.out.println("Input model contains " + count + " elements");

    // Delete existing resource
    FileUtils.deleteDirectory(new File(cdoModel.toFileString()));

    CDOBackend backend = new CDOBackend();
    Resource cdoResource = backend.createResource(new File(cdoModel.toFileString()), "res1");
    cdoResource.getContents().addAll(EcoreUtil.copyAll(xmiResource.getContents()));

    cdoResource.save(Collections.emptyMap());

    it = cdoResource.getAllContents();
    count = 0;
    while (it.hasNext()) {
      it.next();
      count++;
    }
    System.out.println("CDO model contains " + count + " elements");

    backend.close();
  }

  public static void main(String[] args) throws Exception {
    importCDO(URI.createURI("model/petstore-java.xmi"), URI.createURI("model/petstore-java.cdo"));
  }

}
