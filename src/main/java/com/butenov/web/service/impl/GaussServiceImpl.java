package com.butenov.web.service.impl;

import com.butenov.web.service.GaussService;
import org.springframework.stereotype.Service;

@Service
public class GaussServiceImpl implements GaussService {

    @Override
    public double[] calculateGauss(double[][] matrix) {
        int n = matrix.length;

        for (int i = 0; i < n; i++) {
            int max = getColumnPivot(matrix, n, i);
            swapRows(matrix, i, max);
            pivotWithinMatrix(matrix, n, i);
        }

        return calculateBackSubstitution(matrix, n);
    }

    @Override
    public double[] calculateError(double[][] augmentedMatrix, double[] x) {
        int n = augmentedMatrix.length;
        double[][] matrix = new double[n][n];
        double[] b = new double[n];
        double[] errors = new double[n];

        separateMatrix(augmentedMatrix, n, matrix, b);
        calculateResidualVector(x, n, matrix, b, errors);

        return errors;
    }

    @Override
    public boolean verifyResult(double[][] augmentedMatrix, double[] result) {
        int n = augmentedMatrix.length;
        double epsilon = 1e-10;

        for (int i = 0; i < n; i++) {
            double sum = calculateRowSum(augmentedMatrix, result, i);

            if (Math.abs(sum - augmentedMatrix[i][n]) > epsilon) {
                return false;
            }
        }

        return true;
    }

    private double calculateRowSum(double[][] matrix, double[] vector, int rowIndex) {
        double sum = 0;
        for (int j = 0; j < vector.length; j++) {
            sum += matrix[rowIndex][j] * vector[j];
        }
        return sum;
    }

    private static double[] calculateBackSubstitution(double[][] matrix, int n) {
        double[] solution = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0;
            for (int j = i + 1; j < n; j++) {
                sum += matrix[i][j] * solution[j];
            }
            solution[i] = (matrix[i][n] - sum) / matrix[i][i];
        }
        return solution;
    }

    private static void pivotWithinMatrix(double[][] matrix, int n, int i) {
        for (int j = i + 1; j < n; j++) {
            double factor = matrix[j][i] / matrix[i][i];
            for (int k = i; k <= n; k++) {
                matrix[j][k] -= factor * matrix[i][k];
            }
        }
    }

    private static void swapRows(double[][] matrix, int i, int max) {
        double[] temp = matrix[i];
        matrix[i] = matrix[max];
        matrix[max] = temp;
    }

    private static int getColumnPivot(double[][] matrix, int n, int i) {
        int max = i;
        for (int j = i + 1; j < n; j++) {
            if (Math.abs(matrix[j][i]) > Math.abs(matrix[max][i])) {
                max = j;
            }
        }
        return max;
    }


    private static void separateMatrix(double[][] augmentedMatrix, int n, double[][] matrix, double[] b) {
        for (int i = 0; i < n; i++) {
            System.arraycopy(augmentedMatrix[i], 0, matrix[i], 0, n);
            b[i] = augmentedMatrix[i][n];
        }
    }

    private void calculateResidualVector(double[] x, int n, double[][] matrix, double[] b, double[] errors) {
        for (int i = 0; i < n; i++) {
            double Ax_i = calculateRowSum(matrix, x, i);
            double r_i = b[i] - Ax_i;

            errors[i] = Math.abs(r_i);
        }
    }
}
