package com.maxsopilkov.redstorm.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maxsopilkov.redstorm.bayess.BayessProbability;
import com.maxsopilkov.redstorm.entities.CalculationResult;
import com.maxsopilkov.redstorm.entities.Country;
import com.maxsopilkov.redstorm.repositories.CalculationResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path="/calculation")
public class CalculationController {

    @Autowired
    private CalculationResultRepository calculationResultRepository;

    /**
     * Message Body
     * @param json - [
     *   {
     *        "name": "Afganistan",
     *        "gdp": {
     *          "year2012": 1873.15394552307,
     *          "year2013": 1913.160644,
     *          "year2014": 1933.399713,
     *          "year2015": 1926.357336,
     *          "year2016": 1944.117005
     *        },
     *        "unempl": 8.5,
     *        "hdi": 12,
     *        "ioh": 3.36,
     *        "army": 5000,
     *        "milexp": 3000,
     *        "resources": 30,
     *        "conflicts": 2,
     *        "nuclear": true,
     *        "inWar": false
     *  }
     * ]
     * @return
     */
    @PostMapping(path="/new")
    public @ResponseBody Iterable<CalculationResult> getParams(@RequestBody String json) {

        System.out.println(json);

        /**
         * TODO: 1) Receive post message - DONE
         * TODO: 2) Build the Bayessian Network - DONE
         * TODO: 3) Teach it
         * TODO: 4) Collect results
         */

        // Fetch all countries and map it using Jackson
        List<Country> countries = new ArrayList<>();
        try {
            countries = new ObjectMapper().readValue(json, new TypeReference<List<Country>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }

        //TODO: Dump data into DB with data from front

        // Send data to bayess calculation
        double[] probability = BayessProbability.run(countries);

        CalculationResult res = new CalculationResult();
        res.setBayessTrue(probability[0]);
        res.setBayessFalse(probability[1]);
        res.setNnTrue(0.4);
        res.setNnFalse(0.6);
        calculationResultRepository.save(res);
        System.out.println("Saved!");


        // This returns a JSON or XML with the users
        return calculationResultRepository.findAll();
    }
}
