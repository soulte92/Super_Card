package com.esgi.al.cleancode.project.Super_Cards.application.service;

import com.esgi.al.cleancode.project.Super_Cards.domain.*;

public class PackService {
    private final DeckService deckService;
    private final HeroService heroService;

    public PackService(DeckService deckService, HeroService heroService) {
        this.deckService = deckService;
        this.heroService = heroService;
    }

    public void createHeroDiamondPackInDeck(DeckId deckId){
        for (int i = 0; i<5; i++){
            String speciality = SpecialityGenerator.generateRandomSpeciality();
            String rarety = RaretyGenerator.generateDiamondCardRarety();
            HeroId heroId = heroService.createHero("TOTO", speciality, rarety);
            deckService.addHeroInDeck(deckId, heroId);
        }
    }

    public void createHeroSilverPackInDeck(DeckId deckId){
        for (int i = 0; i<3; i++){
            String speciality = SpecialityGenerator.generateRandomSpeciality();
            String rarety = RaretyGenerator.generateSilverCardRarety();
            HeroId heroId = heroService.createHero("TOTO", speciality, rarety);
            deckService.addHeroInDeck(deckId, heroId);
        }
    }
}
