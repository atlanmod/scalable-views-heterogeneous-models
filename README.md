# Scalable Views on Heterogeneous Models

Benchmarks for combining EMF Views, NeoEMF and CDO on a MegaM@Rt2 use case.

## Running the benchmarks

The easiest option is to use our pre-built [Docker image][docker-image] which
contains everything you need to run the benchmarks.

If you want to change the code of the benchmarks, or are using other versions of
EMF Views or NeoEMF, then you'll need to follow the manual instructions.

## Running the benchmarks with the Docker image
With Docker installed, you only have to fetch the pre-built image.  In order to
run the benchmarks, you first need to create the models.  Note: if you use the
defaults settings, you will need around **21GB of free space** on disk in order
to generate the models.

0. First, create an empty directory that will hold the models and views:
   ```
   mkdir workspace
   cd workspace
   ```

1. To create the models, simply run:
   ```
   docker run -v `pwd`:/root/workspace atlanmod/scalable-views create-models
   ```
   This will mount the current directory as a volume where the container expects
   to write its files.

   Creating the models can take up to 20 minutes with the default settings (see
   the [Sizes](#sizes) parameter).

2. Then, to create the weaving models used by the views:
   ```
   docker run -v `pwd`:/root/workspace atlanmod/scalable-views create-weaving-models
   ```
   This can take around 30 minutes with default sizes.

After that, you can run the benchmarks:

3. The load-view benchmark:
   ```
   docker run  -v `pwd`:/root/workspace atlanmod/scalable-views load-view
   ```

4. The ocl-query benchmark can run 3 different queries (see [Queries](#queries)):
   ```
   docker run  -v `pwd`:/root/workspace atlanmod/scalable-views ocl-query
   ```

### Benchmark parameters
```
Usage: [-s SIZES] create-models
       [-s SIZES] create-weaving-models
       [-s SIZES -w WARMUPS -m MEASURES] load-view
       [-s SIZES -w WARMUPS -m MEASURES -q QUERY] ocl-query
```

#### Sizes
The `SIZES` parameters is an array of integers which specify which sizes of
models should be generated or used.  E.g.:

```
docker run ... -s '[10,20,30]' create-models
```
(the `...` stands for the volume argument and image name)

This would create three models of size 10, 20, and 30.  You could then run the
benchmarks on these sizes using the same argument:

```
docker run ... -s '[10,20,30]' load-view
```

The default value of SIZES is `[10,100,1000,10000,100000,1000000]`.  Larger
models take considerably more time to generate, and will also take more time to
run in the benchmarks.

#### Warmups and measures
To get multiple data points for the benchmarks, you can ask for multiple
measures with the `-m` option (an integer).  E.g.:

```
docker run ... -m 5 ocl-query
```

Will run the OCL query 5 times for each specified model size.

Additionally, To avoid noise in the data due to JIT optimizations, you may want
to do some warmup rounds using the `-w` option (an integer).  Warmup rounds will
execute the exact same benchmark, but you would usually discard their times when
preparing the results.

#### Queries
There are 3 predefined OCL queries you can use for benchmarking.  Choose one
with the `-q` option.

1. `-q allInstances` is a simple query that only counts objects in the Trace
   model (the largest model in the view).
2. `-q reqToTraces` and `-q traceToReqs` are two queries that navigate the whole
   views (and hence all contributing models).

## Manual instructions
The benchmarks are Eclipse projects.  You'll need the following dependencies:

1. Install [EMF Views (integrate-neoemf branch)](https://github.com/atlanmod/emfviews/tree/integrate-neoemf)
2. Install [NeoEMF (graph-long-list-support branch)](https://github.com/SOM-Research/NeoEMF/tree/graph-long-list-support)
3. Install CDO 4.6 from the Eclipse repositories.

Once you are up and running, you need to generate the models.

1. Run `benchmarks.Creator` to create all the models.  This can take up to 20 minutes.
2. Run `cdo-model.Importer` to create the Java CDO model.  This should take less than a minute.
3. Run `benchmarks.BuildWeavingModel`.  This should take around 20 minutes.

Then, you should be able to run the benchmarks:

- `benchmarks.LoadView` loads views and counts their elements
- `benchmarks.OCLQuery` can run 3 different OCL queries on views

## Copying
You are free to redistribute and modify all the code and models under the terms
of the GNU General Public License as by the Free Software Foundation, either
version 3 of the License, or (at your option) any later version.

See [LICENSE](LICENSE).

[docker-image]: https://hub.docker.com/r/atlanmod/scalable-views/
