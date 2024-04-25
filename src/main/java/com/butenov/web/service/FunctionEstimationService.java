package com.butenov.web.service;

public interface FunctionEstimationService {
    double[] approximate(double[] x, double[] y);

    double interpolate(double[] x, double[] y, double xi);
}
