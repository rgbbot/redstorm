package com.maxsopilkov.redstorm.entities;

public class GDP {

    private String year2012;
    private String year2013;
    private String year2014;
    private String year2015;
    private String year2016;

    public GDP() {}

    public GDP(String year2012, String year2013, String year2014, String year2015, String year2016) {
        this.year2012 = year2012;
        this.year2013 = year2013;
        this.year2014 = year2014;
        this.year2015 = year2015;
        this.year2016 = year2016;
    }

    public String getYear2012() {
        return year2012;
    }

    public void setYear2012(String year2012) {
        this.year2012 = year2012;
    }

    public String getYear2013() {
        return year2013;
    }

    public void setYear2013(String year2013) {
        this.year2013 = year2013;
    }

    public String getYear2014() {
        return year2014;
    }

    public void setYear2014(String year2014) {
        this.year2014 = year2014;
    }

    public String getYear2015() {
        return year2015;
    }

    public void setYear2015(String year2015) {
        this.year2015 = year2015;
    }

    public String getYear2016() {
        return year2016;
    }

    public void setYear2016(String year2016) {
        this.year2016 = year2016;
    }

    @Override
    public String toString() {
        if (this != null) {
            String result = "";
            if (this.getYear2012() != null) {
                result += " - 2012: " + this.getYear2012() + "\n";
            }
            if (this.getYear2013() != null) {
                result += " - 2013: " + this.getYear2013() + "\n";
            }
            if (this.getYear2014() != null) {
                result += " - 2014: " + this.getYear2014() + "\n";
            }
            if (this.getYear2015() != null) {
                result += " - 2015: " + this.getYear2015() + "\n";
            }
            if (this.getYear2016() != null) {
                result += " - 2016: " + this.getYear2016() + "\n";
            }
            return result;
        } else {
            return "No Data.";
        }
    }
}
