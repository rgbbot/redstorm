package com.maxsopilkov.redstorm.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maxsopilkov.redstorm.entities.Country;
import com.maxsopilkov.redstorm.entities.Result;
import com.maxsopilkov.redstorm.repositories.CalculationRepository;
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
    private CalculationRepository calculationRepository;

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
     *        "nuclear": true
     *  }
     * ]
     * @return
     */
    @PostMapping(path="/new")
    public @ResponseBody Iterable<Result> getParams(@RequestBody String json) {

        System.out.println(json);

        /**
         * TODO: 1) Receive post message - DONE
         * TODO: 2) Build the Bayessian Network
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
//        for(Country country : countries) {
//            System.out.println(country.toString());
//        }


        // This returns a JSON or XML with the users
        return calculationRepository.findAll();
    }
}
