package com.esgi.al.cleancode.project.Super_Cards.domain;

import java.util.UUID;

public class Player {
    private PlayerId playerId;
    private DeckId deckId;
    private String pseudo;
    private int nbToken;

    public Player(PlayerId playerId, DeckId deckId, String pseudo) {
        this.pseudo = pseudo;
        this.nbToken = 4;
        this.deckId = deckId;
    }
}
