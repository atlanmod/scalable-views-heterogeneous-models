# Scalable Views on Heterogeneous Models

Benchmarks for combining EMF Views, NeoEMF and CDO on a MegaM@Rt2 use case.

## Running the benchmarks

The benchmarks run inside Eclipse.  There's no Maven build yet.

1. Install [EMF Views](https://github.com/atlanmod/emfviews/tree/integrate-neoemf)
2. Install [NeoEMF](https://github.com/SOM-Research/NeoEMF/tree/graph-long-list-support)
3. Install CDO 4.6 from the Eclipse repositories.

Once you are up and running, you need to generate the models.

1. Run `benchmarks.Creator` to create all models.  This should take around 5 minutes.
2. Run `cdo-model.Importer` to create the Java CDO model.  This should take less than a minute.
3. Run `benchmarks.BuildWeavingModel`.  This should take around 20 minutes.

Then, you should be able to run the benchmarks:

- `benchmarks.LoadView` loads views and counts their elements
- `benchmarks.OCLQuery` can run 3 different OCL queries on views
- `benchmarks.ATLTranso` runs an ATL transformation on views

## Copying
You are free to redistribute and modify all the code and models under the terms
of the GNU General Public License as by the Free Software Foundation, either
version 3 of the License, or (at your option) any later version.

See [LICENSE](LICENSE).
