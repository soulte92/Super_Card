package com.esgi.al.cleancode.project.Super_Cards.application;

import com.esgi.al.cleancode.project.Super_Cards.domain.DeckException;
import com.esgi.al.cleancode.project.Super_Cards.domain.DeckId;

public class DeckApplicationException extends RuntimeException{
    public DeckApplicationException(String message) {
        super(message);
    }

    public static DeckApplicationException notFoundDeck(DeckId deckId) {
        return new DeckApplicationException(String.format("Deck with Id =  " + deckId.value() + " not found !"));
    }
}
