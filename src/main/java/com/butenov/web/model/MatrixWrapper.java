package com.butenov.web.model;
import java.util.Arrays;

public class MatrixWrapper {
    private double[][] matrix;

    public MatrixWrapper(double[][] matrix) {
        this.matrix = matrix;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public double[][] getCopy() {
        return Arrays.stream(matrix)
                .map(double[]::clone)
                .toArray(double[][]::new);
    }
}
