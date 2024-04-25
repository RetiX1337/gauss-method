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
}
