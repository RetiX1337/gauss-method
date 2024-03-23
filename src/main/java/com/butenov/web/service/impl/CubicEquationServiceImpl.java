package com.butenov.web.service.impl;

import com.butenov.web.service.CubicEquationService;
import org.springframework.stereotype.Service;

@Service
public class CubicEquationServiceImpl implements CubicEquationService {
    private static final double EPSILON = 1e-4;

    public double solveDichotomy(final double[] multipliers) {
        double a = multipliers[0];
        double b = multipliers[1];
        double c = multipliers[2];
        double d = multipliers[3];

        double left = -1000, right = 1000;
        while (right - left > EPSILON) {
            double mid = left + (right - left) / 2;
            if (f(a, b, c, d, mid) * f(a, b, c, d, left) <= 0) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return left;
    }

    private static double f(double a, double b, double c, double d, double x) {
        return a * Math.pow(x, 3) + b * Math.pow(x, 2) + c * x + d;
    }
}
