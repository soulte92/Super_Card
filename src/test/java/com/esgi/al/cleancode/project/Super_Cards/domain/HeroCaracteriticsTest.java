package com.esgi.al.cleancode.project.Super_Cards.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class HeroCaracteriticsTest {

    @Test
    void should_initialize_hero_carateristics_by_speciality() {
        Hero hero1 = new Hero("toto", Speciality.TANK.label, Rarety.COMMON.label);
        Hero newHero = HeroConfiguration.initCarateristicsBySpeciality(hero1);
         assertEquals(newHero.hp, 1000);
         assertEquals(newHero.power, 100);
         assertEquals(newHero.armor, 20);

        newHero.speciality = Speciality.KILLER.label;
        newHero = HeroConfiguration.initCarateristicsBySpeciality(newHero);
         assertEquals(newHero.hp, 800);
         assertEquals(newHero.power, 200);
         assertEquals(newHero.armor, 5);

        newHero.speciality = Speciality.MAGICIAN.label;
        newHero = HeroConfiguration.initCarateristicsBySpeciality(newHero);
         assertEquals(newHero.hp, 700);
         assertEquals(newHero.power, 150);
         assertEquals(newHero.armor, 10);

    }

    @Test
    void should_not_initialize_hero_carateristics_by_speciality(){
        Hero hero1 = new Hero("toto", Speciality.TANK.label, Rarety.COMMON.label);
        Hero newHero = HeroConfiguration.initCarateristicsBySpeciality(hero1);
        fail();
    }

    @Test
    void should_enhace_hero_caracteritics_by_rarety() {
        Hero hero1 = new Hero("toto", Speciality.TANK.label, Rarety.COMMON.label);
        Hero newHero = HeroConfiguration.initCarateristicsBySpeciality(hero1);
        assertEquals(newHero.hp, 1000);
        assertEquals(newHero.power, 100);
        assertEquals(newHero.armor, 20);

        newHero.rarety = Rarety.RARE.label;
        newHero = HeroConfiguration.initCarateristicsBySpeciality(newHero);
        newHero = HeroConfiguration.enhaceCaracteriticsByRarety(newHero);
        assertEquals(newHero.hp, 1100);
        assertEquals(newHero.power, 110);
        assertEquals(newHero.armor, 22);

        newHero.rarety = Rarety.LEGENDARY.label;
        newHero = HeroConfiguration.initCarateristicsBySpeciality(newHero);
        newHero = HeroConfiguration.enhaceCaracteriticsByRarety(newHero);
        assertEquals(newHero.hp, 1200);
        assertEquals(newHero.power, 120);
        assertEquals(newHero.armor, 24);
    }

    @Test
    void should_not_enhace_hero_caracteritics_by_not_supported_rarety(){
        Hero hero1 = new Hero("toto", Speciality.TANK.label, "DEMON");
        Hero newHero = HeroConfiguration.initCarateristicsBySpeciality(hero1);
        newHero = HeroConfiguration.enhaceCaracteriticsByRarety(newHero);
        fail();
    }

}