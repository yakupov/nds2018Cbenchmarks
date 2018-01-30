package ru.itmo.nds.jmh.benchmarks.dtlznd.t3;

import ru.itmo.nds.jmh.benchmarks.dtlznd.AbstractDTLZNDBenchmark;

abstract class AbstractT3Benchmark extends AbstractDTLZNDBenchmark {
    @Override
    protected int nThreads() {
        return 3;
    }
}
