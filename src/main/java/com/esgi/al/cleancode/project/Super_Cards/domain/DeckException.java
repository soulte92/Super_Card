package com.esgi.al.cleancode.project.Super_Cards.domain;

public class DeckException extends RuntimeException {

    private DeckException() {
    }

    private DeckException(String message) {
        super(message);
    }

    public static DeckException create() {
        throw new DeckException();
    }

    public static DeckException notFoundDeck(DeckId deckId) {
        return new DeckException(String.format("Deck with Id =  " + deckId.value() + " not found !"));
    }
}
