package com.esgi.al.cleancode.project.Super_Cards.domain;

public class HeroGenerator {
    public static Hero generateHeroFromSpeciality(String speciality){
        String rarety = RaretyGenerator.generateSilverCardRarety();
        return new Hero("SUPER_TOTO", speciality, rarety);
    }
    public static Hero generateRandomHeroWithSilverPack(){
        String speciality = SpecialityGenerator.generateRandomSpeciality();
        String rarety = RaretyGenerator.generateSilverCardRarety();
        return new Hero("SUPER_TOTO", speciality, rarety);
    }
}
