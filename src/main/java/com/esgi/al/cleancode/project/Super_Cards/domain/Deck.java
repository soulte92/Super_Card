package com.esgi.al.cleancode.project.Super_Cards.domain;

import java.util.ArrayList;
import java.util.UUID;

public class Deck {
    DeckId deckId;
    ArrayList<Hero> heroArrayList;

    private Deck(DeckId deckId){
        this.heroArrayList = new ArrayList<>();
    }

    public static Deck create(DeckId deckId){
        return new Deck(DeckId.of(UUID.randomUUID()));
    }

    public static Deck of(DeckId deckId, ArrayList<Hero> heroArrayListToUse){
        Deck newDeck = new Deck(deckId);
        newDeck = newDeck.addHeros(deckId, heroArrayListToUse);
        return newDeck;
    }

    public Deck addHeros(DeckId deckId, ArrayList<Hero> heroArrayListToUse){
        Deck newDeck = new Deck(deckId);
        newDeck.heroArrayList = this.heroArrayList;
        newDeck.heroArrayList.addAll(heroArrayListToUse);
        return newDeck;
    }

    public Deck retrieveHeroByIndex(DeckId deckId, int heroIndex){
        Deck newDeck = new Deck(deckId);
        newDeck.heroArrayList = this.heroArrayList;
        newDeck.heroArrayList.remove(heroIndex);
        return newDeck;
    }

    public Hero getHeroByIndex(int heroIndex){
        return this.heroArrayList.get(heroIndex);
    }
}
