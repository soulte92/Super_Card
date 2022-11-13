package com.esgi.al.cleancode.project.Super_Cards;

import java.util.HashMap;
import java.util.Objects;

public class Hero {
    protected String name;
    protected int hp;
    protected int xp;
    protected int power;
    protected int armor;
    protected Speciality speciality;
    protected Rarety rarety;
    protected int level;
    protected HashMap<String, HashMap<String, Integer>> specialPowerMap;

    public Hero(String name, int hp, int xp, int power, int armor, Speciality speciality, Rarety rarety, int level) {
        this.name = name;
        this.hp = hp;
        this.xp = xp;
        this.power = power;
        this.armor = armor;
        this.speciality = speciality;
        this.rarety = rarety;
        this.level = level;
        this.specialPowerMap = HeroCaracteritics.configSpecialPowerMap();
    }
    public Hero(String name, Speciality speciality, Rarety rarety) {
        this.name = name;
        this.xp = 0;
        this.speciality = speciality;
        this.rarety = rarety;
        this.level = 1;
        this.specialPowerMap = HeroCaracteritics.configSpecialPowerMap();
    }

    public Hero attack(Hero heroDefender){
        // heroFighter don't attack if heroDefender is dead
        if(heroDefender.isDead()) return heroDefender.copy();

        // Decrease heroDefencer hp
        int hpToRetrieve = (this.power + this.specialPowerMap.get(this.speciality.label).get(heroDefender.speciality.label)) - heroDefender.armor;
        heroDefender.retrieveHpFromHero(hpToRetrieve);

        // Increase heroFighter xp and update caracteristics
        if(heroDefender.isDead()){

            // Increase xp
            int xpToIncrease = 1;
            Hero newHero;
            this.increaseXpToHero(xpToIncrease);

            // Update heroFighter level
           this.updateHeroLevel();

            // Increase caracteristics
            double percent = 0.1;
            this.enhaceCaracteriticsByPerCent(percent);
            return heroDefender;
        }
        return heroDefender;
    }

    public void setCaracteristics(int newHp, int newPower, int newArmor){
        this.hp = newHp;
        this.power = newPower;
        this.armor = newArmor;
    }

    public void enhaceCaracteriticsByPerCent(double perCent){
        this.hp += this.hp * perCent;
        this.power += this.power * perCent;
        this.armor += this.armor * perCent;
    }

    public void retrieveHpFromHero(int hpToRetrieve){
        this.hp -= hpToRetrieve;
    }

    public void increaseXpToHero(int xpToIncrease){
        this.xp += xpToIncrease;
    }

    public void updateHeroLevel(){
        this.level = this.xp / 5;
    }

    public Hero copy(){
        Hero newHero = new Hero(this.name, this.hp, this.xp, this.power, this.armor, this.speciality, this.rarety, this.level);
        return newHero;
    }

    public boolean isDead(){
        return this.hp <= 0;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "name='" + name + '\'' +
                ", hp=" + hp +
                ", xp=" + xp +
                ", power=" + power +
                ", armor=" + armor +
                ", speciality=" + speciality +
                ", rarety=" + rarety +
                ", level=" + level +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hero)) return false;
        Hero hero = (Hero) o;
        return hp == hero.hp && xp == hero.xp && power == hero.power && armor == hero.armor && level == hero.level && name.equals(hero.name) && speciality == hero.speciality && rarety == hero.rarety;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, hp, xp, power, armor, speciality, rarety, level);
    }
}
