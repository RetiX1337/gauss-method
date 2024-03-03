package com.butenov.web.service.impl;

import com.butenov.web.service.LinearSystemService;
import org.springframework.stereotype.Service;

@Service
public class LinearSystemServiceImpl implements LinearSystemService {
    private static final double EPSILON = 0.005;

    @Override
    public double[] solveSimpleIterations(double[][] matrix, double[] resultVector,
                                          double[] approachingVector) {
        double[] previousVector;
        final int size = resultVector.length;
        do {
            previousVector = approachingVector.clone();
            for (int i = 0; i < size; i++) {
                double sum = resultVector[i];
                for (int j = 0; j < size; j++) {
                    if (i != j) {
                        sum -= matrix[i][j] * previousVector[j];
                    }
                }
                approachingVector[i] = sum / matrix[i][i];
            }
        } while (!isConverged(previousVector, approachingVector, size));

        return approachingVector;
    }

    private boolean isConverged(double[] previousVector, double[] currentVector, final int size) {
        for (int i = 0; i < size; i++) {
            if (Math.abs(previousVector[i] - currentVector[i]) > EPSILON) {
                return false;
            }
        }
        return true;
    }
}
