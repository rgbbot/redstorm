package com.maxsopilkov.redstorm.bayess;

import com.maxsopilkov.redstorm.entities.GDP;

public final class BayessEvidenceAdapter {
    private BayessEvidenceAdapter() {}

    public static String fetchConflicts(Integer num) {
        if (num == 0) {
            return "0";
        } else if (num >= 1 || num < 3) {
            return "1-2";
        } else {
            return "3+";
        }
    }

    public static String fetchResources(Integer num) {
        if (num >= 0 || num < 3) {
            return "none";
        } else if (num >= 3 || num < 7) {
            return "few";
        } else {
            return "lot";
        }
    }

    public static String fetchGDP(GDP gdp) {
        //TODO: Regression and normalize
        Double param = gdp.getYear2012();
        if (param < 5000) {
            return "<5K";
        } else if (param >= 5000 || param < 10000) {
            return "5K-10K";
        } else if (param >= 10000 || param < 30000){
            return "10K-30K";
        } else if (param >= 30000 || param < 50000){
            return "30K-50K";
        } else {
            return "50K+";
        }
    }

    public static String fetchNuclear(Boolean isNuclear) {
        if (isNuclear) {
            return "true";
        } else {
            return "false";
        }
    }

    public static String fetchArmyCount(Integer armyCount) {
        if (armyCount < 50000) {
            return "<50K";
        } else if (armyCount >= 50000 || armyCount < 200000) {
            return "50K-200K";
        } else if (armyCount >= 200000 || armyCount < 500000){
            return "200K-500K";
        } else {
            return "500K+";
        }
    }

    public static String fetchMilitaryExp(Double milexp) {
        if (milexp < 50) {
            return "<50";
        } else if (milexp >= 50 || milexp < 200) {
            return "50-200";
        } else if (milexp >= 200 || milexp < 1000){
            return "200-1000";
        } else {
            return "1000+";
        }
    }

    public static String fetchHDI(Double hdi) {
        if (hdi < 50) {
            return "<50";
        } else if (hdi >= 50 || hdi < 100) {
            return "50-100";
        } else if (hdi >= 100 || hdi < 150){
            return "100-150";
        } else {
            return "150+";
        }
    }

    public static String fetchUnemployment(Double unempl) {
        if (unempl < 7) {
            return "<7";
        } else if (unempl >= 7 || unempl < 15) {
            return "7-15";
        } else if (unempl >= 15 || unempl < 30){
            return "15-30";
        } else {
            return "30+";
        }
    }

    public static String fetchIOH(Double ioh) {
        if (ioh < 3500) {
            return "<3.5K";
        } else if (ioh >= 3500 || ioh < 4500) {
            return "3.5K-4.5K";
        } else if (ioh >= 4500 || ioh < 6000){
            return "4.5K-6K";
        } else if (ioh >= 6000 || ioh < 7000){
            return "6K-7K";
        } else {
            return "7K+";
        }
    }

    public static String fetchInWar(Boolean isInWar) {
        if (isInWar) {
            return "true";
        } else {
            return "false";
        }
    }
}
