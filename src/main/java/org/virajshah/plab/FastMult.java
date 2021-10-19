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
        i = 0;
        j = 0;
        threads = new Thread[4];
        for (int i = 0; i < threads.length; i++)
            threads[i] = new Worker(this);
    }

    public FastMult(Mat mat1, Mat mat2, int numThreads) {
        data1 = mat1.data;
        data2 = mat2.data;
        result = new double[mat1.getHeight()][mat2.getWidth()];
        i = 0;
        j = 0;
        threads = new Thread[numThreads];
        for (int i = 0; i < threads.length; i++)
            threads[i] = new Worker(this);
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

    public synchronized void nextRowCol(int[] ptr) {
        if (++j == result[0].length) {
            j = 0;
            i++;
        }

        if (i >= result.length)
            ptr[0] = -1;
        else {
            ptr[0] = i;
            ptr[1] = j;
        }
    }

    private static final class Worker extends Thread {
        FastMult multiplier;

        Worker(FastMult multiplier) {
            this.multiplier = multiplier;
        }

        public void run() {
            int[] rc = new int[2];
            int i, j;
            while (true) {
                multiplier.nextRowCol(rc);

                if (rc[0] == -1)
                    break;

                i = rc[0];
                j = rc[1];

                int sum = 0;
                for (int k = 0; k < multiplier.data2.length; k++)
                    sum += multiplier.data1[i][k] * multiplier.data2[k][j];

                multiplier.result[i][j] = sum;
            }
        }
    }
}
