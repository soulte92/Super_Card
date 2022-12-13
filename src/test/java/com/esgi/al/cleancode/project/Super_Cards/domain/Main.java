package com.esgi.al.cleancode.project.Super_Cards.domain;

import java.util.UUID;

public class Main {

    public static void main(String[] args) {
        Hero hero1 = new Hero(HeroId.of(UUID.randomUUID()), "toto", Speciality.MAGICIAN.label, RaretyGenerator.generateDiamondCardRarety());

        HeroConfiguration.initCarateristicsBySpeciality(hero1);
        System.out.println(hero1);
        HeroConfiguration.enhaceCaracteriticsByRarety(hero1);
        System.out.println(hero1);

        //TODO  Will serve for the card pack creation
//        HashMap<String, Double> raretyProbabilities = new HashMap<String, Double>();
//        raretyProbabilities.put("COMMON", 0.75);
//        raretyProbabilities.put("RARE", 0.35);
//        raretyProbabilities.put("LEGENDARY", 0.15);
//
//        ProbalityDistribution distribution = new ProbalityDistribution(raretyProbabilities);
//        System.out.println(distribution.sample());
    }
}
