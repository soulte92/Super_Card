package com.esgi.al.cleancode.project.Super_Cards.domain;

import java.util.UUID;

public class HeroGenerator {
    public static Hero generateHeroFromSpeciality(String speciality){
        String rarety = RaretyGenerator.generateSilverCardRarety();
        return new Hero(HeroId.of(UUID.randomUUID()), "SUPER_TOTO", speciality, rarety);
    }
    public Hero generateRandomHeroWithSilverPack(HeroId heroId, String heroName){
        String speciality = SpecialityGenerator.generateRandomSpeciality();
        String rarety = RaretyGenerator.generateSilverCardRarety();
        Hero hero = new Hero(heroId, heroName, speciality, rarety);
        hero = HeroConfiguration.initCarateristicsBySpeciality(hero);
        return hero;
    }

    public Hero generateRandomHeroWithDiamondPack(HeroId heroId, String heroName){
        String speciality = SpecialityGenerator.generateRandomSpeciality();
        String rarety = RaretyGenerator.generateDiamondCardRarety();
        Hero hero = new Hero(heroId, heroName, speciality, rarety);
        hero = HeroConfiguration.initCarateristicsBySpeciality(hero);
        return hero;
    }
}
