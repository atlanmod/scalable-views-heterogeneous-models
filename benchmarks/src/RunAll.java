public class RunAll {
  public static void main(String[] args) throws Exception {
    int[] sizes = {10, 100, 1000, 10000, 100000, 1000000};
    int warmups = 5;
    int measures = 10;

    if (args.length == 0) {
      // Use default values
    }
    else if (args.length != 3) {
      System.err.println("Usage: RunAll SIZES WARMUPS MEASURES");
      System.exit(1);
    }
    else {
      sizes = Util.parseIntArray(args[0]);
      warmups = Integer.parseInt(args[1]);
      measures = Integer.parseInt(args[2]);
    }

    // Creation only need to run once... but for some reason the NeoEMF models
    // can get corrupted. Better recreate fresh on each run.
    Creator.createModels(sizes);
    LoadView.loadViews(sizes, warmups, measures);
    OCLQuery.benchAllQueries(sizes, warmups, measures);
    RunEOL.benchAllQueries(sizes, warmups, measures);
  }
}
