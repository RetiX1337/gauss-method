package com.butenov.web.service;

public interface GaussService {
    double[] calculateGauss(double[][] matrix);

    double[] calculateError(double[][] augmentedMatrix, double[] x);

    boolean verifyResult(double[][] matrix, double[] x);
}
