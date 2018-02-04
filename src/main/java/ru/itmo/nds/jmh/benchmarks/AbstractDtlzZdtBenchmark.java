package ru.itmo.nds.jmh.benchmarks;

import org.openjdk.jmh.annotations.*;
import ru.ifmo.nds.IIndividual;
import ru.ifmo.nds.INonDominationLevel;
import ru.ifmo.nds.dcns.concurrent.CJFBYPopulation;
import ru.ifmo.nds.dcns.concurrent.LevelLockJFBYPopulation;
import ru.ifmo.nds.dcns.jfby.JFBYNonDominationLevel;
import ru.ifmo.nds.dcns.jfby.JFBYPopulation;
import ru.ifmo.nds.dcns.jfby.TotalSyncJFBYPopulation;
import ru.ifmo.nds.dcns.sorter.IncrementalJFB;
import ru.ifmo.nds.impl.FitnessOnlyIndividual;
import ru.itmo.nds.front_storage.DoublesAdditionProblem;
import ru.itmo.nds.jmh.benchmarks.utils.TestData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 5)
@Measurement(iterations = 4)
@Fork(value = 2)
public abstract class AbstractDtlzZdtBenchmark extends AbstractBenchmark {
    private final IncrementalJFB incrementalJFB = new IncrementalJFB();

    private final AtomicReference<TestData> preparedTestData = new AtomicReference<>();
    private DoublesAdditionProblem additionProblem;

    @Override
    protected TestData getPreparedTestData() {
        return preparedTestData.get();
    }

    protected abstract DoublesAdditionProblem loadAdditionProblem() throws Exception;

    @SuppressWarnings("WeakerAccess")
    @Setup(Level.Invocation)
    public void prepareTestData() throws Exception {
        if (additionProblem == null)
            additionProblem = loadAdditionProblem();

        final List<INonDominationLevel> jfbyLevels = new ArrayList<>();
        final List<INonDominationLevel> tsJfbyLevels = new ArrayList<>();
        final CopyOnWriteArrayList<AtomicReference<INonDominationLevel>> cjfbyLevels = new CopyOnWriteArrayList<>();
        final CopyOnWriteArrayList<AtomicReference<INonDominationLevel>> cjfbyAltLevels = new CopyOnWriteArrayList<>();
        final CopyOnWriteArrayList<INonDominationLevel> syncJFBYLevels = new CopyOnWriteArrayList<>();
        additionProblem.getFronts().forEach(f -> {
            final List<IIndividual> individuals = f.stream()
                    .sorted((o1, o2) -> {
                        for (int objIndex = 0; objIndex < o1.length; ++objIndex) {
                            if (o1[objIndex] < o2[objIndex])
                                return -1;
                            else if (o1[objIndex] > o2[objIndex])
                                return 1;
                        }
                        return 0;
                    })
                    .map(FitnessOnlyIndividual::new)
                    .collect(Collectors.toList());
            final JFBYNonDominationLevel level = new JFBYNonDominationLevel(incrementalJFB, individuals);
            jfbyLevels.add(level);
            tsJfbyLevels.add(level);
            cjfbyLevels.add(new AtomicReference<>(level));
            cjfbyAltLevels.add(new AtomicReference<>(level));
            syncJFBYLevels.add(level);
        });

        final JFBYPopulation jfbyPopulation = new JFBYPopulation(jfbyLevels, Long.MAX_VALUE);
        final TotalSyncJFBYPopulation tsJfbyPopulation = new TotalSyncJFBYPopulation(tsJfbyLevels, Long.MAX_VALUE);
        final CJFBYPopulation cjfbyPopulation = new CJFBYPopulation(cjfbyLevels, Integer.MAX_VALUE, false);
        final CJFBYPopulation cjfbyAltPopulation = new CJFBYPopulation(cjfbyAltLevels, Integer.MAX_VALUE, true);
        final LevelLockJFBYPopulation levelLockJFBYPopulation = new LevelLockJFBYPopulation(incrementalJFB, syncJFBYLevels, Long.MAX_VALUE);

        final List<double[]> addends = additionProblem.getAddends();
        final Map<Integer, List<double[]>> concurrentAddends = new HashMap<>();
        final int nThreads = nThreads();
        for (int i = 0; i < addends.size(); ++i) {
            final int threadId = i % nThreads;
            concurrentAddends.putIfAbsent(threadId, new ArrayList<>());
            concurrentAddends.get(threadId).add(addends.get(i));
        }

        preparedTestData.set(new TestData(jfbyPopulation, tsJfbyPopulation, cjfbyPopulation, cjfbyAltPopulation,
                levelLockJFBYPopulation, addends, concurrentAddends));
    }

    @Benchmark
    public int jfby() {
        return sortUsingJFBY();
    }

    @Benchmark
    public int cjfby() {
        return sortUsingCJFBY();
    }

    @Benchmark
    public int cjfbyAlt() {
        return sortUsingCJFBYAlt();
    }

    @Benchmark
    public int levelLockJfby() {
        return sortUsingSyncJFBY();
    }

    @Benchmark
    public int tsJfby() {
        return sortUsingTsJFBY();
    }
}
