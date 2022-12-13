package com.esgi.al.cleancode.project.Super_Cards.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class HeroTest {

    @Test
    void should_not_attack_dead_hero() {
        Hero hero1 = new Hero(HeroId.of(UUID.randomUUID()), "super-boy", 100, 30, 40, 5, Speciality.TANK.label, Rarety.COMMON.label, 6);
        Hero hero2 = new Hero(HeroId.of(UUID.randomUUID()), "super-girl", 0, 30, 20, 10, Speciality.TANK.label, Rarety.COMMON.label, 6);

        Hero newHero2 = hero1.attack(hero2);
        assertEquals(hero2, newHero2);
    }

    @Test
    void should_not_win_when_attack_hero() {
        Hero hero1 = new Hero(HeroId.of(UUID.randomUUID()), "super-boy", 100, 30, 40, 5, Speciality.TANK.label, Rarety.COMMON.label, 6);
        Hero hero2 = new Hero(HeroId.of(UUID.randomUUID()), "super-girl", 100, 30, 20, 10, Speciality.TANK.label, Rarety.COMMON.label, 6);
        Hero hero3 = new Hero(HeroId.of(UUID.randomUUID()), "super-dog", 100, 30, 20, 10, Speciality.MAGICIAN.label, Rarety.COMMON.label, 6);

        hero2 = hero1.attack(hero2);
        assertEquals(hero2.hp, 70);

        hero3 = hero1.attack(hero3);
        assertEquals(hero3.hp, 50);
    }

    @Test
    void should_win_when_attack_hero() {
        Hero hero1 = new Hero(HeroId.of(UUID.randomUUID()), "super-boy", 100, 30, 40, 5, Speciality.TANK.label, Rarety.COMMON.label, 6);
        Hero hero2 = new Hero(HeroId.of(UUID.randomUUID()), "super-girl", 100, 30, 20, 10, Speciality.TANK.label, Rarety.COMMON.label, 6);

        hero2 = hero1.attack(hero2);
        hero2 = hero1.attack(hero2);
        hero2 = hero1.attack(hero2);
        hero2 = hero1.attack(hero2);
        hero2 = hero1.attack(hero2);

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
    void should_enhace_hero_caracteritics_by_perCent() {
        Hero hero2 = new Hero(HeroId.of(UUID.randomUUID()), "super-boy", 100, 30, 20, 5, Speciality.TANK.label, Rarety.COMMON.label, 6);
        hero2.enhaceCaracteriticsByPerCent(0.5);

        assertEquals(hero2.hp, 150);
        assertEquals(hero2.power, 30);
        assertEquals(hero2.armor, 7);
    }

    @Test
    void should_not_enhace_hero_caracteritics_by_perCent(){
        Hero hero2 = new Hero(HeroId.of(UUID.randomUUID()), "super-boy", 100, 30, 20, 5, Speciality.TANK.label, Rarety.COMMON.label, 6);
        hero2.enhaceCaracteriticsByPerCent(0.);

        assertEquals(hero2.hp, 100);
        assertEquals(hero2.power, 20);
        assertEquals(hero2.armor, 5);
    }

    @Test
    void should_throw_when_enhace_hero_caracteritics_with_negative_perCent(){
        Hero hero2 = new Hero(HeroId.of(UUID.randomUUID()), "super-boy", 100, 30, 20, 5, Speciality.TANK.label, Rarety.COMMON.label, 6);
        hero2 = HeroConfiguration.initCarateristicsBySpeciality(hero2);
        Hero finalHero = hero2;

        // Should throw exception when percent is not in ]0,1[
        Assertions.assertThrows(HeroException.class, () -> {
            finalHero.enhaceCaracteriticsByPerCent(-15.);
        });
        Assertions.assertThrows(HeroException.class, () -> {
            finalHero.enhaceCaracteriticsByPerCent(5.);
        });
    }

    @Test
    void should_retrieve_hero_hp() {
        Hero hero2 = new Hero(HeroId.of(UUID.randomUUID()), "super-boy", 100, 30, 20, 5, Speciality.TANK.label, Rarety.COMMON.label, 6);
        hero2.retrieveHpFromHero(30);
        assertEquals(hero2.hp, 70);
    }

    @Test
    void should_not_retrieve_more_than_hero_hp(){
        Hero hero2 = new Hero(HeroId.of(UUID.randomUUID()), "super-boy", 100, 30, 20, 5, Speciality.TANK.label, Rarety.COMMON.label, 6);
        hero2.retrieveHpFromHero(120);
        assertEquals(hero2.hp, 0);
    }

    @Test
    void should_increase_hero_experience() {
        Hero hero2 = new Hero(HeroId.of(UUID.randomUUID()), "super-boy", 100, 30, 20, 5, Speciality.TANK.label, Rarety.COMMON.label, 6);
        // Before increase XP
        assertEquals(hero2.xp, 30);

        // After increase XP
        hero2.increaseXpToHero(15);
        assertEquals(hero2.xp, 45);
    }

    @Test
    void should_not_increase_hero_experience_with_negative_experience(){
        Hero hero2 = new Hero(HeroId.of(UUID.randomUUID()), "super-boy", 100, 30, 20, 5, Speciality.TANK.label, Rarety.COMMON.label, 6);
        // Before increase XP
        assertEquals(hero2.xp, 30);

        // After increase XP
        hero2.increaseXpToHero(-4);
        assertEquals(hero2.xp, 30);
    }

    @Test
    void should_update_hero_level() {
        Hero hero2 = new Hero(HeroId.of(UUID.randomUUID()), "super-boy", 100, 30, 20, 5, Speciality.TANK.label, Rarety.COMMON.label, 6);
        // Before updating the level
        assertEquals(hero2.level, 6);

        // After updating the level
        hero2.increaseXpToHero(15);
        hero2.updateHeroLevel();
        assertEquals(hero2.level, 9);
    }

    @Test
    void should_not_update_hero_level(){
        Hero hero2 = new Hero(HeroId.of(UUID.randomUUID()), "super-boy", 100, 30, 20, 5, Speciality.TANK.label, Rarety.COMMON.label, 6);
        // Before updating the level
        assertEquals(hero2.level, 6);

        // After updating the level with unchanged xp
        hero2.updateHeroLevel();
        assertEquals(hero2.level, 6);
    }

    @Test
    void should_copy_hero() {
        Hero hero2 = new Hero(HeroId.of(UUID.randomUUID()), "super-boy", 100, 30, 20, 5, Speciality.TANK.label, Rarety.COMMON.label, 6);
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
    void should_return_dead_hero() {
        Hero hero2 = new Hero(HeroId.of(UUID.randomUUID()), "super-boy", 0, 30, 20, 5, Speciality.TANK.label, Rarety.COMMON.label, 6);
        assertTrue(hero2.isDead());
    }

    @Test
    void should_not_return_dead_hero() {
        Hero hero2 = new Hero(HeroId.of(UUID.randomUUID()), "super-boy", 1, 30, 20, 5, Speciality.TANK.label, Rarety.COMMON.label, 6);
        assertFalse(hero2.isDead());
    }

    @Test
    void Equals() {
        Hero hero2 = new Hero(HeroId.of(UUID.randomUUID()), "super-boy", 1, 30, 20, 5, Speciality.TANK.label, Rarety.COMMON.label, 6);
        Hero newHero = hero2.copy();

        assertTrue(hero2.equals(newHero));
    }
}