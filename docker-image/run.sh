#!/bin/sh

# Copy the initial models in the workspace
cp -r /root/models . && cp -r /root/views .

OPTIND=1 # Reset getopts (see https://stackoverflow.com/a/14203146 )

# Parse arguments
SIZES='[10, 100, 1000, 10000, 100000, 1000000]'
WARMUPS=0
MEASURES=1
QUERY=allInstances
JVM_ARGS="-XX:+UseConcMarkSweepGC -Xmx8g"
FAST_EXTENTS_MAP=true

while getopts "s:w:m:q:j:f:" opt; do
    case "$opt" in
        s) SIZES=$OPTARG
           ;;
        w) WARMUPS=$OPTARG
           ;;
        m) MEASURES=$OPTARG
           ;;
        q) QUERY=$OPTARG
           ;;
        j) JVM_ARGS=$OPTARG
           ;;
        f) FAST_EXTENTS_MAP=$OPTARG
           ;;
    esac
done
shift $((OPTIND-1))

# Parse command
CMD=
case $1 in
    create-models)
        PROG="Creator"
        ARGS=
        ;;
    create-weaving-models)
        PROG="BuildWeavingModel"
        ARGS=
        ;;
    load-view)
        PROG=LoadView
        ARGS="$WARMUPS $MEASURES"
        ;;
    ocl-query)
        PROG=OCLQuery
        ARGS="$WARMUPS $MEASURES $QUERY $FAST_EXTENTS_MAP"
        ;;
    *)
        echo 'Usage: ./run.sh [-s SIZES] create-models'
        echo '       ./run.sh [-s SIZES] create-weaving-models'
        echo '       ./run.sh [-s SIZES -w WARMUPS -m MEASURES] load-view'
        echo '       ./run.sh [-s SIZES -w WARMUPS -m MEASURES -q QUERY -f FAST_EXTENTS_MAP] ocl-query'
        echo ''
        echo 'Additional options:'
        echo '  -j ARGS    Additional arguments to pass to the JVM'
        exit 0
        ;;
esac

# Set the classpath and run the requested program
java $JVM_ARGS -Dfile.encoding=UTF-8 -classpath /root/deps/org.atlanmod.emfviews.virtuallinks:/root/deps/org.atlanmod.emfviews:/root/deps/benchmarks:/root/deps/cdo-model:/root/deps/java-cdo:/root/deps/trace-neoemf:/root/deps/trace:/root/deps/virtuallinks-neoemf:/root/deps/com.google.guava_21.0.0.v20170206-1425.jar:/root/deps/com.google.inject_3.0.0.v201312141243.jar:/root/deps/fr.inria.atlanmod.neoemf.core_1.0.3.20180426150957.jar:/root/deps/fr.inria.atlanmod.neoemf.data.blueprints.core_1.0.3.20180426150957.jar:/root/deps/fr.inria.atlanmod.neoemf.data.blueprints.neo4j.wrapper_1.0.3.20180426150957.jar:/root/deps/fr.inria.atlanmod.neoemf.data.blueprints.neo4j_1.0.3.20180426150957.jar:/root/deps/fr.inria.atlanmod.neoemf.io_1.0.3.20180426150957.jar:/root/deps/lpg.runtime.java_2.0.17.v201004271640.jar:/root/deps/org.antlr.runtime_3.1.b1_3.1.0.jar:/root/deps/org.antlr.runtime_3.2.0.v201101311130.jar:/root/deps/org.apache.commons.cli_1.2.0.v201404270220.jar:/root/deps/org.apache.log4j_1.2.15.v201012070815.jar:/root/deps/org.eclipse.emf.cdo.common_4.6.0.v20170519-1811.jar:/root/deps/org.eclipse.emf.cdo.net4j_4.1.500.v20170130-1651.jar:/root/deps/org.eclipse.emf.cdo.server.db_4.5.0.v20170130-1651.jar:/root/deps/org.eclipse.emf.cdo.server.net4j_4.1.400.v20170130-1651.jar:/root/deps/org.eclipse.emf.cdo.server_4.6.0.v20170602-1611.jar:/root/deps/org.eclipse.emf.cdo_4.6.0.v20170226-0704.jar:/root/deps/org.eclipse.emf.edit_2.12.0.v20170609-0928.jar:/root/deps/org.eclipse.emf.mapping.ecore2xml_2.9.0.v20170609-0928.jar:/root/deps/org.eclipse.emf.mwe.core_1.3.21.201705291011.jar:/root/deps/org.eclipse.emf.mwe.utils_1.3.21.201705291011.jar:/root/deps/org.eclipse.emf.mwe2.runtime_2.9.1.201705291011.jar:/root/deps/org.eclipse.emf_2.6.0.v20170609-0928.jar:/root/deps/org.eclipse.epsilon.common_1.4.0.201611012202.jar:/root/deps/org.eclipse.epsilon.dependencies_1.4.0.201611012202.jar:/root/deps/org.eclipse.epsilon.ecl.engine_1.4.0.201611012202.jar:/root/deps/org.eclipse.epsilon.emc.emf_1.4.0.201611012202.jar:/root/deps/org.eclipse.epsilon.eol.engine_1.4.0.201611012202.jar:/root/deps/org.eclipse.epsilon.erl.engine_1.4.0.201611012202.jar:/root/deps/org.eclipse.gmt.modisco.java_1.1.0.201705300912.jar:/root/deps/org.eclipse.jdt.annotation_2.1.100.v20170511-1408.jar:/root/deps/org.eclipse.m2m.atl.common_3.8.0.v201705182014.jar:/root/deps/org.eclipse.m2m.atl.emftvm.trace_3.8.0.v201705182014.jar:/root/deps/org.eclipse.m2m.atl.emftvm_3.8.0.v201705182014.jar:/root/deps/org.eclipse.net4j.db.h2_4.3.0.v20161018-1530.jar:/root/deps/org.eclipse.net4j.db.jdbc_4.3.200.v20161018-1819.jar:/root/deps/org.eclipse.net4j.db_4.6.0.v20170217-1728.jar:/root/deps/org.eclipse.net4j.jvm_4.1.500.v20161018-1819.jar:/root/deps/org.eclipse.net4j.util_3.7.0.v20170521-0536.jar:/root/deps/org.eclipse.net4j_4.6.0.v20170522-0642.jar:/root/deps/org.eclipse.ocl.common_1.4.200.v20160613-1518.jar:/root/deps/org.eclipse.ocl.ecore_3.6.200.v20170522-1736.jar:/root/deps/org.eclipse.ocl.pivot_1.3.0.v20170522-1753.jar:/root/deps/org.eclipse.ocl.xtext.base_1.3.0.v20170522-1753.jar:/root/deps/org.eclipse.ocl.xtext.essentialocl_1.3.0.v20170522-1753.jar:/root/deps/org.eclipse.ocl_3.6.200.v20170522-1736.jar:/root/deps/org.eclipse.rmf.reqif10.serialization_0.13.0.201509161042.jar:/root/deps/org.eclipse.rmf.reqif10_0.13.0.201509161042.jar:/root/deps/org.eclipse.sphinx.emf.serialization_0.11.0.201706140911.jar:/root/deps/org.eclipse.uml2.common_2.1.0.v20170605-1616.jar:/root/deps/org.eclipse.uml2.types_2.0.0.v20170605-1616.jar:/root/deps/org.eclipse.uml2.uml_5.3.0.v20170605-1616.jar:/root/deps/org.eclipse.xtext.util_2.13.0.v20171020-0708.jar:/root/deps/org.eclipse.xtext_2.13.0.v20171020-0708.jar:/root/deps/org.h2_1.3.168.v201212121212.jar:/root/deps/org.objectweb.asm_5.2.0.v20170126-0011.jar:/root/deps/javax.inject_1.0.0.v20091030.jar:/root/deps/org.eclipse.core.contenttype_3.6.0.v20170207-1037.jar:/root/deps/org.eclipse.core.jobs_3.9.0.v20170322-0013.jar:/root/deps/org.eclipse.core.resources_3.12.0.v20170417-1558.jar:/root/deps/org.eclipse.core.runtime_3.13.0.v20170207-1030.jar:/root/deps/org.eclipse.emf.common_2.13.0.v20170609-0707.jar:/root/deps/org.eclipse.emf.ecore.change_2.11.0.v20170609-0707.jar:/root/deps/org.eclipse.emf.ecore.xmi_2.13.0.v20170609-0707.jar:/root/deps/org.eclipse.emf.ecore_2.13.0.v20170609-0707.jar:/root/deps/org.eclipse.equinox.app_1.3.400.v20150715-1528.jar:/root/deps/org.eclipse.equinox.common_3.9.0.v20170207-1454.jar:/root/deps/org.eclipse.equinox.preferences_3.7.0.v20170126-2132.jar:/root/deps/org.eclipse.equinox.registry_3.7.0.v20170222-1344.jar:/root/deps/org.eclipse.osgi.compatibility.state_1.1.0.v20170516-1513.jar:/root/deps/org.eclipse.osgi_3.12.0.v20170512-1932.jar:/root/deps/caffeine-2.3.5.jar:/root/deps/cglib-nodep-3.2.4.jar:/root/deps/commons-configuration-1.10.jar:/root/deps/commons-io-2.5.jar:/root/deps/commons-lang-2.6.jar:/root/deps/commons-lang3-3.5.jar:/root/deps/commons-logging-1.1.1.jar:/root/deps/guava-15.0.jar:/root/deps/log4j-api-2.7.jar:/root/deps/log4j-core-2.7.jar:/root/deps/log4j-jcl-2.7.jar:/root/deps/log4j-jul-2.7.jar:/root/deps/log4j-slf4j-impl-2.7.jar:/root/deps/slf4j-api-1.7.21.jar:/root/deps/blueprints-core-2.6.0.jar:/root/deps/caffeine-2.3.5.jar:/root/deps/commons-configuration-1.10.jar:/root/deps/commons-io-2.5.jar:/root/deps/commons-lang-2.6.jar:/root/deps/commons-lang3-3.5.jar:/root/deps/commons-logging-1.1.1.jar:/root/deps/guava-15.0.jar:/root/deps/hppc-0.6.0.jar:/root/deps/jackson-annotations-2.2.3.jar:/root/deps/jackson-core-2.2.3.jar:/root/deps/jackson-databind-2.2.3.jar:/root/deps/jettison-1.3.3.jar:/root/deps/stax-api-1.0.1.jar:/root/deps/jta-1.1.jar:/root/deps/lucene-core-3.6.2.jar:/root/deps/neo4j-kernel-1.9.6.jar:/root/deps/neo4j-lucene-index-1.9.6.jar:/root/deps/blueprints-neo4j-graph-2.6.0.jar:/root/deps/caffeine-2.3.5.jar:/root/deps/guava-15.0.jar:/root/deps/stax-api-1.0-2.jar:/root/deps/stax2-api-4.0.0.jar:/root/deps/woodstox-core-asl-4.4.1.jar $PROG "$SIZES" $ARGS
