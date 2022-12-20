package com.esgi.al.cleancode.project.Super_Cards.adapter.out;

import com.esgi.al.cleancode.project.Super_Cards.application.port.out.DeckRepository;
import com.esgi.al.cleancode.project.Super_Cards.domain.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InMemoryDeckRepository implements DeckRepository {
    private final Map<DeckId, Deck> registry = new HashMap<>();

    @Override
    public DeckId nextId() {
        return DeckId.of(UUID.randomUUID());
    }

    @Override
    public Deck findById(DeckId deckId) {
        return registry.computeIfAbsent(deckId,
                key -> {
                    throw DeckException.notFoundDeck(deckId);
                });
    }

    @Override
    public ArrayList<Deck> findAll() {
        return null;
    }

    @Override
    public void save(Deck deck) {
        registry.put(deck.deckId, deck);
    }
}
