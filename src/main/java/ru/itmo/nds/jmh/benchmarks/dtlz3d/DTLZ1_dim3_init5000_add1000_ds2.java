package ru.itmo.nds.jmh.benchmarks.dtlz3d;

import com.google.gson.Gson;
import ru.itmo.nds.front_storage.DoublesAdditionProblem;
import ru.itmo.nds.jmh.benchmarks.AbstractDtlzZdtBenchmark;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * Perform all the same tests as in {@link AbstractDtlzZdtBenchmark}
 * but on another dataset
 */
public class DTLZ1_dim3_init5000_add1000_ds2 extends AbstractDtlzZdtBenchmark {
    @Override
    protected DoublesAdditionProblem loadAdditionProblem() throws Exception {
        try (InputStream is = DTLZ1_dim3_init5000_add1000_ds2.class
                .getResourceAsStream("dtlz1_dim3_initSize5000_addendsCount1000_dataset2.json")) {
            Objects.requireNonNull(is, "Test data not found");
            return new Gson().fromJson(new InputStreamReader(is), DoublesAdditionProblem.class);
        }
    }
}
