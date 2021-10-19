package org.virajshah.plab;

public class FastMult {
    double[][] data1;
    double[][] data2;
    double[][] result;
    volatile int i;
    volatile int j;
    Thread[] threads;

    public FastMult(Mat mat1, Mat mat2) {
        data1 = mat1.data;
        data2 = mat2.data;
        result = new double[mat1.getHeight()][mat2.getWidth()];
        threads = new Thread[4];
        for (int i = 0; i < threads.length; i++)
            threads[i] = new Worker(this);
        i = 0;
        j = 0;
    }

    public double[][] multiply() {
        for (Thread t : threads)
            t.start();

        for (Thread t : threads)
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        return result;
    }

    public synchronized int[] nextRowCol() {
        if (++j == result[0].length) {
            j = 0;
            if (++i == result.length)
                return new int[] { -1, 0 };
        }
        return new int[] { i, j };
    }

    private static final class Worker extends Thread {
        FastMult multiplier;

        Worker(FastMult multiplier) {
            this.multiplier = multiplier;
        }

        public void run() {
            while (true) {
                int[] rc = multiplier.nextRowCol();

                if (rc[0] == -1)
                    break;

                int i = rc[0];
                int j = rc[1];

                int sum = 0;
                for (int k = 0; k < multiplier.data2.length; k++)
                    sum += multiplier.data1[i][k] * multiplier.data2[k][j];

                multiplier.result[i][j] = sum;
            }
        }
    }
}
