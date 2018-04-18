package com.maxsopilkov.redstorm.entities;

import javax.persistence.*;

@Entity
@Table(name = "calculation_result")
public class CalculationResult {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private double bayessTrue;
    private double bayessFalse;
    private double nnTrue;
    private double nnFalse;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getBayessTrue() {
        return bayessTrue;
    }

    public void setBayessTrue(double bayessTrue) {
        this.bayessTrue = bayessTrue;
    }

    public double getBayessFalse() {
        return bayessFalse;
    }

    public void setBayessFalse(double bayessFalse) {
        this.bayessFalse = bayessFalse;
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
