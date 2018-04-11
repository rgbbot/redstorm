package com.maxsopilkov.redstorm.bayess;

import cc.kave.repackaged.jayes.BayesNet;
import cc.kave.repackaged.jayes.BayesNode;
import cc.kave.repackaged.jayes.inference.IBayesInferer;
import cc.kave.repackaged.jayes.inference.junctionTree.JunctionTreeAlgorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BayessProbability {



    public static void run() {
        BayesNet net = new BayesNet();
        BayesNode a = net.createNode("a");
        a.addOutcomes("true", "false");
        a.setProbabilities(0.2, 0.8);

        BayesNode b = net.createNode("b");
        b.addOutcomes("one", "two", "three");
        b.setParents(Arrays.asList(a));

        b.setProbabilities(
                0.1, 0.4, 0.5, // a == true
                0.3, 0.4, 0.3 // a == false
        );

        BayesNode c = net.createNode("c");
        c.addOutcomes("true", "false");
        c.setParents(Arrays.asList(a, b));
        c.setProbabilities(
                // a == true
                0.1, 0.9, // b == one
                0.0, 1.0, // b == two
                0.5, 0.5, // b == three
                // a == false
                0.2, 0.8, // b == one
                0.0, 1.0, // b == two
                0.7, 0.3 // b == three
        );

        IBayesInferer inferer = new JunctionTreeAlgorithm();
        inferer.setNetwork(net);

        Map<BayesNode,String> evidence = new HashMap<BayesNode,String>();
        evidence.put(a, "false");
        evidence.put(b, "three");
        inferer.setEvidence(evidence);

        double[] beliefsC = inferer.getBeliefs(c);

        System.out.println("P(c | a = “false”, b =”three”): " + Arrays.toString(beliefsC));
    }
}
