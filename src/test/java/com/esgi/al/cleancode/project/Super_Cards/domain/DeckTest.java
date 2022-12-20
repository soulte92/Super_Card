package com.esgi.al.cleancode.project.Super_Cards.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DeckTest {

    @Test
    void should_create_empty_deck() {
        Deck deck = Deck.create(DeckId.of(UUID.randomUUID()));

        assertNotNull(deck);

        assertNotNull(deck.heroIdArrayList);

        assertEquals(deck.heroIdArrayList.size(), 0);
    }

//    @Test
//    void should_create_from_initial_hero_cards() {
//        ArrayList<HeroId> heroIdArrayList = new ArrayList<>();
//        Hero newHero = HeroGenerator.generateRandomHeroWithSilverPack();
//        heroIdArrayList.add(newHero.heroId);
//
//        Deck deck = Deck.of(DeckId.of(UUID.randomUUID()), heroIdArrayList);
//
//        assertEquals(deck.heroIdArrayList.size(), 1);
//
//        assertEquals(deck.heroIdArrayList.get(0), newHero);
//    }
//
//    @Test
//    void should_not_create_from_initial_hero_cards() {
//        ArrayList<HeroId> heroIdArrayList = new ArrayList<>();
//
//        Deck deck = Deck.of(DeckId.of(UUID.randomUUID()), heroIdArrayList);
//
//        assertEquals(deck.heroIdArrayList.size(), 0);
//    }
//
//    @Test
//    void should_add_heros_to_deck() {
//        Deck deck = Deck.create(DeckId.of(UUID.randomUUID()));
//        ArrayList<HeroId> heroIdArrayList = new ArrayList<>();
//        Hero newHero1 = HeroGenerator.generateRandomHeroWithSilverPack();
//        Hero newHero2 = HeroGenerator.generateRandomHeroWithSilverPack();
//        Hero newHero3 = HeroGenerator.generateRandomHeroWithSilverPack();
//        heroIdArrayList.add(newHero1.heroId);
//        heroIdArrayList.add(newHero2.heroId);
//        heroIdArrayList.add(newHero3.heroId);
//
//        deck.addHeros(deck.deckId, heroIdArrayList);
//
//        assertEquals(deck.heroIdArrayList.size(), 3);
//
//        assertTrue(deck.heroIdArrayList.contains(newHero1));
//        assertTrue(deck.heroIdArrayList.contains(newHero2));
//        assertTrue(deck.heroIdArrayList.contains(newHero3));
//    }
//
//    @Test
//    void should_not_add_heros_to_deck() {
//        Deck deck = Deck.create(DeckId.of(UUID.randomUUID()));
//        ArrayList<HeroId> heroIdArrayList = new ArrayList<>();
//        deck.addHeros(deck.deckId, heroIdArrayList);
//
//        assertEquals(deck.heroIdArrayList.size(), 0);
//    }
//
//    @Test
//    void should_retrieve_hero_by_index_from_deck() {
//        Deck deck = Deck.create(DeckId.of(UUID.randomUUID()));
//        ArrayList<HeroId> heroIdArrayList = new ArrayList<>();
//        Hero newHero1 = HeroGenerator.generateRandomHeroWithSilverPack();
//        Hero newHero2 = HeroGenerator.generateRandomHeroWithSilverPack();
//        Hero newHero3 = HeroGenerator.generateRandomHeroWithSilverPack();
//        heroIdArrayList.add(newHero1.heroId);
//        heroIdArrayList.add(newHero2.heroId);
//        heroIdArrayList.add(newHero3.heroId);
//
//        deck.addHeros(deck.deckId, heroIdArrayList);
//
//        // Before retrieve newHero2
//        assertEquals(deck.heroIdArrayList.size(), 3);
//        assertTrue(deck.heroIdArrayList.contains(newHero1));
//        assertTrue(deck.heroIdArrayList.contains(newHero2));
//        assertTrue(deck.heroIdArrayList.contains(newHero3));
//
//        //Hero retrievedHero = deck.heroIdArrayList.get(1);
//        // After retrieve newHero2
//        deck.retrieveHeroByIndex(deck.deckId, 1);
//        assertFalse(deck.heroIdArrayList.contains(newHero2));//TODO memory issue
//        assertEquals(deck.heroIdArrayList.size(), 2);
//        assertEquals(deck.heroIdArrayList.get(0), newHero1);
//        assertEquals(deck.heroIdArrayList.get(1), newHero3);
//    }

//    @Test
//    void should_not_retrieve_hero_by_index_from_deck() {
//
//        Deck deck = Deck.create(DeckId.of(UUID.randomUUID()));
//        ArrayList<HeroId> heroIdArrayList = new ArrayList<>();
//        Hero newHero1 = HeroGenerator.generateRandomHeroWithSilverPack();
//        heroIdArrayList.add(newHero1.heroId);
//
//        deck.addHeros(deck.deckId, heroIdArrayList);
//
//        // Before retrieve newHero2
//        assertEquals(deck.heroIdArrayList.size(), 1);
//        assertTrue(deck.heroIdArrayList.contains(newHero1));
//
//        // Throw exception because try to retrieve non-existing hero
//        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
//            deck.retrieveHeroByIndex(deck.deckId, 1);
//        });
//    }
//
//    @Test
//    void should_return_hero_by_index() {
//        Deck deck = Deck.create(DeckId.of(UUID.randomUUID()));
//        ArrayList<HeroId> heroIdArrayList = new ArrayList<>();
//        Hero newHero1 = HeroGenerator.generateRandomHeroWithSilverPack();
//        Hero newHero2 = HeroGenerator.generateRandomHeroWithSilverPack();
//        heroIdArrayList.add(newHero1.heroId);
//        heroIdArrayList.add(newHero2.heroId);
//
//        deck.addHeros(deck.deckId, heroIdArrayList);
//        assertEquals(deck.getHeroByIndex(1), newHero2);
//    }
//
//    @Test
//    void should_not_return_hero_by_index() {
//        Deck deck = Deck.create(DeckId.of(UUID.randomUUID()));
//        ArrayList<HeroId> heroIdArrayList = new ArrayList<>();
//        Hero newHero1 = HeroGenerator.generateRandomHeroWithSilverPack();
//        Hero newHero2 = HeroGenerator.generateRandomHeroWithSilverPack();
//        heroIdArrayList.add(newHero1.heroId);
//        heroIdArrayList.add(newHero2.heroId);
//
//        deck.addHeros(deck.deckId, heroIdArrayList);
//
//        // Throw exception because try to get non-existing hero
//        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
//            deck.getHeroByIndex(2);
//        });
//    }
}