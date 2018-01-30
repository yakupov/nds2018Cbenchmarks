package ru.itmo.nds.jmh.benchmarks.dtlz3d.t24;

import ru.itmo.nds.jmh.benchmarks.dtlz3d.AbstractDTLZ3DBenchmark;

abstract class AbstractT24Benchmark extends AbstractDTLZ3DBenchmark {
    @Override
    protected int nThreads() {
        return 24;
    }
}
