package ru.itmo.nds.jmh.benchmarks.utils;

import ru.ifmo.nds.dcns.concurrent.CJFBYPopulation;
import ru.ifmo.nds.dcns.concurrent.LevelLockJFBYPopulation;
import ru.ifmo.nds.dcns.jfby.JFBYPopulation;
import ru.ifmo.nds.dcns.jfby.TotalSyncJFBYPopulation;

import java.util.List;
import java.util.Map;

public class TestData {
    private final JFBYPopulation jfbyPopulation;
    private final TotalSyncJFBYPopulation tsJfbyPopulation;
    private final CJFBYPopulation cjfbyPopulation;
    private final CJFBYPopulation cjfbyPopulationAlt;
    private final LevelLockJFBYPopulation levelLockJFBYPopulation;
    private final List<double[]> addends;
    private final Map<Integer, List<double[]>> concurrentAddends;

    /**
     * Test data
     *
     * @param jfbyPopulation          For JFBY
     * @param tsJfbyPopulation        For synchronized JFBY
     * @param cjfbyPopulation         For CJFBY
     * @param cjfbyPopulationAlt      For CJFBY, another point insertion algorithm
     * @param levelLockJFBYPopulation For LevelLock JFBY
     * @param addends                 Points to add, in the given order
     * @param concurrentAddends       Points to add, split into thread-specific tasks
     */
    public TestData(JFBYPopulation jfbyPopulation,
                    TotalSyncJFBYPopulation tsJfbyPopulation, CJFBYPopulation cjfbyPopulation,
                    CJFBYPopulation cjfbyPopulationAlt,
                    LevelLockJFBYPopulation levelLockJFBYPopulation, List<double[]> addends,
                    Map<Integer, List<double[]>> concurrentAddends) {
        this.jfbyPopulation = jfbyPopulation;
        this.tsJfbyPopulation = tsJfbyPopulation;
        this.cjfbyPopulation = cjfbyPopulation;
        this.cjfbyPopulationAlt = cjfbyPopulationAlt;
        this.levelLockJFBYPopulation = levelLockJFBYPopulation;
        this.addends = addends;
        this.concurrentAddends = concurrentAddends;
    }

    public JFBYPopulation getJfbyPopulation() {
        return jfbyPopulation;
    }

    public TotalSyncJFBYPopulation getTsJfbyPopulation() {
        return tsJfbyPopulation;
    }

    public CJFBYPopulation getCjfbyPopulation() {
        return cjfbyPopulation;
    }

    public CJFBYPopulation getCjfbyPopulationAlt() {
        return cjfbyPopulationAlt;
    }

    public LevelLockJFBYPopulation getLevelLockJFBYPopulation() {
        return levelLockJFBYPopulation;
    }

    public List<double[]> getAddends() {
        return addends;
    }

    public Map<Integer, List<double[]>> getConcurrentAddends() {
        return concurrentAddends;
    }
}
