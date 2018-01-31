import org.junit.Ignore;
import org.junit.Test;
import ru.itmo.nds.jmh.benchmarks.dtlz3d.t3.DTLZ1_dim3_init5000_add1000_ds2;
import ru.itmo.nds.jmh.benchmarks.dtlz3d.t3.DTLZ4_dim3_init5000_add1000_ds2;

public class ManualTest {
    @Test
    @Ignore
    public void dtlz1dim3ds2() throws Exception {
        final DTLZ1_dim3_init5000_add1000_ds2 benchmark = new DTLZ1_dim3_init5000_add1000_ds2();
        benchmark.prepareTestData();
        System.out.println(benchmark.cjfbyAlt());
    }

    @Test
    @Ignore
    public void dtlz4dim3ds2() throws Exception {
        final DTLZ4_dim3_init5000_add1000_ds2 benchmark = new DTLZ4_dim3_init5000_add1000_ds2();
        benchmark.prepareTestData();
        System.out.println(benchmark.syncJfby());
    }
}
