package com.esgi.al.cleancode.project.Super_Cards;

import java.util.HashMap;

public class HeroCaracteritics {
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
        if(hero.speciality == Speciality.TANK){
            newHero.setCaracteristics(1000, 100, 20);
            //TODO • Puissance supplémentaires contre mages : 20
        }
        else if(hero.speciality == Speciality.KILLER){
            newHero.setCaracteristics(800, 200, 5);
            //TODO • Puissance supplémentaires contre tanks : 30
        }
        else if(hero.speciality == Speciality.MAGICIAN){
            newHero.setCaracteristics(700, 150, 10);
            //TODO • Puissance supplémentaires contre KILLER : 25
        }
        return newHero;
    }

    /*
    specialPowerMap = {
        tank: {
            mages: 20,
            tanks: 0,
            killer: 0
        },
        mages: {
            mages: 20,
            tanks: 0,
            killer: 0
        },
        killer: {
            mages: 20,
            tanks: 0,
            killer: 0
        }
    }


    * */

    public static Hero enhaceCaracteriticsByRarety(Hero hero){
        Hero newHero = hero.copy();
        if(hero.rarety == Rarety.COMMON){
            newHero.enhaceCaracteriticsByPerCent(0);
        }
        else if(hero.rarety == Rarety.RARE){
            newHero.enhaceCaracteriticsByPerCent(0.1);
        }
        else if(hero.rarety == Rarety.LEGENDARY){
            newHero.enhaceCaracteriticsByPerCent(0.2);
        }
        return newHero;
    }
}
