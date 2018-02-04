package ru.itmo.nds.jmh.benchmarks;

import ru.ifmo.nds.IManagedPopulation;
import ru.ifmo.nds.impl.FitnessOnlyIndividual;
import ru.itmo.nds.jmh.benchmarks.utils.TestData;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

abstract class AbstractBenchmark {
    protected abstract TestData getPreparedTestData();

    protected abstract int nThreads();

    int sortUsingJFBY() {
        final TestData testData = getPreparedTestData();
        final IManagedPopulation population = testData.getJfbyPopulation();
        int rs = 0;
        for (double[] doubles : testData.getAddends()) {
            rs = population.addIndividual(new FitnessOnlyIndividual(doubles));
        }
        return rs;
    }

    int sortUsingCJFBY() {
        return sortUsingConcurrentJFBY(getPreparedTestData().getCjfbyPopulation());
    }

    int sortUsingCJFBYAlt() {
        return sortUsingConcurrentJFBY(getPreparedTestData().getCjfbyPopulationAlt());
    }

    int sortUsingSyncJFBY() {
        return sortUsingConcurrentJFBY(getPreparedTestData().getLevelLockJFBYPopulation());
    }

    int sortUsingTsJFBY() {
        return sortUsingConcurrentJFBY(getPreparedTestData().getTsJfbyPopulation());
    }

    private int sortUsingConcurrentJFBY(final IManagedPopulation population) {
        final ExecutorService es = Executors.newFixedThreadPool(nThreads());
        try {
            final TestData testData = getPreparedTestData();
            final int nThreads = nThreads();
            final CountDownLatch latch = new CountDownLatch(nThreads);
            final AtomicInteger atomicInteger = new AtomicInteger(0);
            for (int i = 0; i < nThreads; ++i) {
                final int threadId = i;
                es.submit(() -> {
                    int res = 0;
                    try {
                        for (double[] doubles : testData.getConcurrentAddends().get(threadId)) {
                            res += population.addIndividual(new FitnessOnlyIndividual(doubles));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        latch.countDown();
                        atomicInteger.set(res);
                    }
                });
            }
            try {
                latch.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return atomicInteger.get();
        } finally {
            es.shutdownNow();
        }
    }

}
