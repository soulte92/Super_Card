package com.esgi.al.cleancode.project.Super_Cards.domain;

import com.esgi.al.cleancode.project.Super_Cards.kernel.ProbalityDistribution;

import java.util.HashMap;

public class RaretyGenerator {
    /*
Pack argent :
Jetons requis pour ouverture : 1
Nombre de cartes : 3
Probabilité de rareté :
	 	 Légendaire : 5%
	 	 Rare : 20%
	 	 Commune : 75%
Pack diamant :
Jetons requis pour ouverture : 2
Nombre de cartes : 5
Probabilité de rareté :
	 	 Légendaire : 15%
	 	 Rare : 35%
	 	 Commune : 50%
    */

    public static String generateSilverCardRarety(){
        HashMap<String, Double> raretyProbabilities = new HashMap<String, Double>();
        raretyProbabilities.put(Rarety.COMMON.label, 0.75);
        raretyProbabilities.put(Rarety.RARE.label, 0.2);
        raretyProbabilities.put(Rarety.LEGENDARY.label, 0.05);

        ProbalityDistribution distribution = new ProbalityDistribution(raretyProbabilities);

        return (String) distribution.sample();
    }

    public static String generateDiamondCardRarety(){
        HashMap<String, Double> raretyProbabilities = new HashMap<String, Double>();
        raretyProbabilities.put(Rarety.LEGENDARY.label, 0.15);
        raretyProbabilities.put(Rarety.RARE.label, 0.35);
        raretyProbabilities.put(Rarety.COMMON.label, 0.5);

        ProbalityDistribution distribution = new ProbalityDistribution(raretyProbabilities);

        return (String) distribution.sample();
    }
}
