package com.butenov.web.service;

public interface FunctionEstimationService {
    double[] approximate(double[] x, double[] y);

    double interpolate(double[] x, double[] y, double xi);

    double[] approximateSecondDegree(double[] x, double[] y);

    double firstDegreeError(double[] x, double[] y, double[] polynomial);

    double secondDegreeError(double[] x, double[] y, double[] polynomial);
}
