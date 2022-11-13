package com.esgi.al.cleancode.project.Super_Cards;

public class Main {

    public static void main(String[] args) {
        Hero hero1 = new Hero("toto", Speciality.MAGICIAN, Rarety.LEGENDARY);

        HeroCaracteritics.initCarateristicsBySpeciality(hero1);
        System.out.println(hero1);
        HeroCaracteritics.enhaceCaracteriticsByRarety(hero1);
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
