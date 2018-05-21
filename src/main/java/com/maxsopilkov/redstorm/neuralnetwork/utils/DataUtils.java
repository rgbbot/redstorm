package com.maxsopilkov.redstorm.neuralnetwork.utils;

import com.maxsopilkov.redstorm.entities.Country;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Utils for read external data and save it in memory
 * @author jlmd
 */
public class DataUtils {
    /**
     * Parse input txt data separated by "," in two-dimensional float array
     * @param fileURI
     * @return float[][] with data
     */
    //TODO: Insert more values in txt.
    //TODO: Extend fArray
    //TODO: Extend array
    public static float[][] readInputsFromFile(String fileURI){
        float[][] fArray = new float[0][];

        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(fileURI), StandardCharsets.UTF_8);
            fArray = new float[lines.size()][];

            for(int i = 0; i<lines.size(); i++){
                fArray[i] = convertStringArrayToFloatArray(lines.get(i).split(","));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

       return fArray;
    }

    public static float[][] fetchInputsFromCountries(List<Country> countries) {
        float[][] fArray;

        fArray = new float[countries.size()][];
        for(int i = 0; i < countries.size(); i++){
            fArray[i] = convertCountryToFloatArray(countries.get(i));
        }

        return fArray;
    }



    /**
     * Parse output txt data separated by "," in two-dimensional float array
     * @param fileURI
     * @return int[] with data
     */
    public static int[] readOutputsFromFile(String fileURI){
        int[] iArray = new int[0];

        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(fileURI), StandardCharsets.UTF_8);
            iArray = new int[lines.size()];

            for(int i = 0; i<lines.size(); i++){
                iArray[i] = Integer.parseInt(lines.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return iArray;
    }

    public static int[] fetchOutputsFromCountries(List<Country> countries){
        int[] iArray;

        iArray = new int[countries.size()];

        for(int i = 0; i< countries.size(); i++) {
            iArray[i] = (countries.get(i).getInWar()) ? 1 : 0;
        }

        return iArray;
    }

    /**
     * Convert array of strings to array of float
     * @param num Array of string
     * @return array of float
     */
    private static float[] convertStringArrayToFloatArray(String[] num){
        if (num != null) {
            float fArray[] = new float[num.length];
            for (int i = 0; i <num.length; i++) {
                fArray[i] = Float.parseFloat(num[i]);
            }
            return fArray;
        }
        return null;
    }

    public static float[] convertCountryToFloatArray(Country country){
        float fArray[];
        float gdp12 = country.getGdp().getYear2012().floatValue();
        float gdp13 = country.getGdp().getYear2013().floatValue();
        float gdp14 = country.getGdp().getYear2014().floatValue();
        float gdp15 = country.getGdp().getYear2015().floatValue();
        float gdp16 = country.getGdp().getYear2016().floatValue();
        float unempl = country.getUnempl().floatValue();
        float hdi = country.getHdi().floatValue();
        float ioh = country.getIoh().floatValue();
        float army = country.getArmy().floatValue();
        float milexp = country.getMilexp().floatValue();
        float resources = country.getResources().floatValue();
        float aggression = country.getAggression().floatValue();
        float nuclear = (country.getNuclear() == true) ? (float) 1 : (float) 0;

        fArray = new float[] {gdp12, gdp13, gdp14, gdp15, gdp16, unempl, hdi, ioh, army, milexp, resources, aggression, nuclear};
//        fArray = new float[] {ioh, nuclear};
        return fArray;
    }

}
