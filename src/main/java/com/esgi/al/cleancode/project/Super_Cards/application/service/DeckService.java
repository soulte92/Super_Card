package com.esgi.al.cleancode.project.Super_Cards.application.service;

import com.esgi.al.cleancode.project.Super_Cards.application.port.out.DeckRepository;
import com.esgi.al.cleancode.project.Super_Cards.domain.Deck;
import com.esgi.al.cleancode.project.Super_Cards.domain.DeckId;
import com.esgi.al.cleancode.project.Super_Cards.domain.Hero;
import com.esgi.al.cleancode.project.Super_Cards.domain.HeroId;

import java.util.ArrayList;
import java.util.List;

public class DeckService {
    private final DeckRepository deckRepository;

    private final HeroService heroService;

    public DeckService(DeckRepository deckRepository, HeroService heroService) {
        this.deckRepository = deckRepository;
        this.heroService = heroService;
    }

    public DeckId createDeck(){
        DeckId deckId = deckRepository.nextId();
        Deck deck = Deck.create(deckId);
        deckRepository.save(deck);
        return deckId;
    }

    public List<Hero> getHerosFromDeck(DeckId deckId){
        Deck deck = deckRepository.findById(deckId);
        ArrayList<Hero> heroes = new ArrayList<>();

        for (HeroId heroId: deck.heroIdArrayList) {
            heroes.add(heroService.getHero(heroId));
        }
        return heroes;
    }

//    public Deck addHeros(DeckId deckId, ArrayList<HeroId> heroIdArrayListToUse){
//        Deck newDeck = new Deck(deckId);
//        newDeck.heroIdArrayList = this.heroIdArrayList;
//        newDeck.heroIdArrayList.addAll(heroIdArrayListToUse);
//        return newDeck;
//    }
    public DeckId addHeroInDeck(DeckId deckId, HeroId heroId){
        Deck newDeck = deckRepository.findById(deckId);

        //Verify that hero exists
        Hero hero = heroService.getHero(heroId);

        newDeck.heroIdArrayList.add(heroId);
        deckRepository.save(newDeck);
        return deckId;
    }


}
