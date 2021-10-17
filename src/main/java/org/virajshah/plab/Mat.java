package org.virajshah.plab;

public class Mat {
    private double[][] data;

    public Mat(int n, int m) {
        data = new double[n][m];

        for (int i = 0; i < data.length; i++)
            for (int j = 0; j < data[i].length; j++)
                data[i][j] = Math.random() * 100;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Mat))
            return false;

        Mat other = (Mat) obj;

        if (getWidth() != other.getWidth() || getHeight() != other.getHeight())
            return false;

        for (int i = 0; i < getHeight(); i++)
            for (int j = 0; j < getWidth(); j++)
                if (getElement(i, j) != other.getElement(i, j))
                    return false;

        return true;
    }

    public double getElement(int row, int col) {
        return data[row][col];
    }

    public void setElement(int row, int col, double val) {
        data[row][col] = val;
    }

    public int getWidth() {
        return data[0].length;
    }

    public int getHeight() {
        return data.length;
    }

    public void print() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        String out = "";
        for (double[] row : data) {
            for (double item : row)
                out += String.format("%5.3f ", item);
            out += "\n";
        }
        return out;
    }

    public Mat multiply(Mat other) {
        Mat result = new Mat(getHeight(), other.getWidth());
        int accumulator;
        int resultColPointer = 0;
        for (int row = 0; row < result.getHeight(); row++) {
            for (resultColPointer = 0; resultColPointer < getWidth(); resultColPointer++) {
                accumulator = 0;
                for (int col = 0; col < result.getWidth(); col++)
                    accumulator += getElement(row, col) * other.getElement(col, row);
                result.setElement(row, resultColPointer, accumulator);
            }
        }
        return result;
    }

    public void setRow(int row, double[] data) {
        this.data[row] = data;
    }
}
