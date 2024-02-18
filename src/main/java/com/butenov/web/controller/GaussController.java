package com.butenov.web.controller;

import com.butenov.web.model.MatrixWrapper;
import com.butenov.web.service.GaussService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GaussController {
    private final GaussService gaussService;

    @Autowired
    public GaussController(final GaussService gaussService) {
        this.gaussService = gaussService;
    }

    @GetMapping("/gauss")
    public String getGauss(@RequestParam("length") int length, @RequestParam("width") int width, Model model) {
        model.addAttribute("matrixWrapper", new MatrixWrapper(new double[length][width + 1]));
        return "gauss";
    }

    @PostMapping("/gauss")
    public String postGauss(@ModelAttribute("matrixWrapper") MatrixWrapper matrix, Model model) {
        double[] result = gaussService.calculateGauss(matrix.getCopy());
        double[] error = gaussService.calculateError(matrix.getCopy(), result);
        model.addAttribute("matrixWrapper", matrix);
        model.addAttribute("result", result);
        model.addAttribute("error", error);
        return "gauss";
    }
}
