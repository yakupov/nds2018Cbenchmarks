package ru.itmo.nds.jmh.benchmarks.dtlznd.t6;

import ru.itmo.nds.jmh.benchmarks.dtlznd.AbstractDTLZNDBenchmark;

abstract class AbstractT6Benchmark extends AbstractDTLZNDBenchmark {
    @Override
    protected int nThreads() {
        return 6;
    }
}
