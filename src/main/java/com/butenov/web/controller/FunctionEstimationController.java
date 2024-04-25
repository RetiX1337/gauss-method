package com.butenov.web.controller;

import com.butenov.web.service.FunctionEstimationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;

@Controller
public class FunctionEstimationController {

    @Autowired
    private FunctionEstimationService functionEstimationService;

    @GetMapping("/approximate")
    public String getApproximateForm() {
        return "estimation/approximate-form";
    }

    @GetMapping("/interpolation")
    public String getInterpolationForm() {
        return "estimation/interpolation-form";
    }

    @PostMapping("/approximate")
    public String approximate(@RequestParam double[] x, @RequestParam double[] y, Model model) {
        double[] approximateFirstDegree = functionEstimationService.approximate(x, y);
        double[] approximateSecondDegree = functionEstimationService.approximateSecondDegree(x, y);
        double firstDegreeError = functionEstimationService.firstDegreeError(x, y, approximateFirstDegree);
        double secondDegreeError = functionEstimationService.secondDegreeError(x, y, approximateSecondDegree);
        model.addAttribute("approximateFirstDegree", Arrays.toString(approximateFirstDegree));
        model.addAttribute("approximateSecondDegree", Arrays.toString(approximateSecondDegree));
        model.addAttribute("firstDegreeError", firstDegreeError);
        model.addAttribute("secondDegreeError", secondDegreeError);
        model.addAttribute("xFormatted", csvString(x));
        model.addAttribute("yFormatted", csvString(y));
        return "estimation/approximate-form";
    }

    @PostMapping("/interpolation")
    public String interpolate(@RequestParam double[] x, @RequestParam double[] y, @RequestParam double xi, Model model) {
        double result = functionEstimationService.interpolate(x, y, xi);
        model.addAttribute("result", result);
        model.addAttribute("x", x);
        model.addAttribute("y", y);
        model.addAttribute("xFormatted", csvString(x));
        model.addAttribute("yFormatted", csvString(y));
        model.addAttribute("xi", xi);
        return "estimation/interpolation-form";
    }

    private static String csvString(final double[] input) {
        return Arrays.toString(input).replace("[", "")
                .replace("]", "")
                .replace(" ", "");
    }
}
