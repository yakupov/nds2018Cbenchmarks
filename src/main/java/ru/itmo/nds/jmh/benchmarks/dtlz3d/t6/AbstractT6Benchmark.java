package ru.itmo.nds.jmh.benchmarks.dtlz3d.t6;

import ru.itmo.nds.jmh.benchmarks.dtlz3d.AbstractDTLZ3DBenchmark;

abstract class AbstractT6Benchmark extends AbstractDTLZ3DBenchmark {
    @Override
    protected int nThreads() {
        return 6;
    }
}
