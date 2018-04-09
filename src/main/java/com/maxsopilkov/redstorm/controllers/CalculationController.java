package com.maxsopilkov.redstorm.controllers;

import com.maxsopilkov.redstorm.entities.Result;
import com.maxsopilkov.redstorm.repositories.CalculationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/calculation")
public class CalculationController {
    @Autowired
    private CalculationRepository calculationRepository;

    @PostMapping(path="/new")
    public @ResponseBody Iterable<Result> getAllUsers() {

        /**
         * TODO: 1) Receive post message
         * TODO: 2) Build the Bayessian Network
         * TODO: 3) Teach it
         * TODO: 4) Collect results
         */


        // This returns a JSON or XML with the users
        return calculationRepository.findAll();
    }
}
