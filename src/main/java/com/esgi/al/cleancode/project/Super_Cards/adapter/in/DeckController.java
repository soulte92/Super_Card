package com.esgi.al.cleancode.project.Super_Cards.adapter.in;

import com.esgi.al.cleancode.project.Super_Cards.application.port.out.DeckRepository;
import com.esgi.al.cleancode.project.Super_Cards.application.service.DeckService;
import com.esgi.al.cleancode.project.Super_Cards.domain.Deck;
import com.esgi.al.cleancode.project.Super_Cards.domain.DeckId;
import com.esgi.al.cleancode.project.Super_Cards.domain.Hero;

import java.util.List;

public class DeckController {

    private final DeckService deckService;

    public DeckController(DeckService deckService) {
        this.deckService = deckService;
    }

    public DeckId createDeck(){
        return deckService.createDeck();
    }

    public List<Hero> getHeroes(DeckId deckId){
        return deckService.getHeros(deckId);
    }
}
