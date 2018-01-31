import org.junit.Ignore;
import org.junit.Test;
import ru.itmo.nds.jmh.benchmarks.dtlz3d.t3.DTLZ1_dim3_init5000_add1000_ds2;
import ru.itmo.nds.jmh.benchmarks.dtlz3d.t24.DTLZ4_dim3_init5000_add1000_ds2;
import ru.itmo.nds.jmh.benchmarks.zdt.t3.ZDT2_dim2_init5000_add1000_ds3;

import java.util.concurrent.TimeUnit;

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
        for (int i = 0; i < 100; ++i) {
            System.out.println(i);
            benchmark.prepareTestData();
            System.out.println(benchmark.syncJfby());
        }
    }

    @Test
    @Ignore
    public void zdt2ds3() throws Exception {
        final ZDT2_dim2_init5000_add1000_ds3 benchmark = new ZDT2_dim2_init5000_add1000_ds3();
        benchmark.prepareTestData();
        System.out.println(benchmark.syncJfby());

        for (int i = 0; i < 100; ++i) {
            final long ts = System.nanoTime();
            benchmark.prepareTestData();
            final int rs = benchmark.cjfbyAlt();
            final long duration = TimeUnit.NANOSECONDS.toSeconds(System.nanoTime() - ts);
            System.out.println("rs=" + rs + ", duration = (s) " + duration);
        }
    }
}
