package ru.itmo.nds.jmh.benchmarks.dtlz3d.t12;

import ru.itmo.nds.jmh.benchmarks.dtlz3d.AbstractDTLZ3DBenchmark;

abstract class AbstractT12Benchmark extends AbstractDTLZ3DBenchmark {
    @Override
    protected int nThreads() {
        return 12;
    }
}
