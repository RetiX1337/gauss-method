package com.butenov.web.controller;

import com.butenov.web.service.LinearSystemService;
import com.butenov.web.util.Task2Util;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/linear-system")
@Controller
public class LinearSystemController {
    private final LinearSystemService linearSystemService;

    public LinearSystemController(final LinearSystemService linearSystemService) {
        this.linearSystemService = linearSystemService;
    }

    @GetMapping
    public String getLinearSystem() {
        return "linear-system";
    }

    @PostMapping
    public String postLinearSystem(@RequestParam("mParameter") final Integer m, final Model model) {
        final double[][] matrix = Task2Util.getTask2Matrix();
        final double[] resultVector = Task2Util.getTask2ResultVector(m);
        final double[] approachingVector = Task2Util.getTask2ApproachingVector(m);

        final double[] result = linearSystemService.solveSimpleIterations(matrix, resultVector, approachingVector);
        model.addAttribute("result", result);
        return "linear-system";
    }
}
