package ru.itmo.nds.jmh.benchmarks.utils;

import ru.ifmo.nds.dcns.concurrent.CJFBYPopulation;
import ru.ifmo.nds.dcns.concurrent.SyncJFBYPopulation;
import ru.ifmo.nds.dcns.jfby.JFBYPopulation;

import java.util.List;
import java.util.Map;

public class TestData {
    private final JFBYPopulation jfbyPopulation;
    private final CJFBYPopulation cjfbyPopulation;
    private final CJFBYPopulation cjfbyPopulationAlt;
    private final SyncJFBYPopulation syncJFBYPopulation;
    private final List<double[]> addends;
    private final Map<Integer, List<double[]>> concurrentAddends;

    /**
     * Test data
     *
     * @param jfbyPopulation     For JFBY
     * @param cjfbyPopulation    For CJFBY
     * @param cjfbyPopulationAlt For CJFBY, another point insertion algorithm
     * @param syncJFBYPopulation For SyncJFBY
     * @param addends            Points to add, in the given order
     * @param concurrentAddends  Points to add, split into thread-specific tasks
     */
    public TestData(JFBYPopulation jfbyPopulation,
                    CJFBYPopulation cjfbyPopulation,
                    CJFBYPopulation cjfbyPopulationAlt,
                    SyncJFBYPopulation syncJFBYPopulation, List<double[]> addends,
                    Map<Integer, List<double[]>> concurrentAddends) {
        this.jfbyPopulation = jfbyPopulation;
        this.cjfbyPopulation = cjfbyPopulation;
        this.cjfbyPopulationAlt = cjfbyPopulationAlt;
        this.syncJFBYPopulation = syncJFBYPopulation;
        this.addends = addends;
        this.concurrentAddends = concurrentAddends;
    }

    public JFBYPopulation getJfbyPopulation() {
        return jfbyPopulation;
    }

    public CJFBYPopulation getCjfbyPopulation() {
        return cjfbyPopulation;
    }

    public CJFBYPopulation getCjfbyPopulationAlt() {
        return cjfbyPopulationAlt;
    }

    public SyncJFBYPopulation getSyncJFBYPopulation() {
        return syncJFBYPopulation;
    }

    public List<double[]> getAddends() {
        return addends;
    }

    public Map<Integer, List<double[]>> getConcurrentAddends() {
        return concurrentAddends;
    }
}
