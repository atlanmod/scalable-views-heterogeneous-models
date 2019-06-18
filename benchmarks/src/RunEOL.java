
import java.io.File;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.epsilon.common.parse.problem.ParseProblem;
import org.eclipse.epsilon.emc.emfviews.EMFViewsModel;
import org.eclipse.epsilon.eol.EolModule;
import org.eclipse.epsilon.eol.execute.operations.EolOperationFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.internal.resource.UMLResourceFactoryImpl;

import org.atlanmod.emfviews.core.EmfViewsFactory;
import org.atlanmod.emfviews.core.EpsilonResource;
import org.atlanmod.emfviews.virtuallinks.VirtualLinksPackage;

import trace.TracePackage;
import traceneoemf.TraceneoemfPackage;
import virtuallinksneoemf.VirtuallinksneoemfPackage;

public class RunEOL {
  static URI here = URI.createFileURI(new File(".").getAbsolutePath());

  static URI pathURI(String relativePath) {
    return URI.createFileURI(relativePath).resolve(here);
  }

  public interface Thunk {
    void apply() throws Exception;
  }

  static void time(String task, Thunk f) throws Exception {
    Instant start = Instant.now();
    f.apply();
    Instant end = Instant.now();
    System.out.printf("%s [%dms]\n", task, ChronoUnit.MILLIS.between(start, end));
  }

  static void benchQuery(URI viewPath, URI programPath, boolean forceEMFEMC) throws Exception {
    // Parse EOL program
    EolModule module = new EolModule();
    module.parse(new File(programPath.toFileString()));
    if (module.getParseProblems().size() > 0) {
      System.err.println("Parse errors occured...");
      for (ParseProblem problem : module.getParseProblems()) {
        System.err.println(problem.toString());
      }
      throw new Exception("Error in parsing ECL file.  See stderr for details");
    }
    module.getContext().setOperationFactory(new EolOperationFactory());

    // Add view using the EMF Views EMC
    EMFViewsModel m = new EMFViewsModel();
    m.setForceDefaultEMFDriver(forceEMFEMC);
    m.setName("VIEW");
    m.setModelFileUri(viewPath);
    time("Load view", () -> m.load());
    module.getContext().getModelRepository().addModel(m);

    // Execute EOL
    try {
      time("EOL execute query", () -> module.execute());
    } finally {
      m.disposeModel();
    }
  }

  public static void main(String[] args) throws Exception {

    // Default arguments
    boolean forceEMFEMC = false;
    int[] sizes = {};
    URI programPath = pathURI("queries/allInstances.eol");
    int warmups = 0;
    int measures = 1;

    // Parse command line options
    {
      Options opts = new Options();
      opts.addOption("u", false, "force default (slower) EMF EMC");

      CommandLineParser parser = new BasicParser();
      CommandLine cmd;

      final String usage = "run-eol [-u] SIZES EOL WARMUPS MEASURES";

      try {
        cmd = parser.parse(opts, args);
        if (cmd.getArgList().size() != 4) {
          System.err.printf("Usage: %s\n", usage);
          System.exit(1);
        }

        forceEMFEMC = cmd.hasOption("u");
        sizes = Util.parseIntArray(cmd.getArgs()[0]);
        programPath = pathURI(cmd.getArgs()[1]);
        warmups = Integer.parseInt(cmd.getArgs()[2]);
        measures = Integer.parseInt(cmd.getArgs()[3]);
      } catch (ParseException e) {
        System.out.println(e.getMessage());
        (new HelpFormatter()).printHelp(usage, opts);
        System.exit(1);
      }
    }

    // Init Ecore packages that may be used by viewpoints
    UMLPackage.eINSTANCE.eClass();
    org.eclipse.gmt.modisco.java.emf.JavaPackage.eINSTANCE.eClass();
    org.eclipse.gmt.modisco.java.cdo.java.JavaPackage.eINSTANCE.eClass();
    TracePackage.eINSTANCE.eClass();
    TraceneoemfPackage.eINSTANCE.eClass();
    VirtualLinksPackage.eINSTANCE.eClass();
    VirtuallinksneoemfPackage.eINSTANCE.eClass();

    // Register model file extensions to be opened as EMF models
    Map<String, Object> ext2Fact = Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap();

    ext2Fact.put("ecore", new EcoreResourceFactoryImpl());
    ext2Fact.put("xmi", new XMIResourceFactoryImpl());
    Resource.Factory epsRF = new Resource.Factory() {
      @Override
      public Resource createResource(URI uri) {
        return new EpsilonResource(uri);
      }
    };
    ext2Fact.put("csv", epsRF);
    ext2Fact.put("uml", new UMLResourceFactoryImpl());
    ext2Fact.put("eview", new EmfViewsFactory());
    ext2Fact.put("eviewpoint", new EmfViewsFactory());

    // Dumb dance to have final args passed to lambda
    final URI p = programPath;
    final boolean f = forceEMFEMC;

    for (int s : sizes) {
      Util.bench(String.format("EOL query %s for XMI view of size %d", p, s), () -> {
        benchQuery(Util.resourceURI("/views/java-trace/%d.eview", s), p, f);
      }, warmups, measures);

      Util.bench(String.format("EOL query %s for NeoEMF view of size %d", p, s), () -> {
        benchQuery(Util.resourceURI("/views/neoemf-trace/%d.eview", s), p, f);
      }, warmups, measures);
    }
  }
}
