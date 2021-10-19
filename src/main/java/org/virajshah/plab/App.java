package org.virajshah.plab;

import java.util.HashMap;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        HashMap<Integer, List<Double>> runtimes = new HashMap<>();

        for (int sqSize = 2; sqSize < 1000; sqSize++) {
            Mat series1 = new Mat(sqSize, sqSize);
            Mat series2 = new Mat(sqSize, sqSize);
            MatSIMD parallel1 = new MatSIMD(sqSize, sqSize);
            MatSIMD parallel2 = new MatSIMD(sqSize, sqSize);

            series1.multiply(series2);

            parallel1.multiply(parallel1);
        }
    }

}
