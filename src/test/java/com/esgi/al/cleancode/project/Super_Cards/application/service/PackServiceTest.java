package com.esgi.al.cleancode.project.Super_Cards.application.service;

import com.esgi.al.cleancode.project.Super_Cards.adapter.out.InMemoryDeckRepository;
import com.esgi.al.cleancode.project.Super_Cards.adapter.out.InMemoryHeroRepository;
import com.esgi.al.cleancode.project.Super_Cards.domain.Deck;
import com.esgi.al.cleancode.project.Super_Cards.domain.DeckId;
import com.esgi.al.cleancode.project.Super_Cards.domain.HeroConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PackServiceTest {

    @Test
    void createHeroDiamondPackInDeck() {
        InMemoryHeroRepository inMemoryHeroRepository = new InMemoryHeroRepository();
        HeroConfiguration heroConfiguration = new HeroConfiguration();
        HeroService heroService = new HeroService(inMemoryHeroRepository, heroConfiguration);
        InMemoryDeckRepository inMemoryDeckRepository = new InMemoryDeckRepository();
        DeckService deckService = new DeckService(inMemoryDeckRepository, heroService);

        PackService packService = new PackService(deckService, heroService);

        DeckId deckId = deckService.createDeck();
        packService.createHeroDiamondPackInDeck(deckId);
        Deck deck = deckService.getDeck(deckId);
        Assertions.assertEquals(deck.heroIdArrayList.size(), 5);
    }

    @Test
    void createHeroSilverPackInDeck() {
        InMemoryHeroRepository inMemoryHeroRepository = new InMemoryHeroRepository();
        HeroConfiguration heroConfiguration = new HeroConfiguration();
        HeroService heroService = new HeroService(inMemoryHeroRepository, heroConfiguration);
        InMemoryDeckRepository inMemoryDeckRepository = new InMemoryDeckRepository();
        DeckService deckService = new DeckService(inMemoryDeckRepository, heroService);

        PackService packService = new PackService(deckService, heroService);

        DeckId deckId = deckService.createDeck();
        packService.createHeroSilverPackInDeck(deckId);
        Deck deck = deckService.getDeck(deckId);
        Assertions.assertEquals(deck.heroIdArrayList.size(), 3);
    }
}