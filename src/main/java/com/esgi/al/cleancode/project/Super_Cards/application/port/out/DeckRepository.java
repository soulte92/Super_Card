package com.esgi.al.cleancode.project.Super_Cards.application.port.out;

import com.esgi.al.cleancode.project.Super_Cards.domain.Deck;
import com.esgi.al.cleancode.project.Super_Cards.domain.DeckId;

import java.util.ArrayList;

public interface DeckRepository {
    Deck findById(DeckId deckId);

    ArrayList<Deck> findAll();

    DeckId nextId();

    void save(Deck deck);
}
