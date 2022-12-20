package com.esgi.al.cleancode.project.Super_Cards.domain;

import java.util.ArrayList;
import java.util.UUID;

public class Deck {
    public DeckId deckId;
    public ArrayList<HeroId> heroIdArrayList;

    private Deck(DeckId deckId){
        this.deckId = deckId;
        this.heroIdArrayList = new ArrayList<>();
    }

    public static Deck create(DeckId deckId){
        return new Deck(deckId);
    }

//    public static Deck of(DeckId deckId, ArrayList<HeroId> heroIdArrayListToUse){
//        Deck newDeck = new Deck(deckId);
//        newDeck = newDeck.addHeros(deckId, heroIdArrayListToUse);
//        return newDeck;
//    }
//
//    public Deck retrieveHeroByIndex(DeckId deckId, int heroIndex){
//        Deck newDeck = new Deck(deckId);
//        newDeck.heroIdArrayList = this.heroIdArrayList;
//        newDeck.heroIdArrayList.remove(heroIndex);
//        return newDeck;
//    }
//
//    public HeroId getHeroByIndex(int heroIndex){
//        return this.heroIdArrayList.get(heroIndex);
//    }
}
