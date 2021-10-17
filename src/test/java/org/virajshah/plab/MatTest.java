package org.virajshah.plab;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MatTest {
    @Test
    public void shouldDetectMatrixSizeMismatch() {
        Mat mat1 = new Mat(3, 3);
        Mat mat2 = new Mat(3, 4);
        Mat mat3 = new Mat(4, 3);

        // ! These three loops ensure that all elements are equal.
        // ! Otherwise randomized values would be the reason for
        // ! Mat.equals() returning false.

        for (int i = 0; i < mat1.getHeight(); i++) {
            for (int j = 0; j < mat1.getWidth(); j++) {
                mat1.setElement(i, j, 0);
            }
        }

        for (int i = 0; i < mat2.getHeight(); i++) {
            for (int j = 0; j < mat2.getWidth(); j++) {
                mat2.setElement(i, j, 0);
            }
        }

        for (int i = 0; i < mat3.getHeight(); i++) {
            for (int j = 0; j < mat3.getWidth(); j++) {
                mat3.setElement(i, j, 0);
            }
        }

        assertFalse(mat1.equals(mat2));
        assertFalse(mat2.equals(mat3));
        assertFalse(mat1.equals(mat3));
    }

    @Test
    public void shouldMultiplySquareMatrices() {
        Mat m1 = new Mat(2, 2);
        Mat m2 = new Mat(2, 2);
        Mat expected = new Mat(2, 2);

        m1.setRow(0, new double[] { 1, 2 });
        m1.setRow(1, new double[] { 1, 2 });

        m2.setRow(0, new double[] { 1, 2 });
        m2.setRow(1, new double[] { 1, 2 });

        expected.setRow(0, new double[] { 3, 6 });
        expected.setRow(1, new double[] { 3, 6 });

        assertEquals(expected.toString(), m1.multiply(m2).toString());
    }

    @Test
    public void shouldMultiplyRectMatrices() {
        Mat m1 = new Mat(2, 3);
        Mat m2 = new Mat(3, 2);
        Mat expected = new Mat(2, 2);

        m1.setRow(0, new double[] { 1, 2, 3 });
        m1.setRow(1, new double[] { 1, 2, 3 });

        m2.setRow(0, new double[] { 1, 2 });
        m2.setRow(1, new double[] { 1, 2 });
        m2.setRow(2, new double[] { 1, 2 });

        expected.setRow(0, new double[] { 6, 12 });
        expected.setRow(1, new double[] { 6, 12 });

        assertEquals(expected.toString(), m1.multiply(m2).toString());
    }
}
