package com.butenov.web.controller;

import com.butenov.web.model.CubicEquationWrapper;
import com.butenov.web.service.CubicEquationService;
import com.butenov.web.util.Task3Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/cubic-equation")
@Controller
public class CubicEquationController {
    private final CubicEquationService cubicEquationService;

    @Autowired
    public CubicEquationController(final CubicEquationService cubicEquationService) {
        this.cubicEquationService = cubicEquationService;
    }

    @GetMapping
    public String getCubicEquation(final Model model) {
        model.addAttribute(new CubicEquationWrapper(Task3Util.getTask3Multipliers()));
        return "cubic-equation";
    }

    @PostMapping
    public String postCubicEquation(@ModelAttribute("cubicEquationWrapper")
                                        final CubicEquationWrapper cubicEquationWrapper, final Model model) {
        final double solution = cubicEquationService.solveDichotomy(cubicEquationWrapper.getCopy());
        model.addAttribute("solution", solution);
        model.addAttribute(cubicEquationWrapper);
        return "cubic-equation";
    }
}
