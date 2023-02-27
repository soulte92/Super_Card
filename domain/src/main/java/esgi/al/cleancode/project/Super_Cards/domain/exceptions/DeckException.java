package esgi.al.cleancode.project.Super_Cards.domain.exceptions;

import java.util.UUID;

public class DeckException extends RuntimeException {

    public DeckException() {
    }

    public DeckException(String message) {
        super(message);
    }

    public static DeckException create() {
        throw new DeckException();
    }

    public static DeckException notFoundDeck(UUID id) {
        return new DeckException(String.format("Deck with Id = " + id.toString() +" not found !"));
    }
}