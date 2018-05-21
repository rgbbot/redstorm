package com.maxsopilkov.redstorm.neuralnetwork;

import com.maxsopilkov.redstorm.entities.Country;

public class NeuralResult {
    private Country country;
    private double nnTrue;
    private double nnFalse;

    public NeuralResult(Country country, double nnTrue, double nnFalse) {
        this.country = country;
        this.nnTrue = nnTrue;
        this.nnFalse = nnFalse;
    }

    public NeuralResult() {}

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public double getNnTrue() {
        return nnTrue;
    }

    public void setNnTrue(double nnTrue) {
        this.nnTrue = nnTrue;
    }

    public double getNnFalse() {
        return nnFalse;
    }

    public void setNnFalse(double nnFalse) {
        this.nnFalse = nnFalse;
    }
}
