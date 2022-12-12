package com.esgi.al.cleancode.project.Super_Cards.domain;

import java.util.HashMap;
import java.util.Random;

public class HeroConfiguration {
    public static HashMap<String, HashMap<String, Integer>> configSpecialPowerMap(){
        HashMap<String, HashMap<String, Integer>> specialPowerMap = new HashMap<String, HashMap<String, Integer>>();

        HashMap<String, Integer> tank = new HashMap<String, Integer>();
        tank.put(Speciality.TANK.label, 0);
        tank.put(Speciality.KILLER.label, 0);
        tank.put(Speciality.MAGICIAN.label, 20);
        HashMap<String, Integer> killer = new HashMap<String, Integer>();
        killer.put(Speciality.TANK.label, 30);
        killer.put(Speciality.KILLER.label, 0);
        killer.put(Speciality.MAGICIAN.label, 0);
        HashMap<String, Integer> magician = new HashMap<String, Integer>();
        magician.put(Speciality.TANK.label, 0);
        magician.put(Speciality.KILLER.label, 25);
        magician.put(Speciality.MAGICIAN.label, 0);

        specialPowerMap.put(Speciality.TANK.label, tank);
        specialPowerMap.put(Speciality.KILLER.label, killer);
        specialPowerMap.put(Speciality.MAGICIAN.label, magician);
        
        return specialPowerMap;
    }

    public static Hero initCarateristicsBySpeciality(Hero hero){
        Hero newHero = hero.copy();
        if(hero.speciality.equals(Speciality.TANK.label)){
            newHero.setCaracteristics(1000, 100, 20);
        }
        else if(hero.speciality.equals(Speciality.KILLER.label)){
            newHero.setCaracteristics(800, 200, 5);
        }
        else if(hero.speciality.equals(Speciality.MAGICIAN.label)){
            newHero.setCaracteristics(700, 150, 10);
        }
        else{
            throw HeroConfigurationException.notSupportedSpeciality(hero.speciality);
        }
        return newHero;
    }

    public static Hero enhaceCaracteriticsByRarety(Hero hero){
        Hero newHero = hero.copy();
        if(hero.rarety.equals(Rarety.COMMON.label)){
            newHero.enhaceCaracteriticsByPerCent(0);
        }
        else if(hero.rarety.equals(Rarety.RARE.label)){
            newHero.enhaceCaracteriticsByPerCent(0.1);
        }
        else if(hero.rarety.equals(Rarety.LEGENDARY.label)){
            newHero.enhaceCaracteriticsByPerCent(0.2);
        }
        else{
            throw HeroConfigurationException.notSupportedRarety(hero.rarety);
        }
        return newHero;
    }
}
