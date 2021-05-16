package os.mem;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        // Test 1:
        // BestFit, n=100, epochs: 100, mean:{20, 60}, std:{5, 10}
        MemoryBestFit mem1 = new MemoryBestFit(100);
        ArrayList<Metric> metrics;
        Simulator<MemoryBestFit> simBestFit = new Simulator<>(mem1, 17);

        metrics = simBestFit.simulateRange(100, 20, 60, 2, 5, 10, 1, true);
        for (Metric metric : metrics)
            System.out.printf("%s\n-----------------------\n", metric);
        // Test 1 - END ---------------------------------------------------------

    }
}
