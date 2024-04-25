package com.butenov.web.service.impl;

import com.butenov.web.service.FunctionEstimationService;
import org.springframework.stereotype.Service;

@Service
public class FunctionEstimationServiceImpl implements FunctionEstimationService {
    public double[] approximate(double[] x, double[] y) {
        int n = x.length;
        double sumX = 0, sumY = 0, sumXY = 0, sumXX = 0;
        for (int i = 0; i < n; i++) {
            sumX += x[i];
            sumY += y[i];
            sumXY += x[i] * y[i];
            sumXX += x[i] * x[i];
        }
        double a = (n * sumXY - sumX * sumY) / (n * sumXX - sumX * sumX);
        double b = (sumY - a * sumX) / n;
        return new double[]{a, b};
    }

    @Override
    public double interpolate(double[] x, double[] y, double xi) {
        int n = x.length;
        double yi = 0;
        for (int i = 0; i < n; i++) {
            double p = 1;
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    p *= (xi - x[j]) / (x[i] - x[j]);
                }
            }
            yi += p * y[i];
        }
        return yi;
    }

    @Override
    public double[] approximateSecondDegree(double[] x, double[] y) {
        int n = x.length;
        double sumX = 0, sumY = 0, sumX2 = 0, sumX3 = 0, sumX4 = 0, sumXY = 0, sumX2Y = 0;

        for (int i = 0; i < n; i++) {
            sumX += x[i];
            sumY += y[i];
            sumX2 += Math.pow(x[i], 2);
            sumX3 += Math.pow(x[i], 3);
            sumX4 += Math.pow(x[i], 4);
            sumXY += x[i] * y[i];
            sumX2Y += Math.pow(x[i], 2) * y[i];
        }

        double[][] matrixA = {{n, sumX, sumX2}, {sumX, sumX2, sumX3}, {sumX2, sumX3, sumX4}, {sumY, sumXY, sumX2Y}};
        double[] matrixB = {sumY, sumXY, sumX2Y};

        return calculateLinearSystem(matrixA, matrixB);
    }

    @Override
    public double firstDegreeError(double[] x, double[] y, double[] polynomial) {
        double error = 0;
        int n = x.length;

        for (int i = 0; i < n; i++) {
            double yApprox = polynomial[0] * x[i] + polynomial[1];
            error += Math.pow(y[i] - yApprox, 2);
        }

        return error;
    }

    @Override
    public double secondDegreeError(double[] x, double[] y, double[] polynomial) {
        double error = 0;
        int n = x.length;

        for (int i = 0; i < n; i++) {
            double yApprox = polynomial[2] * Math.pow(x[i], 2) + polynomial[1] * x[i] + polynomial[0];
            error += Math.pow(y[i] - yApprox, 2);
        }

        return error;
    }

    private double[] calculateLinearSystem(double[][] A, double[] B) {
        int n = B.length;
        gaussianElimination(A, B, n);
        return backSubstitution(A, B, n);
    }

    private void gaussianElimination(double[][] A, double[] B, int n) {
        for (int p = 0; p < n; p++) {
            int max = findMaxRow(A, p, n);

            swapRows(A, B, p, max);

            if (Math.abs(A[p][p]) <= 1e-10) {
                throw new RuntimeException("Matrix is singular");
            }

            eliminateLowerTriangular(A, B, p, n);
        }
    }

    private int findMaxRow(double[][] A, int p, int n) {
        int max = p;
        for (int i = p + 1; i < n; i++) {
            if (Math.abs(A[i][p]) > Math.abs(A[max][p])) {
                max = i;
            }
        }
        return max;
    }

    private void swapRows(double[][] A, double[] B, int p, int max) {
        double[] tempRow = A[p];
        A[p] = A[max];
        A[max] = tempRow;

        double tempB = B[p];
        B[p] = B[max];
        B[max] = tempB;
    }

    private void eliminateLowerTriangular(double[][] A, double[] B, int p, int n) {
        for (int i = p + 1; i < n; i++) {
            double alpha = A[i][p] / A[p][p];
            B[i] -= alpha * B[p];
            for (int j = p; j < n; j++) {
                A[i][j] -= alpha * A[p][j];
            }
        }
    }

    private double[] backSubstitution(double[][] A, double[] B, int n) {
        double[] solution = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < n; j++) {
                sum += A[i][j] * solution[j];
            }
            solution[i] = (B[i] - sum) / A[i][i];
        }
        return solution;
    }
}
