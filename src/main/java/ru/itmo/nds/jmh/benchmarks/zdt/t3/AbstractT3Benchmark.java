package ru.itmo.nds.jmh.benchmarks.zdt.t3;

import ru.itmo.nds.jmh.benchmarks.zdt.AbstractZDTBenchmark;

abstract class AbstractT3Benchmark extends AbstractZDTBenchmark {
    @Override
    protected int nThreads() {
        return 3;
    }
}
