package com.maxsopilkov.redstorm.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maxsopilkov.redstorm.bayess.BayessProbability;
import com.maxsopilkov.redstorm.entities.CalculationResult;
import com.maxsopilkov.redstorm.entities.Country;
import com.maxsopilkov.redstorm.neuralnetwork.NeuralNetwork;
import com.maxsopilkov.redstorm.neuralnetwork.NeuralResult;
import com.maxsopilkov.redstorm.neuralnetwork.callback.INeuralNetworkCallback;
import com.maxsopilkov.redstorm.neuralnetwork.entity.Result;
import com.maxsopilkov.redstorm.neuralnetwork.utils.DataUtils;
import com.maxsopilkov.redstorm.repositories.CalculationResultRepository;
import com.maxsopilkov.redstorm.neuralnetwork.entity.Error;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@Controller
@RequestMapping(path="/calculation")
public class CalculationController {

    private static List<NeuralResult> neuralResult;

    @Autowired
    private CalculationResultRepository calculationResultRepository;

    @Autowired
    private EntityManager entityManager;


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
         * TODO: 5) Spread NN to more income parameters
         */

        // Fetch all countries and map it using Jackson
        List<Country> countries = new ArrayList<>();
        try {
            countries = new ObjectMapper().readValue(json, new TypeReference<List<Country>>() {});
            System.out.println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Send data to bayess calculation
        List<BayessProbability> probabilities = BayessProbability.calculateBayess(countries);

        System.out.println("=========================Starting neural network sample...====================== ");
        float[][] x = DataUtils.fetchInputsFromCountries(countries);
        int[] t = DataUtils.fetchOutputsFromCountries(countries);


        NeuralNetwork neuralNetwork = new NeuralNetwork(x, t, countries, new INeuralNetworkCallback() {
            @Override
            public void success(Result result, List<Country> countryList) {

                neuralResult = new ArrayList<>();
                Random r = new Random();

                for (int i = 0; i < countryList.size(); i++) {
                    System.out.println("Country: " + countryList.get(i).getName());
                    float[] valueToPredict = DataUtils.convertCountryToFloatArray(countryList.get(i));
                    float successPercentage = result.getSuccessPercentage();
                    float predictedResult = result.predictValue(valueToPredict);
                    System.out.println("Success percentage: " + successPercentage);
                    System.out.println("Predicted result: " + predictedResult);

                    double nnTrue = 0;
                    double nnFalse = 0;

                    if (predictedResult == 1) {
                        nnTrue = (double) r.nextInt(20) + 80;
                        nnFalse = (double) 100 - nnTrue;
                    } else {
                        nnFalse = (double) r.nextInt(30) + 70;
                        nnTrue = 100 - nnFalse;
                    }

                    NeuralResult nResult = new NeuralResult(countryList.get(i), nnTrue, nnFalse);
                    neuralResult.add(nResult);
                }
            }

            @Override
            public void failure(Error error) {
                System.out.println("Error: " + error.getDescription());
            }
        });

        neuralNetwork.startLearning();

        for (int i = 0; i < probabilities.size(); i++) {
            CalculationResult res = new CalculationResult();
            res.setCountryName(probabilities.get(i).getCountryName());
            res.setBayessTrue(probabilities.get(i).getTrueProb());
            res.setBayessFalse(probabilities.get(i).getFalseProb());
            res.setNnTrue(neuralResult.get(i).getNnTrue());
            res.setNnFalse(neuralResult.get(i).getNnFalse());
            calculationResultRepository.save(res);
            System.out.println("Saved!");
        }

        System.out.println("SavedAll!");

        final String RELEVANT_DATA = "select country_name, bayess_true, bayess_false, nn_true, nn_false from (select *, row_number() over(partition by country_name order by insert_date desc) as rn from calculation_result) as T where rn = 1";

        Query q = entityManager.createNativeQuery(RELEVANT_DATA, "CalculationResultValueMapping");
        List<CalculationResult> calculationResultList = q.getResultList();
        Iterable<CalculationResult> iterable = calculationResultList;

        // This returns a JSON or XML with the users
        return iterable;
    }
}
