
import java.util.concurrent.*;

public class Corrida1 {
    static int count = 0;

    public static void main(String[] args) throws Exception {
        int T = 8;
        int M = 250_000; // cada thread faz M incrementos
        ExecutorService pool = Executors.newFixedThreadPool(T);

        Runnable r = () -> {
            for (int i = 0; i < M; i++) {
                count++;
            }
        };

        long t0 = System.nanoTime();
        for (int i = 0; i < T; i++) pool.submit(r);
        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.MINUTES);
        long t1 = System.nanoTime();

        System.out.printf("Esperado=%d, Obtido=%d, Tempo=%.3fs%n",
            (long) T * M, count, (t1 - t0) / 1e9);
    }
}
