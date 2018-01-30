package ru.itmo.nds.jmh.benchmarks.dtlznd.t12;

import ru.itmo.nds.jmh.benchmarks.dtlznd.AbstractDTLZNDBenchmark;

abstract class AbstractT12Benchmark extends AbstractDTLZNDBenchmark {
    @Override
    protected int nThreads() {
        return 12;
    }
}
