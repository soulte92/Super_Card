package com.esgi.al.cleancode.project.Super_Cards.domain;

import java.util.UUID;

public class Player {
    public PlayerId playerId;
    public DeckId deckId;
    public String pseudo;
    private int nbToken;

    public Player(PlayerId playerId, DeckId deckId, String pseudo) {
        this.playerId = playerId;
        this.pseudo = pseudo;
        this.nbToken = 4;
        this.deckId = deckId;
    }
}
