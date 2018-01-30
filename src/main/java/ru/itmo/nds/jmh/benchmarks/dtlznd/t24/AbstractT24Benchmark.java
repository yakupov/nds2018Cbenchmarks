package ru.itmo.nds.jmh.benchmarks.dtlznd.t24;

import ru.itmo.nds.jmh.benchmarks.dtlznd.AbstractDTLZNDBenchmark;

abstract class AbstractT24Benchmark extends AbstractDTLZNDBenchmark {
    @Override
    protected int nThreads() {
        return 24;
    }
}
