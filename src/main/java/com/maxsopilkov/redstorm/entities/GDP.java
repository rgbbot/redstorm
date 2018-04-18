package com.maxsopilkov.redstorm.entities;

public class GDP {

    private Double year2012;
    private Double year2013;
    private Double year2014;
    private Double year2015;
    private Double year2016;

    public GDP() {}

    public GDP(Double year2012, Double year2013, Double year2014, Double year2015, Double year2016) {
        this.year2012 = year2012;
        this.year2013 = year2013;
        this.year2014 = year2014;
        this.year2015 = year2015;
        this.year2016 = year2016;
    }

    public Double getYear2012() {
        return year2012;
    }

    public void setYear2012(Double year2012) {
        this.year2012 = year2012;
    }

    public Double getYear2013() {
        return year2013;
    }

    public void setYear2013(Double year2013) {
        this.year2013 = year2013;
    }

    public Double getYear2014() {
        return year2014;
    }

    public void setYear2014(Double year2014) {
        this.year2014 = year2014;
    }

    public Double getYear2015() {
        return year2015;
    }

    public void setYear2015(Double year2015) {
        this.year2015 = year2015;
    }

    public Double getYear2016() {
        return year2016;
    }

    public void setYear2016(Double year2016) {
        this.year2016 = year2016;
    }

    @Override
    public String toString() {
        if (this != null) {
            String result = "";
            if (this.getYear2012() != null) {
                result += " - 2012: " + this.getYear2012().toString() + "\n";
            }
            if (this.getYear2013() != null) {
                result += " - 2013: " + this.getYear2013().toString() + "\n";
            }
            if (this.getYear2014() != null) {
                result += " - 2014: " + this.getYear2014().toString()+ "\n";
            }
            if (this.getYear2015() != null) {
                result += " - 2015: " + this.getYear2015().toString() + "\n";
            }
            if (this.getYear2016() != null) {
                result += " - 2016: " + this.getYear2016().toString() + "\n";
            }
            return result;
        } else {
            return "No Data.";
        }
    }
}
