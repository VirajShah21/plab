package org.virajshah.plab;

public class MatSIMD extends Mat {

    private static final class MatRCMult extends Thread {
        private double[] mat1row;

        private double[] mat2col;

        private int setRow;

        private int setCol;

        private Mat resPointer;

        public MatRCMult(double[] mat1row, double[] mat2col, int setRow, int setCol, Mat resPointer) {
            this.mat1row = mat1row;
            this.mat2col = mat2col;
            this.setRow = setRow;
            this.setCol = setCol;
            this.resPointer = resPointer;
        }

        @Override
        public void run() {
            double sum = 0;
            for (int i = 0; i < mat1row.length; i++)
                sum += mat1row[i] * mat2col[i];
            resPointer.data[setRow][setCol] = sum;
        }
    }

    public MatSIMD(int n, int m) {
        super(n, m);
    }

    @Override
    public MatSIMD multiply(Mat other) {
        // Got these values so they are not constantly fetched.
        int resHeight = getHeight();
        int resWidth = other.getWidth();

        // The resultant matrix (not filled in yet of course)
        MatSIMD result = new MatSIMD(resHeight, resWidth);

        MatRCMult[] threads = new MatRCMult[resHeight * resWidth];

        for (int i = 0; i < resHeight; i++) {
            for (int j = 0; j < resWidth; j++) {
                Thread t = threads[i * resWidth + j] = new MatRCMult(getRow(i), other.getCol(j), i, j, result);
                t.start();
            }
        }

        try {
            for (Thread t : threads)
                t.join();
        } catch (InterruptedException e) {
            System.out.println("A thread was interrupted while multiplying!");
        }

        return result;
    }
}
