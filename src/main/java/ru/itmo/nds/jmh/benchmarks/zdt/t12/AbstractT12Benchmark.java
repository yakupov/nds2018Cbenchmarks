package ru.itmo.nds.jmh.benchmarks.zdt.t12;

import ru.itmo.nds.jmh.benchmarks.zdt.AbstractZDTBenchmark;

abstract class AbstractT12Benchmark extends AbstractZDTBenchmark {
    @Override
    protected int nThreads() {
        return 12;
    }
}
