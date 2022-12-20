package com.esgi.al.cleancode.project.Super_Cards.adapter.in;

import com.esgi.al.cleancode.project.Super_Cards.application.service.HeroService;
import com.esgi.al.cleancode.project.Super_Cards.domain.Hero;
import com.esgi.al.cleancode.project.Super_Cards.domain.HeroId;

import java.util.ArrayList;

public class HeroController {
    private final HeroService heroService;

    public HeroController(HeroService heroService) {
        this.heroService = heroService;
    }

    public HeroId createHero(String name, String speciality, String rarety){
        return heroService.createHero(name, speciality, rarety);
    }

    public ArrayList<Hero> getAvailableHeroes(){
        return heroService.getAllAliveHeroes();
    }
}
