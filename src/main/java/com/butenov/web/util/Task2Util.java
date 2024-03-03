package com.butenov.web.util;

public class Task2Util {
    public static double[] getTask2ResultVector(final int m) {
        return new double[]{3 * m, m - 6, 15 - m, m + 2};
    }

    public static double[][] getTask2Matrix() {
        return new double[][]
                {{5.0, 1.0, -1.0, 1.0},
                        {1.0, -4.0, 1.0, -1.0},
                        {-1.0, 1.0, 4.0, 1.0},
                        {1.0, 2.0, 1.0, -5.0}};
    }

    public static double[] getTask2ApproachingVector(final int m) {
        return new double[]{0.7 * m, 1, 2, 0.5};
    }
}
