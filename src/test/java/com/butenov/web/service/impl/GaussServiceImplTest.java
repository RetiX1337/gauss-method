package com.butenov.web.service.impl;

import com.butenov.web.config.Application;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = Application.class)
public class GaussServiceImplTest {
    @Autowired
    private GaussServiceImpl gaussService;

    @ParameterizedTest
    @MethodSource("getMatrix")
    public void testCalculateGauss_withRegularValues_shouldSucceed(final double[][] matrix) {
        final double[] actualResult = gaussService.calculateGauss(matrix);

        assertThat(gaussService.verifyResult(matrix, actualResult)).isTrue();
    }

    private static Stream<double[][]> getMatrix() {
        return Stream.of(
                new double[][] {{6.62, -2.65, -2.45, -2.57}, {5.21, -0.21, 2.13, 2.17}, {1.15, 4.21, -1.75, -1.90}},
                new double[][] {{6.62, -2.65, -2.45, -2.57, 1}, {5.21, -0.21, 2.13, 2.17, 2}, {1.15, 4.21, -1.75, -1.90, 3}},
                new double[][] {{6.62, -2.65, -2.45, -2.57, 1}, {5.21, -0.21, 2.13, 2.17, 2}, {1.15, 4.21, -1.75, -1.90, 3}, {5.21, -4, 2.13, 1.3, 12}},
                new double[][] {{2.00, 2.60, 1.93, 2.15}, {3.45, -0.58, 1.21, 1.55}, {1.25, 4.21, -1.95, 2.10}},
                new double[][] {{1.25, 2.25, -3.75, 2.00}, {1.75, -3.25, 2.05, -1.80}, {2.35, 1.25, 1.85, 6.70}});
    }
}
