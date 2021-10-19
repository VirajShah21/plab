package org.virajshah.plab;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        HashMap<Integer, long[]> runtimes = new HashMap<>();

        for (int sqSize = 2; sqSize < 3; sqSize++) {
            System.out.printf("Running for matrix with size %s\n", sqSize);
            Mat series1 = new Mat(sqSize, sqSize);
            Mat series2 = new Mat(sqSize, sqSize);
            FastMult fastMult = new FastMult(series1, series2);

            long start, stop;

            start = System.currentTimeMillis();
            series1.multiply(series2);
            stop = System.currentTimeMillis();
            runtimes.put(sqSize, new long[] { stop - start, 0 });

            start = System.currentTimeMillis();
            fastMult.multiply();
            stop = System.currentTimeMillis();
            runtimes.get(sqSize)[1] = stop - start;
        }

        System.out.printf("| %5s | %20s | %20s |\n===========================\n", "Size", "Series (ms)",
                "Parallel (ms)");

        for (Map.Entry<Integer, long[]> entrySet : runtimes.entrySet())
            System.out.printf("| %5d | %20d | %20d |\n", entrySet.getKey(), entrySet.getValue()[0],
                    entrySet.getValue()[1]);

    }

}
