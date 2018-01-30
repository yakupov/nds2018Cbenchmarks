package ru.itmo.nds.jmh.benchmarks.zdt.t24;

import ru.itmo.nds.jmh.benchmarks.zdt.AbstractZDTBenchmark;

abstract class AbstractT24Benchmark extends AbstractZDTBenchmark {
    @Override
    protected int nThreads() {
        return 24;
    }
}
