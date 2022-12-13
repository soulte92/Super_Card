package com.esgi.al.cleancode.project.Super_Cards.domain;

import java.util.UUID;

public class HeroGenerator {
    public static Hero generateHeroFromSpeciality(String speciality){
        String rarety = RaretyGenerator.generateSilverCardRarety();
        return new Hero(HeroId.of(UUID.randomUUID()), "SUPER_TOTO", speciality, rarety);
    }
    public static Hero generateRandomHeroWithSilverPack(){
        String speciality = SpecialityGenerator.generateRandomSpeciality();
        String rarety = RaretyGenerator.generateSilverCardRarety();
        Hero hero = new Hero(HeroId.of(UUID.randomUUID()), "SUPER_TOTO", speciality, rarety);
        hero = HeroConfiguration.initCarateristicsBySpeciality(hero);
        return hero;
    }
}
