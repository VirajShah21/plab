package org.virajshah.plab;

import java.util.HashMap;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        HashMap<Integer, long[]> runtimes = new HashMap<>();

        for (int sqSize = 2; sqSize < 1000; sqSize++) {
            Mat series1 = new Mat(sqSize, sqSize);
            Mat series2 = new Mat(sqSize, sqSize);
            MatSIMD parallel1 = new MatSIMD(sqSize, sqSize);
            MatSIMD parallel2 = new MatSIMD(sqSize, sqSize);

            long start, stop;

            start = System.currentTimeMillis();
            series1.multiply(series2);
            stop = System.currentTimeMillis();
            runtimes.put(sqSize, new long[] { stop - start, 0 });

            start = System.currentTimeMillis();
            parallel1.multiply(parallel2);
            stop = System.currentTimeMillis();
            runtimes.get(sqSize)[1] = stop - start;
        }
    }

}
