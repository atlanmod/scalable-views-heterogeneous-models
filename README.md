# Scalable Views on Heterogeneous Models

Benchmarks for combining EMF Views, NeoEMF, CDO and Epsilon models on a
MegaM@Rt2 use case.

*Note*: if you are looking for the source for the benchmarks of our MODELS 2018
paper [*Towards Scalable Model Views on Heterogeneous Model
Resources*][models2018-paper], they are in the [models2018
branch][models2018-branch].

## Running the benchmarks

First clone this repository and its dependencies, which come as git submodules:

```
git clone https://github.com/atlanmod/scalable-views-heterogeneous-models.git
git submodule update --init
```

**Warning**: the [SOM-Research/NeoEMF][som-neo] dependency weighs 3GB.

Once the pull is complete, you should add all the projects in Eclipse (4.7 and
up should work).  You may need additional dependencies:

- Epsilon
- CDO
- UML
- RMF
- MoDisco

You also need to build the NeoEMF update site, as they are Maven projects.  See
the [build instructions](https://github.com/SOM-Research/NeoEMF#build).

Once the build is complete, simply run `benchmarks/src/RunAll.java` as a Java
program.  This will run all benchmarks using the default parameters:

```
sizes = {10, 100, 1000, 10000, 100000, 1000000};
warmups = 5;
measures = 10;
```

To customize the parameters or run individual benchmarks, continue reading.

### Benchmark parameters
```
Usage: RunAll SIZES WARMUPS MEASURES
```

#### Sizes
The `SIZES` parameters is an array of integers which specify which sizes of
models should be generated and used.  E.g.:

```
RunAll [10,20,30]
```

This would create three trace models of size 10, 20, and 30 and run the
benchmarks on them.

The default value of SIZES is `[10,100,1000,10000,100000,1000000]`.  Larger
models take considerably more time to generate, and will also take more time to
run in the benchmarks.

#### Warmups and measures
To get multiple data points for the benchmarks, you can ask for multiple
measures.  E.g.:

```
RunAll [10] 1 5
```

Will run each benchmark 6 times (1 warmup round + 5 measures) for each specified
model size.

Warmup wounds are useful to avoid noise in the data due to JIT optimizations.
Warmup rounds will execute the exact same benchmark code, but their time will be
discarded in the results.

#### JVM Arguments
You may need to tweak the arguments passed to the JVM.  For example, by using
the concurrent mark and sweep garbage collector (`-XX:+UseConcMarkSweepGC`) or
increasing the max heap size to 8G.  If you are running into out of memory
exceptions, you want want to increase that even further.

```
-XX:+UseConcMarkSweepGC -Xmx16g
```

### Running individual benchmarks
`RunAll` will, as the name implies, run all the benchmarks.  You can also run
the 3 benchmarks individually by running them as Java programs:

```
├── LoadView.java
├── OCLQuery.java
├── RunEOL.java
```

- `LoadView` loads all views and enumerate their elements.
- `OCLQuery` runs 3 OCL queries on views.
- `RunEOL` runs 3 EOL queries on views.

These program all understand the `sizes`, `warmups` and `measures` arguments,
but may also have extra arguments (e.g., the RunEOL benchmark can take an EOL
program to run).

### What else is in there?


```
├── all-ryzen                 # Raw results for a full run
├── benchmarks.org            # Compiled results with additional graphs
└── benchmarks
     ├── metamodels           # Metamodels used by views
     ├── models               # Models used by views
     ├── queries              # EOL queries
     ├── results-to-table.el  # Emacs Lisp script to parse results
     ├── src
     │   ├── Creator.java             # Create trace models for benchmarking
     │   ├── FastExtentMap.java       # Optimize the allInstances() OCL call
     │   ├── LoadView.java            # Bench loading the views
     │   ├── OCLQuery.java            # Bench running OCL queries on the views
     │   ├── RunAll.java              # Create trace models and run all benchmarks
     │   ├── RunEOL.java              # Bench running EOL queries on the views
     │   ├── Util.java                # Timing stuff, loading resources used by benchmarks
     │   ├── VirtualLinkMatcher.java  # Optimize loading of views with large traces
     │   └── log4j2.xml               # Log4j configuration (reduce NeoEMF verbosity)
     └── views            # View files and trace models generated by Creator
```

## Copying
You are free to redistribute and modify all the code and models under the terms
of the GNU General Public License as by the Free Software Foundation, either
version 3 of the License, or (at your option) any later version.

See [LICENSE](LICENSE).


[som-neo]: https://github.com/SOM-Research/NeoEMF/
[models2018-paper]: https://hal.archives-ouvertes.fr/hal-01845976
[models2018-branch]: https://github.com/atlanmod/scalable-views-heterogeneous-models/tree/models2018
