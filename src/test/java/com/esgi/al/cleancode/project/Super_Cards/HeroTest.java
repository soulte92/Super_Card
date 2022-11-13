package com.esgi.al.cleancode.project.Super_Cards;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeroTest {

    @Test
    void attackWithDeadHeroDefender() {
        Hero hero1 = new Hero("super-boy", 100, 30, 40, 5, Speciality.TANK, Rarety.COMMON, 6);
        Hero hero2 = new Hero("super-girl", 0, 30, 20, 10, Speciality.TANK, Rarety.COMMON, 6);

        Hero newHero2 = hero1.attack(hero2);
        assertEquals(hero2, newHero2);
    }

    @Test
    void attackWithNormalHeroDefenderWithoutWinning() {
        Hero hero1 = new Hero("super-boy", 100, 30, 40, 5, Speciality.TANK, Rarety.COMMON, 6);
        Hero hero2 = new Hero("super-girl", 100, 30, 20, 10, Speciality.TANK, Rarety.COMMON, 6);
        Hero hero3 = new Hero("super-dog", 100, 30, 20, 10, Speciality.MAGICIAN, Rarety.COMMON, 6);

        hero2 = hero1.attack(hero2);
        assertEquals(hero2.hp, 70);

        hero3 = hero1.attack(hero3);
        assertEquals(hero3.hp, 50);
    }

    @Test
    void attackWithNormalHeroDefenderWithWinning() {
        Hero hero1 = new Hero("super-boy", 100, 30, 40, 5, Speciality.TANK, Rarety.COMMON, 6);
        Hero hero2 = new Hero("super-girl", 100, 30, 20, 10, Speciality.TANK, Rarety.COMMON, 6);

        hero2 = hero1.attack(hero2);
        hero2 = hero1.attack(hero2);
        hero2 = hero1.attack(hero2);
        hero2 = hero1.attack(hero2);
        hero2 = hero1.attack(hero2);
//        System.out.println(hero2);

        // Test that HeroDefender is dead
        assertTrue(hero2.isDead());

        // Test increased XP of heroAttaker
        assertEquals(hero1.xp, 31);

        // Test No level update because heroDefender dead before getting last xp
        assertEquals(hero1.level, 6);

        // Test enhace Caracteritics of heroAttaker
        assertEquals(hero1.hp, 110);
        assertEquals(hero1.power, 44);
        assertEquals(hero1.armor, 5);
    }

    @Test
    void enhaceCaracteriticsByPerCent() {
        Hero hero2 = new Hero("super-boy", 100, 30, 20, 5, Speciality.TANK, Rarety.COMMON, 6);
        hero2.enhaceCaracteriticsByPerCent(0.5);

        assertEquals(hero2.hp, 150);
        assertEquals(hero2.power, 30);
        assertEquals(hero2.armor, 7);
    }

    @Test
    void retrieveHp() {
        Hero hero2 = new Hero("super-boy", 100, 30, 20, 5, Speciality.TANK, Rarety.COMMON, 6);
        hero2.retrieveHpFromHero(30);

        assertEquals(hero2.hp, 70);
    }

    @Test
    void increaseXp() {
        Hero hero2 = new Hero("super-boy", 100, 30, 20, 5, Speciality.TANK, Rarety.COMMON, 6);
        // Before increase XP
        assertEquals(hero2.xp, 30);

        // After increase XP
        hero2.increaseXpToHero(15);
        assertEquals(hero2.xp, 45);
    }

    @Test
    void updateLevel() {
        Hero hero2 = new Hero("super-boy", 100, 30, 20, 5, Speciality.TANK, Rarety.COMMON, 6);
        // Before updating the level
        assertEquals(hero2.level, 6);

        // After updating the level
        hero2.increaseXpToHero(15);
        hero2.updateHeroLevel();
        assertEquals(hero2.level, 9);
    }

    @Test
    void copy() {
        Hero hero2 = new Hero("super-boy", 100, 30, 20, 5, Speciality.TANK, Rarety.COMMON, 6);
        Hero newHero = hero2.copy();

        assertEquals(newHero.name, hero2.name);
        assertEquals(newHero.hp, hero2.hp);
        assertEquals(newHero.xp, hero2.xp);
        assertEquals(newHero.power, hero2.power);
        assertEquals(newHero.armor, hero2.armor);
        assertEquals(newHero.level, hero2.level);
        assertEquals(newHero.rarety, hero2.rarety);
        assertEquals(newHero.speciality, hero2.speciality);
    }

    @Test
    void isDead() {
        Hero hero2 = new Hero("super-boy", 0, 30, 20, 5, Speciality.TANK, Rarety.COMMON, 6);
        assertTrue(hero2.isDead());
    }

    @Test
    void isNotDead() {
        Hero hero2 = new Hero("super-boy", 1, 30, 20, 5, Speciality.TANK, Rarety.COMMON, 6);
        assertFalse(hero2.isDead());
    }

    @Test
    void Equals() {
        Hero hero2 = new Hero("super-boy", 1, 30, 20, 5, Speciality.TANK, Rarety.COMMON, 6);
        Hero newHero = hero2.copy();

        assertTrue(hero2.equals(newHero));
    }
}