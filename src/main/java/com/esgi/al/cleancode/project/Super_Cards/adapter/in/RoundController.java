package com.esgi.al.cleancode.project.Super_Cards.adapter.in;

import com.esgi.al.cleancode.project.Super_Cards.application.service.RoundService;
import com.esgi.al.cleancode.project.Super_Cards.domain.HeroId;
import com.esgi.al.cleancode.project.Super_Cards.domain.Round;

import java.util.ArrayList;

public class RoundController {
    private final RoundService roundService;

    public RoundController(RoundService roundService) {
        this.roundService = roundService;
    }

    public ArrayList<Round> getHeroFighterRounds(HeroId heroId){
        return roundService.getHeroFighterRounds(heroId);
    }
}
