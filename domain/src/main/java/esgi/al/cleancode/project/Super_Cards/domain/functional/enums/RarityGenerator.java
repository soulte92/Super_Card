package esgi.al.cleancode.project.Super_Cards.domain.functional.enums;

import java.util.HashMap;

public class RarityGenerator {
    public static String generateSilverCardRarity(){
        HashMap<String, Double> rarityProbabilities = new HashMap<String, Double>();
        rarityProbabilities.put(Rarity.COMMON.label, 0.75);
        rarityProbabilities.put(Rarity.RARE.label, 0.2);
        rarityProbabilities.put(Rarity.LEGENDARY.label, 0.05);

        ProbalityDistribution distribution = new ProbalityDistribution(rarityProbabilities);

        return (String) distribution.sample();
    }

    public static String generateDiamondCardRarity(){
        HashMap<String, Double> rarityProbabilities = new HashMap<String, Double>();
        rarityProbabilities.put(Rarity.LEGENDARY.label, 0.15);
        rarityProbabilities.put(Rarity.RARE.label, 0.35);
        rarityProbabilities.put(Rarity.COMMON.label, 0.5);

        ProbalityDistribution distribution = new ProbalityDistribution(rarityProbabilities);

        return (String) distribution.sample();
    }
}
