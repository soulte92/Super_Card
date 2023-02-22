package esgi.al.cleancode.project.Super_Cards.domain.functional.service;

import esgi.al.cleancode.project.Super_Cards.domain.exceptions.HeroException;
import esgi.al.cleancode.project.Super_Cards.domain.functional.enums.Rarity;
import esgi.al.cleancode.project.Super_Cards.domain.functional.enums.Speciality;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Hero;

import java.util.HashMap;

public class HeroUtils {
    public static boolean isHeroDead(Hero hero){
        return hero.getHp() <= 0;
    }
    public static Hero retrieveHeroHp(Hero hero, double hpToRetrieve){
        return Hero.builder().heroId(hero.getHeroId())
                .name(hero.getName())
                .xp(hero.getXp())
                .level(hero.getLevel())
                .speciality(hero.getSpeciality())
                .rarity(hero.getRarity())
                .hp(hero.getHp() - hpToRetrieve)
                .power(hero.getPower())
                .armor(hero.getArmor()).build();
    }
    public static Hero increaseHeroXp(Hero hero, int xpToIncrease){
        return Hero.builder().heroId(hero.getHeroId())
                .name(hero.getName())
                .xp(hero.getXp() + xpToIncrease)
                .level(hero.getLevel())
                .speciality(hero.getSpeciality())
                .rarity(hero.getRarity())
                .hp(hero.getHp())
                .power(hero.getPower())
                .armor(hero.getArmor()).build();
    }
    public static Hero updateHeroLevel(Hero hero){
        return Hero.builder().heroId(hero.getHeroId())
                .name(hero.getName())
                .xp(hero.getXp())
                .level(hero.getXp() / 5)
                .speciality(hero.getSpeciality())
                .rarity(hero.getRarity())
                .hp(hero.getHp())
                .power(hero.getPower())
                .armor(hero.getArmor()).build();
    }

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

    public static Hero initCharacteristicsBySpeciality(String name, String speciality, String rarity){
        Hero newHero;
        if(speciality.equals(Speciality.TANK.label)){
            newHero = Hero.builder()
                    .name(name)
                    .speciality(speciality)
                    .rarity(rarity).hp(1000.).power(100.).armor(20.).build();
        }
        else if(speciality.equals(Speciality.KILLER.label)){
            newHero = Hero.builder()
                    .name(name)
                    .speciality(speciality)
                    .rarity(rarity).hp(800.).power(200.).armor(5.).build();
        }
        else if(speciality.equals(Speciality.MAGICIAN.label)){
            newHero = Hero.builder()
                    .name(name)
                    .speciality(speciality)
                    .rarity(rarity).hp(700.).power(150.).armor(10.).build();
        }
        else{
            throw HeroException.notSupportedSpeciality(speciality);
        }
        return newHero;
    }

    public static Hero enhaceCharacteriticsByRarity(Hero hero){
        Hero newHero;
        if(hero.rarity.equals(Rarity.COMMON.label)){
            newHero = enhanceCharacteristicsByPerCent( hero, 0.);
        }
        else if(hero.rarity.equals(Rarity.RARE.label)){
            newHero = enhanceCharacteristicsByPerCent( hero, 0.1);
        }
        else if(hero.rarity.equals(Rarity.LEGENDARY.label)){
            newHero = enhanceCharacteristicsByPerCent( hero, 0.2);
        }
        else{
            throw HeroException.notSupportedRarety(hero.rarity);
        }
        return newHero;
    }

    public static Hero enhanceCharacteristicsByPerCent(Hero hero, double perCent){
        if ((0>perCent) || (perCent>1)){
            throw HeroException.enhaceCaracteriticsByPerCentException(perCent);
        }
        return Hero.builder().heroId(hero.getHeroId())
                .name(hero.name)
                .xp(hero.xp)
                .level(hero.level)
                .speciality(hero.speciality)
                .rarity(hero.rarity)
                .hp(hero.hp + (hero.hp * perCent))
                .power(hero.power + (hero.power * perCent))
                .armor(hero.armor + (hero.armor * perCent)).build();
    }
}
