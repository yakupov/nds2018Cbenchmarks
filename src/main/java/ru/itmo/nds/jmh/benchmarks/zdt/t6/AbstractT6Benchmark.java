package ru.itmo.nds.jmh.benchmarks.zdt.t6;

import ru.itmo.nds.jmh.benchmarks.zdt.AbstractZDTBenchmark;

abstract class AbstractT6Benchmark extends AbstractZDTBenchmark {
    @Override
    protected int nThreads() {
        return 6;
    }
}
