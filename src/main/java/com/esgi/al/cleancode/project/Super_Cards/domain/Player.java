package com.esgi.al.cleancode.project.Super_Cards.domain;

public class Player {
    private String pseudo;
    private int nbToken;
    private Deck deck;

    public Player(String pseudo) {
        this.pseudo = pseudo;
        this.nbToken = 4;
        this.deck = Deck.create();
    }

    //TODO Ouverture d’un pack de cartes using ProbalityDistribution class

    // TODO Inscription joueur
}
