
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

  // Cannot use java.util.Function because of the Exception
  public interface Thunk<T> {
    T apply() throws Exception;
  }

  static <T> T time(String task, Thunk<T> f) throws Exception {
    Instant start = Instant.now();
    T t = f.apply();
    Instant end = Instant.now();
    System.out.printf("%s [%dms]\n", task, ChronoUnit.MILLIS.between(start, end));
    return t;
  }

  static void benchQuery(URI viewPath, URI programPath, boolean forceEMFEMC) throws Exception {
    // Parse EOL program
    EolModule module = time("Parse EOL", () -> {
      EolModule m = new EolModule();
      m.parse(new File(programPath.toFileString()));
      if (m.getParseProblems().size() > 0) {
        System.err.println("Parse errors occured...");
        for (ParseProblem problem : m.getParseProblems()) {
          System.err.println(problem.toString());
        }
        throw new Exception("Error in parsing ECL file.  See stderr for details");
      }
      m.getContext().setOperationFactory(new EolOperationFactory());
      return m;
    });

    Resource view;
    view = time("Load view", () -> Util.loadResource(viewPath));

    // Add view using the EMF Views EMC
    EMFViewsModel m = new EMFViewsModel(view);
    m.setForceDefaultEMFDriver(forceEMFEMC);
    m.setName("VIEW");
    m.load();
    module.getContext().getModelRepository().addModel(m);

    // Execute EOL
    try {
      System.out.print("Result: ");
      time("EOL execute query", () -> module.execute());
    } finally {
      m.disposeModel();
    }
  }

  static void benchQueryOnAllModels(URI programPath, boolean forceEMFEMC, int[] sizes, int warmups, int measures) throws Exception {
    String queryName = programPath.lastSegment();
    String fee = forceEMFEMC ? "with EMF EMC" : "";

    for (int s : sizes) {
      Util.bench(String.format("EOL query %s for XMI view of size %d %s", queryName, s, fee), () -> {
        benchQuery(Util.resourceURI("/views/java-trace/%d.eview", s), programPath, forceEMFEMC);
      }, warmups, measures);

      Util.bench(String.format("EOL query %s for NeoEMF view of size %d %s", queryName, s, fee), () -> {
        benchQuery(Util.resourceURI("/views/neoemf-trace/%d.eview", s), programPath, forceEMFEMC);
      }, warmups, measures);
    }
  }

  static void benchAllQueries(int[] sizes, int warmups, int measures) throws Exception {
    setup();
    final URI[] queries = { pathURI("queries/allInstances.eol"),
                            pathURI("queries/reqToTraces.eol"),
                            pathURI("queries/traceToReqs.eol")
                            };

    for (URI q : queries) {
      benchQueryOnAllModels(q, false, sizes, warmups, measures);
      benchQueryOnAllModels(q, true, sizes, warmups, measures);
    }

  }

  static void setup() {
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

    setup();
    benchQueryOnAllModels(programPath, forceEMFEMC, sizes, warmups, measures);
  }
}
