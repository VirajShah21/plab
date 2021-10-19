package org.virajshah.plab;

import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        HashMap<Integer, long[]> runtimes = new HashMap<>();

        for (int sqSize = 1; sqSize < 250; sqSize++) {
            System.out.printf("Running for matrix with size %s\n", sqSize);
            Mat series1 = new Mat(sqSize, sqSize);
            Mat series2 = new Mat(sqSize, sqSize);
            long[] trialTimes = new long[4];

            long start, stop;

            start = System.currentTimeMillis();
            series1.multiply(series2);
            stop = System.currentTimeMillis();
            trialTimes[0] = stop - start;

            for (int numThreads = 2; numThreads < 5; numThreads++) {
                FastMult fastMult = new FastMult(series1, series2, numThreads);

                start = System.currentTimeMillis();
                fastMult.multiply();
                stop = System.currentTimeMillis();
                trialTimes[numThreads - 1] = stop - start;
            }

            runtimes.put(sqSize, trialTimes);
        }

        System.out.printf("| %5s | %6s | %6s | %6s | %6s |\n", "Size", "Series", "2P", "3P", "4P");

        for (Map.Entry<Integer, long[]> entrySet : runtimes.entrySet())
            System.out.printf("| %5d | %6d | %6d | %6d | %6d |\n", entrySet.getKey(), entrySet.getValue()[0],
                    entrySet.getValue()[1], entrySet.getValue()[2], entrySet.getValue()[3]);

        Plotter.displayChart(runtimes);

    }

}
