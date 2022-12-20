package com.esgi.al.cleancode.project.Super_Cards.application.service;

import com.esgi.al.cleancode.project.Super_Cards.adapter.out.InMemoryDeckRepository;
import com.esgi.al.cleancode.project.Super_Cards.adapter.out.InMemoryHeroRepository;
import com.esgi.al.cleancode.project.Super_Cards.domain.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DeckServiceTest {

    @Test
    void createDeck() {
        InMemoryDeckRepository inMemoryDeckRepository = new InMemoryDeckRepository();
        InMemoryHeroRepository inMemoryHeroRepository = new InMemoryHeroRepository();
        HeroConfiguration heroConfiguration = new HeroConfiguration();

        HeroService heroService = new HeroService(inMemoryHeroRepository, heroConfiguration);
        DeckService deckService = new DeckService(inMemoryDeckRepository, heroService);

        DeckId deckId = deckService.createDeck();

        Deck deck = inMemoryDeckRepository.findById(deckId);
        Assertions.assertNotNull(deck);
    }

    @Test
    void getHeros() {
        InMemoryDeckRepository inMemoryDeckRepository = new InMemoryDeckRepository();
        InMemoryHeroRepository inMemoryHeroRepository = new InMemoryHeroRepository();
        HeroConfiguration heroConfiguration = new HeroConfiguration();

        HeroService heroService = new HeroService(inMemoryHeroRepository, heroConfiguration);
        DeckService deckService = new DeckService(inMemoryDeckRepository, heroService);

        // Create a deck in DB
        DeckId deckId = deckService.createDeck();

        // Create Heroes in deck
        String speciality = SpecialityGenerator.generateRandomSpeciality();
        String rarety = RaretyGenerator.generateSilverCardRarety();
        var heroId1 = heroService.createHero("toto", speciality, rarety);
        var heroId2 = heroService.createHero("tata", speciality, rarety);

        // Add Heroes in deck
        deckService.addHero(deckId, heroId1);
        deckService.addHero(deckId, heroId2);

        // Get heroes from deck
        ArrayList<Hero> heroArrayList = (ArrayList<Hero>) deckService.getHeros(deckId);

        // Check that there is 2 heroes in th deck
        Assertions.assertEquals(heroArrayList.size(), 2);

        // Check that there are the correct heroes id in the Deck
        int nbCheckedHeroes = 0;
        for (Hero hero: heroArrayList){
            if (hero.heroId.equals(heroId1)){
                nbCheckedHeroes++;
            }
            else if (hero.heroId.equals(heroId2)){
                nbCheckedHeroes++;
            }
        }
        Assertions.assertEquals(nbCheckedHeroes, 2);
    }

    @Test
    void addHero() {
        InMemoryDeckRepository inMemoryDeckRepository = new InMemoryDeckRepository();
        InMemoryHeroRepository inMemoryHeroRepository = new InMemoryHeroRepository();
        HeroConfiguration heroConfiguration = new HeroConfiguration();

        HeroService heroService = new HeroService(inMemoryHeroRepository, heroConfiguration);
        DeckService deckService = new DeckService(inMemoryDeckRepository, heroService);

        // Create a deck in DB
        DeckId deckId = deckService.createDeck();

        // Create Heroes in deck
        String speciality = SpecialityGenerator.generateRandomSpeciality();
        String rarety = RaretyGenerator.generateSilverCardRarety();
        var heroId1 = heroService.createHero("toto", speciality, rarety);
        var heroId2 = heroService.createHero("tata", speciality, rarety);

        // Add Heroes in deck
        deckService.addHero(deckId, heroId1);
        deckService.addHero(deckId, heroId2);


        // Get  added Deck from DB
        Deck deck = inMemoryDeckRepository.findById(deckId);

        // Check that there is 2 heroes in th deck
        Assertions.assertEquals(deck.heroIdArrayList.size(), 2);

        // Check that there are the correct heroes id in the Deck
        Assertions.assertTrue(deck.heroIdArrayList.contains(heroId1));
        Assertions.assertTrue(deck.heroIdArrayList.contains(heroId2));
    }
}