public class RunAll {
  public static void main(String[] args) throws Exception {
    int[] sizes = {10};
    int warmups = 0;
    int measures = 1;

    if (args.length == 0) {
      // Use default values
    }
    else if (args.length != 3) {
      sizes = Util.parseIntArray(args[0]);
      warmups = Integer.parseInt(args[1]);
      measures = Integer.parseInt(args[2]);
    }
    else {
      System.err.println("Usage: RunAll SIZES WARMUPS MEASURES");
      System.exit(1);
    }

    // Creation only need to run once... but isn't a bottleneck
    //Creator.createModels(sizes);
    //LoadView.loadAll(sizes, warmups, measures);
    //OCLQuery.benchAllQueries(sizes, warmups, measures);
    RunEOL.benchAllQueries(sizes, warmups, measures);
  }
}
