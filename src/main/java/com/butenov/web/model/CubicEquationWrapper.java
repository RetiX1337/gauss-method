package com.butenov.web.model;

public class CubicEquationWrapper {
    public static final int ARRAY_SIZE = 4;
    private double[] cubicEquationMultipliers;

    public CubicEquationWrapper() {
        this.cubicEquationMultipliers = new double[ARRAY_SIZE];
    }

    public CubicEquationWrapper(final double[] cubicEquationMultipliers) {
        verifyArraySize(cubicEquationMultipliers);
        this.cubicEquationMultipliers = cubicEquationMultipliers;
    }

    public double[] getCubicEquationMultipliers() {
        return cubicEquationMultipliers;
    }

    public double[] getCopy() {
        return cubicEquationMultipliers.clone();
    }

    public void setCubicEquationMultipliers(double[] cubicEquationMultipliers) {
        this.cubicEquationMultipliers = cubicEquationMultipliers;
    }

    private static void verifyArraySize(double[] cubicEquationMultipliers) {
        if (cubicEquationMultipliers.length != ARRAY_SIZE) {
            throw new IllegalArgumentException("Array size has to be exactly %d!".formatted(ARRAY_SIZE));
        }
    }
}
