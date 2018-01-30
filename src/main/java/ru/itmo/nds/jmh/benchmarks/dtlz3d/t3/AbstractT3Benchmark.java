package ru.itmo.nds.jmh.benchmarks.dtlz3d.t3;

import ru.itmo.nds.jmh.benchmarks.dtlz3d.AbstractDTLZ3DBenchmark;

abstract class AbstractT3Benchmark extends AbstractDTLZ3DBenchmark {
    @Override
    protected int nThreads() {
        return 3;
    }
}
