package com.maxsopilkov.redstorm.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maxsopilkov.redstorm.bayess.BayessProbability;
import com.maxsopilkov.redstorm.entities.CalculationResult;
import com.maxsopilkov.redstorm.entities.Country;
import com.maxsopilkov.redstorm.neuralnetwork.NeuralNetwork;
import com.maxsopilkov.redstorm.neuralnetwork.callback.INeuralNetworkCallback;
import com.maxsopilkov.redstorm.neuralnetwork.entity.Result;
import com.maxsopilkov.redstorm.neuralnetwork.utils.DataUtils;
import com.maxsopilkov.redstorm.repositories.CalculationResultRepository;
import com.maxsopilkov.redstorm.neuralnetwork.entity.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
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
     *        "aggression": 2,
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
         * TODO: 3) Teach it - DONE
         * TODO: 4) Collect results for all countries and send them to front - DONE
         */



        // Fetch all countries and map it using Jackson
        List<Country> countries = new ArrayList<>();
        try {
            countries = new ObjectMapper().readValue(json, new TypeReference<List<Country>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }

        //TODO: Dump data into DB with data from front
        //TODO: Make foreign key on country

        // Send data to bayess calculation
        List<BayessProbability> probabilities = BayessProbability.calculateBayess(countries);

        for (BayessProbability probability : probabilities) {
            CalculationResult res = new CalculationResult();
            res.setCountryName(probability.getCountryName());
            res.setBayessTrue(probability.getTrueProb());
            res.setBayessFalse(probability.getFalseProb());
            res.setNnTrue(0.4);
            res.setNnFalse(0.6);
            calculationResultRepository.save(res);
            System.out.println("Saved!");
        }

        System.out.println("SavedAll!");

        System.out.println("=========================Starting neural network sample...====================== ");
        float[][] x = DataUtils.readInputsFromFile("src\\main\\resources\\data\\x.txt");
        int[] t = DataUtils.readOutputsFromFile("src\\main\\resources\\data\\t.txt");

        //TODO: Change x
        NeuralNetwork neuralNetwork = new NeuralNetwork(x, t, new INeuralNetworkCallback() {
            @Override
            public void success(Result result) {
                float[] valueToPredict = new float[] {-0.205f, 0.780f};
                System.out.println("Success percentage: " + result.getSuccessPercentage());
                System.out.println("Predicted result: " + result.predictValue(valueToPredict));
            }

            @Override
            public void failure(Error error) {
                System.out.println("Error: " + error.getDescription());
            }
        });

        neuralNetwork.startLearning();


        //TODO: Write a query that will fetch latest forecasts
        // This returns a JSON or XML with the users
        return calculationResultRepository.findAll();
    }
}
