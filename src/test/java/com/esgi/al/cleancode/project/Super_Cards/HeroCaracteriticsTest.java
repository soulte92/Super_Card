package com.esgi.al.cleancode.project.Super_Cards;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HeroCaracteriticsTest {

    @Test
    void initCarateristicsBySpeciality() {
        Hero hero1 = new Hero("toto", Speciality.TANK, Rarety.COMMON);
        Hero newHero = HeroCaracteritics.initCarateristicsBySpeciality(hero1);
         assertEquals(newHero.hp, 1000);
         assertEquals(newHero.power, 100);
         assertEquals(newHero.armor, 20);

        newHero.speciality = Speciality.KILLER;
        newHero = HeroCaracteritics.initCarateristicsBySpeciality(newHero);
         assertEquals(newHero.hp, 800);
         assertEquals(newHero.power, 200);
         assertEquals(newHero.armor, 5);

        newHero.speciality = Speciality.MAGICIAN;
        newHero = HeroCaracteritics.initCarateristicsBySpeciality(newHero);
         assertEquals(newHero.hp, 700);
         assertEquals(newHero.power, 150);
         assertEquals(newHero.armor, 10);

         // TODO Test Error cases
    }

    @Test
    void enhaceCaracteriticsByRarety() {
        Hero hero1 = new Hero("toto", Speciality.TANK, Rarety.COMMON);
        Hero newHero = HeroCaracteritics.initCarateristicsBySpeciality(hero1);
        assertEquals(newHero.hp, 1000);
        assertEquals(newHero.power, 100);
        assertEquals(newHero.armor, 20);

        newHero.rarety = Rarety.RARE;
        newHero = HeroCaracteritics.initCarateristicsBySpeciality(newHero);
        newHero = HeroCaracteritics.enhaceCaracteriticsByRarety(newHero);
        assertEquals(newHero.hp, 1100);
        assertEquals(newHero.power, 110);
        assertEquals(newHero.armor, 22);

        newHero.rarety = Rarety.LEGENDARY;
        newHero = HeroCaracteritics.initCarateristicsBySpeciality(newHero);
        newHero = HeroCaracteritics.enhaceCaracteriticsByRarety(newHero);
        assertEquals(newHero.hp, 1200);
        assertEquals(newHero.power, 120);
        assertEquals(newHero.armor, 24);

        // TODO Test Error cases
    }

}