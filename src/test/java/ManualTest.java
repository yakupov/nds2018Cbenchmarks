import org.junit.Ignore;
import org.junit.Test;
import ru.itmo.nds.jmh.benchmarks.dtlz3d.t3.DTLZ1_dim3_init5000_add1000_ds2;

public class ManualTest {
    @Test
    @Ignore
    public void dtlz1dim3ds1() throws Exception {
        final DTLZ1_dim3_init5000_add1000_ds2 benchmark = new DTLZ1_dim3_init5000_add1000_ds2();
        benchmark.prepareTestData();
        System.out.println(benchmark.cjfbyAlt());
    }
}
