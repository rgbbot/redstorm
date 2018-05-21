package com.maxsopilkov.redstorm.bayess;

import cc.kave.repackaged.jayes.BayesNet;
import cc.kave.repackaged.jayes.BayesNode;
import cc.kave.repackaged.jayes.inference.IBayesInferer;
import cc.kave.repackaged.jayes.inference.junctionTree.JunctionTreeAlgorithm;
import com.maxsopilkov.redstorm.entities.Country;

import java.util.*;

public class BayessProbability {

    private String countryName;
    private double trueProb;
    private double falseProb;

    public BayessProbability() {}

    public BayessProbability(String countryName, double trueProb, double falseProb) {
        this.countryName = countryName;
        this.trueProb = trueProb;
        this.falseProb = falseProb;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public double getFalseProb() {
        return falseProb;
    }

    public void setFalseProb(double falseProb) {
        this.falseProb = falseProb;
    }

    public double getTrueProb() {
        return trueProb;
    }

    public void setTrueProb(double trueProb) {
        this.trueProb = trueProb;
    }

    public static List<BayessProbability> calculateBayess(List<Country> countries) {

        List<BayessProbability> bayessResults = new ArrayList<>();

        BayesNet net = new BayesNet();

        // Aggression
        BayesNode aggression = net.createNode("aggression");
        aggression.addOutcomes("0", "1-2", "3+");
        aggression.setProbabilities(0.7, 0.35, 0.05);

        // Resources
        BayesNode resources = net.createNode("resources");
        resources.addOutcomes("none", "few", "lot");
        resources.setParents(Arrays.asList(aggression));

        resources.setProbabilities(
                0.7, 0.25, 0.05, // conflicts == 0
                0.3, 0.4, 0.3, // conflicts == 1-2
                0.1, 0.3, 0.6 // conflicts == 3+
        );

        // GDP
        BayesNode gdp = net.createNode("gdp");
        gdp.addOutcomes("<5K", "5K-10K", "10K-30K", "30K-50K", "50K+");
        gdp.setParents(Arrays.asList(aggression));
        gdp.setProbabilities(
                0.2, 0.15, 0.15, 0.2, 0.3, // conflicts == 0
                0.15, 0.3, 0.3, 0.1, 0.15, // conflicts == 1-2
                0.3, 0.3, 0.2, 0.1, 0.1 // conflicts == 3+
        );

        // Nuclear
        BayesNode nuclear = net.createNode("nuclear");
        nuclear.addOutcomes("true", "false");
        nuclear.setParents(Arrays.asList(aggression));

        nuclear.setProbabilities(
                0.8, 0.2, // conflicts == 0
                0.6, 0.4, // conflicts == 1-2
                0.2, 0.8 // conflicts == 3+
        );

        // Army count
        BayesNode army = net.createNode("army");
        army.addOutcomes("<50K", "50K-200K", "200K-500K", "500K+");
        army.setParents(Arrays.asList(nuclear, resources));

        army.setProbabilities(
                // nuclear == true
                0.05, 0.1, 0.3, 0.55, // resources == none
                0.05, 0.1, 0.15, 0.7, // resources == few
                0.05, 0.05, 0.1, 0.8, // resources == lot
                // nuclear == false
                0.6, 0.24, 0.15, 0.01, // resources == none
                0.3, 0.25, 0.25, 0.2, // resources == few
                0.01, 0.04, 0.25, 0.7 // resources == lot
        );

        // Military expeditures
        BayesNode milexp = net.createNode("milexp");
        milexp.addOutcomes("<50", "50-200", "200-1000", "1000+");
        milexp.setParents(Arrays.asList(gdp));
        milexp.setProbabilities(
                0.34, 0.6, 0.05, 0.01, // gdp == <5K
                0.25, 0.55, 0.15, 0.05, // gdp == 5K-10K
                0.05, 0.35, 0.4, 0.2, // gdp == 10K-30K
                0.01, 0.1, 0.44, 0.45, // gdp == 30K-50K
                0.01, 0.04, 0.25, 0.7 // gdp == 50K+
        );

        // Human Development Index
        BayesNode hdi = net.createNode("hdi");
        hdi.addOutcomes("<50", "50-100", "100-150", "150+");
        hdi.setParents(Arrays.asList(army));
        hdi.setProbabilities(
                0.1, 0.2, 0.3, 0.4, // army == <50K
                0.2, 0.35, 0.3, 0.15, // army == 50K-200K
                0.3, 0.5, 0.15, 0.05, // army == 200K-500K
                0.45, 0.45, 0.09, 0.01 // army == 500K+
        );

        // Unemployment rate
        BayesNode unempl = net.createNode("unempl");
        unempl.addOutcomes("<7", "7-15", "15-30", "30+");
        unempl.setParents(Arrays.asList(milexp));
        unempl.setProbabilities(
                0.2, 0.25, 0.25, 0.3, // milexp == <50
                0.25, 0.25, 0.25, 0.25, // milexp == 50-200
                0.4, 0.3, 0.2, 0.1, // milexp == 200-1000
                0.5, 0.25, 0.2, 0.05 // milexp == 1000+
        );

        // IOH
        BayesNode ioh = net.createNode("ioh");
        ioh.addOutcomes("<3.5K", "3.5K-4.5K", "4.5K-6K", "6K-7K", "7K+");
        ioh.setParents(Arrays.asList(hdi, unempl));
        ioh.setProbabilities(
                // hdi = <50
                0.04, 0.06, 0.2, 0.3, 0.4, // unempl == <7
                0.04, 0.06, 0.25, 0.45, 0.3, // unempl == 7-15
                0.25, 0.35, 0.15, 0.15, 0.1, // unempl == 15-30
                0.4, 0.3, 0.2, 0.06, 0.04, // unempl == 30+
                // hdi = 50-100
                0.04, 0.06, 0.25, 0.35, 0.3, // unempl == <7
                0.1, 0.25, 0.35, 0.3, 0.1, // unempl == 7-15
                0.25, 0.35, 0.3, 0.15, 0.05, // unempl == 15-30
                0.3, 0.35, 0.25, 0.06, 0.04, // unempl == 30+
                // hdi = 100-150
                0.1, 0.25, 0.35, 0.3, 0.1, // unempl == <7
                0.25, 0.35, 0.3, 0.15, 0.05, // unempl == 7-15
                0.3, 0.35, 0.25, 0.06, 0.04, // unempl == 15-30
                0.4, 0.3, 0.23, 0.06, 0.01, // unempl == 30+
                // hdi = 150+
                0.25, 0.35, 0.3, 0.15, 0.05, // unempl == <7
                0.3, 0.35, 0.25, 0.06, 0.04, // unempl == 7-15
                0.4, 0.3, 0.23, 0.06, 0.01, // unempl == 15-30
                0.6, 0.3, 0.05, 0.04, 0.01 // unempl == 30+
        );

        // In War
        BayesNode war = net.createNode("war");
        war.addOutcomes("true", "false");
        war.setParents(Arrays.asList(ioh));
        war.setProbabilities(
                0.3, 0.7, // ioh == <3.5K
                0.2, 0.8, // ioh == 3.5K-4.5K
                0.1, 0.9, // ioh == 4.5K-6K
                0.05, 0.95, // ioh == 6K-7K
                0.01, 0.99  // ioh == 7K+
        );


        IBayesInferer inferer = new JunctionTreeAlgorithm();
        inferer.setNetwork(net);

        for (Country country : countries) {

            Map<BayesNode, String> evidence = new HashMap<>();

            //TODO: Do for current country, check if it makes forecast, do for all countries, dump forecast values into DB.

            evidence.put(aggression, BayessEvidenceAdapter.fetchAggression(country.getAggression()));
            evidence.put(resources, BayessEvidenceAdapter.fetchResources(country.getResources()));
            evidence.put(gdp, BayessEvidenceAdapter.fetchGDP(country.getGdp()));
            evidence.put(nuclear, BayessEvidenceAdapter.fetchNuclear(country.getNuclear()));
            evidence.put(army, BayessEvidenceAdapter.fetchArmyCount(country.getArmy()));
            evidence.put(milexp, BayessEvidenceAdapter.fetchMilitaryExp(country.getMilexp()));
            evidence.put(hdi, BayessEvidenceAdapter.fetchHDI(country.getHdi()));
            evidence.put(unempl, BayessEvidenceAdapter.fetchUnemployment(country.getUnempl()));
            evidence.put(ioh, BayessEvidenceAdapter.fetchIOH(country.getIoh()));


            inferer.setEvidence(evidence);

            double[] beliefsInWar = inferer.getBeliefs(war);

            BayessProbability bayessProbability = new BayessProbability(country.getName(), beliefsInWar[0], beliefsInWar[1]);
            bayessResults.add(bayessProbability);

        }

        return bayessResults;

    }
}
