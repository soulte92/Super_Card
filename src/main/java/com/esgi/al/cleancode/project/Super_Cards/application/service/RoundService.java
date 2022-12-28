package com.esgi.al.cleancode.project.Super_Cards.application.service;

import com.esgi.al.cleancode.project.Super_Cards.application.port.out.RoundRepository;
import com.esgi.al.cleancode.project.Super_Cards.domain.HeroException;
import com.esgi.al.cleancode.project.Super_Cards.domain.HeroId;
import com.esgi.al.cleancode.project.Super_Cards.domain.Round;

import java.util.ArrayList;

public class RoundService {
    private final RoundRepository roundRepository;
    private final HeroService heroService;

    public RoundService(RoundRepository roundRepository, HeroService heroService) {
        this.roundRepository = roundRepository;
        this.heroService = heroService;
    }

    public ArrayList<Round> getHeroFighterRounds(HeroId heroId){
        if (heroService.getHero(heroId)==null){
            throw HeroApplicationException.notFoundHero(heroId);
        }
        return roundRepository.findByFirstHeroId(heroId);
    }
}
