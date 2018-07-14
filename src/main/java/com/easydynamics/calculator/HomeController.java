package com.easydynamics.calculator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller()
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("canvas", new CalculatorCanvas());
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String operation(@RequestParam(required = true) String operation, @ModelAttribute("canvas") CalculatorCanvas canvas) {

        try {
            canvas.setA(NumberService.normalize(canvas.getA()));
        } catch (IllegalArgumentException e) {
            canvas.setResult("Argument A is invalid!");
            return "index";
        }

        try {
            canvas.setB(NumberService.normalize(canvas.getB()));
        } catch (IllegalArgumentException e) {
            canvas.setResult("Argument B is invalid!");
            return "index";
        }

        if (operation.equalsIgnoreCase("add")) {
            canvas.setResult(CalculatorService.add(canvas.getA(), canvas.getB()));
        } else if (operation.equalsIgnoreCase("subtract")) {
            canvas.setResult(CalculatorService.subtract(canvas.getA(), canvas.getB()));
        } else if (operation.equalsIgnoreCase("multiply")) {
            canvas.setResult(CalculatorService.multiply(canvas.getA(), canvas.getB()));
        } else if (operation.equalsIgnoreCase("divide")) {
            canvas.setResult(CalculatorService.divide(canvas.getA(), canvas.getB()));
        } else {
            canvas.setResult("Unknown operation!");
        }

        return "index";
    }
}
