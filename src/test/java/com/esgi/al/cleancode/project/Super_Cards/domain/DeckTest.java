package com.esgi.al.cleancode.project.Super_Cards.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class DeckTest {

    @Test
    void should_create_empty_deck() {
        Deck deck = Deck.create();

        assertNotNull(deck);

        assertNotNull(deck.heroArrayList);

        assertEquals(deck.heroArrayList.size(), 0);
    }

    @Test
    void should_create_from_initial_hero_cards() {
        ArrayList<Hero> heroArrayList = new ArrayList<>();
        Hero newHero = HeroGenerator.generateRandomHeroWithSilverPack();
        heroArrayList.add(newHero);

        Deck deck = Deck.of(heroArrayList);

        assertEquals(deck.heroArrayList.size(), 1);

        assertEquals(deck.heroArrayList.get(0), newHero);
    }

    @Test
    void should_not_create_from_initial_hero_cards() {
        ArrayList<Hero> heroArrayList = new ArrayList<>();

        Deck deck = Deck.of(heroArrayList);

        assertEquals(deck.heroArrayList.size(), 0);
    }

    @Test
    void should_add_heros_to_deck() {
        Deck deck = Deck.create();
        ArrayList<Hero> heroArrayList = new ArrayList<>();
        Hero newHero1 = HeroGenerator.generateRandomHeroWithSilverPack();
        Hero newHero2 = HeroGenerator.generateRandomHeroWithSilverPack();
        Hero newHero3 = HeroGenerator.generateRandomHeroWithSilverPack();
        heroArrayList.add(newHero1);
        heroArrayList.add(newHero2);
        heroArrayList.add(newHero3);

        deck.addHeros(heroArrayList);

        assertEquals(deck.heroArrayList.size(), 3);

        assertTrue(deck.heroArrayList.contains(newHero1));
        assertTrue(deck.heroArrayList.contains(newHero2));
        assertTrue(deck.heroArrayList.contains(newHero3));
    }

    @Test
    void should_not_add_heros_to_deck() {
        Deck deck = Deck.create();
        ArrayList<Hero> heroArrayList = new ArrayList<>();
        deck.addHeros(heroArrayList);

        assertEquals(deck.heroArrayList.size(), 0);
    }

    @Test
    void should_retrieve_hero_by_index_from_deck() {
        Deck deck = Deck.create();
        ArrayList<Hero> heroArrayList = new ArrayList<>();
        Hero newHero1 = HeroGenerator.generateRandomHeroWithSilverPack();
        Hero newHero2 = HeroGenerator.generateRandomHeroWithSilverPack();
        Hero newHero3 = HeroGenerator.generateRandomHeroWithSilverPack();
        heroArrayList.add(newHero1);
        heroArrayList.add(newHero2);
        heroArrayList.add(newHero3);

        deck.addHeros(heroArrayList);

        // Before retrieve newHero2
        assertEquals(deck.heroArrayList.size(), 3);
        assertTrue(deck.heroArrayList.contains(newHero1));
        assertTrue(deck.heroArrayList.contains(newHero2));
        assertTrue(deck.heroArrayList.contains(newHero3));

        //Hero retrievedHero = deck.heroArrayList.get(1);
        // After retrieve newHero2
        deck.retrieveHeroByIndex(1);
        assertFalse(deck.heroArrayList.contains(newHero2));//TODO memory issue
        assertEquals(deck.heroArrayList.size(), 2);
        assertEquals(deck.heroArrayList.get(0), newHero1);
        assertEquals(deck.heroArrayList.get(1), newHero3);
    }

    @Test
    void should_not_retrieve_hero_by_index_from_deck() {

        Deck deck = Deck.create();
        ArrayList<Hero> heroArrayList = new ArrayList<>();
        Hero newHero1 = HeroGenerator.generateRandomHeroWithSilverPack();
        heroArrayList.add(newHero1);

        deck.addHeros(heroArrayList);

        // Before retrieve newHero2
        assertEquals(deck.heroArrayList.size(), 1);
        assertTrue(deck.heroArrayList.contains(newHero1));

        // Throw exception because try to retrieve non-existing hero
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            deck.retrieveHeroByIndex(1);
        });
    }

    @Test
    void should_return_hero_by_index() {
        Deck deck = Deck.create();
        ArrayList<Hero> heroArrayList = new ArrayList<>();
        Hero newHero1 = HeroGenerator.generateRandomHeroWithSilverPack();
        Hero newHero2 = HeroGenerator.generateRandomHeroWithSilverPack();
        heroArrayList.add(newHero1);
        heroArrayList.add(newHero2);

        deck.addHeros(heroArrayList);
        assertEquals(deck.getHeroByIndex(1), newHero2);
    }

    @Test
    void should_not_return_hero_by_index() {
        Deck deck = Deck.create();
        ArrayList<Hero> heroArrayList = new ArrayList<>();
        Hero newHero1 = HeroGenerator.generateRandomHeroWithSilverPack();
        Hero newHero2 = HeroGenerator.generateRandomHeroWithSilverPack();
        heroArrayList.add(newHero1);
        heroArrayList.add(newHero2);

        deck.addHeros(heroArrayList);

        // Throw exception because try to get non-existing hero
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            deck.getHeroByIndex(2);
        });
    }
}