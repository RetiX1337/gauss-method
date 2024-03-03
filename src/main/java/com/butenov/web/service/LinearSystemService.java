package com.butenov.web.service;

public interface LinearSystemService {
    double[] solveSimpleIterations(double[][] matrix, double[] resultVector, double[] approachingVector);
}
