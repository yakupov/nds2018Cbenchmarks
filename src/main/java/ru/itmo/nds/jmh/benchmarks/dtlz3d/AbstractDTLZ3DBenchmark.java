package ru.itmo.nds.jmh.benchmarks.dtlz3d;

import com.google.gson.Gson;
import ru.itmo.nds.front_storage.DoublesAdditionProblem;
import ru.itmo.nds.jmh.benchmarks.AbstractDtlzZdtBenchmark;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

abstract public class AbstractDTLZ3DBenchmark extends AbstractDtlzZdtBenchmark {
    private String generateTestFileName() {
        final String className = getClass().getSimpleName();
        final String[] classNameArr = className.split("_");
        String problemId = "0";
        String datasetId = "0";
        String dim = "0";
        final String problemPrefix = "DTLZ";
        final String datasetPrefix = "ds";
        final String dimPrefix = "dim";
        for (String s : classNameArr) {
            if (s.startsWith(problemPrefix)) {
                problemId = s.substring(problemPrefix.length());
            } else if (s.startsWith(datasetPrefix)) {
                datasetId = s.substring(datasetPrefix.length());
            } else if (s.startsWith(dimPrefix)) {
                dim = s.substring(dimPrefix.length());
            }
        }
        final String rs = "dtlz" + problemId + "_dim" + dim + "_initSize5000_addendsCount1000_dataset" + datasetId + ".json";
        System.out.println("Loading test data from " + rs);
        return rs;
    }

    @Override
    protected DoublesAdditionProblem loadAdditionProblem() throws Exception {
        try (InputStream is = AbstractDTLZ3DBenchmark.class.getResourceAsStream(generateTestFileName())) {
            Objects.requireNonNull(is, "Test data not found");
            return new Gson().fromJson(new InputStreamReader(is), DoublesAdditionProblem.class);
        }
    }
}
