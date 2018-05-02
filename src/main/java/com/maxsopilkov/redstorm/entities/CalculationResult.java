package com.maxsopilkov.redstorm.entities;

import javax.persistence.*;

@Entity
@Table(name = "calculation_result")
@SqlResultSetMapping(
        name = "CalculationResultValueMapping",
        classes = @ConstructorResult(
                targetClass = CalculationResult.class,
                columns = {
                        @ColumnResult(name = "country_name", type = String.class),
                        @ColumnResult(name = "bayess_true", type = double.class),
                        @ColumnResult(name = "bayess_false", type = double.class),
                        @ColumnResult(name = "nn_true", type = double.class),
                        @ColumnResult(name = "nn_false", type = double.class)}))
public class CalculationResult {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String countryName;
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

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
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

    public CalculationResult(String countryName, double bayessTrue, double bayessFalse, double nnTrue, double nnFalse) {
        this.countryName = countryName;
        this.bayessTrue = bayessTrue;
        this.bayessFalse = bayessFalse;
        this.nnTrue = nnTrue;
        this.nnFalse = nnFalse;
    }

    public CalculationResult() {}
}
