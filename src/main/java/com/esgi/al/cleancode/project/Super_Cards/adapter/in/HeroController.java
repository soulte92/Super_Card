package com.esgi.al.cleancode.project.Super_Cards.adapter.in;

import com.esgi.al.cleancode.project.Super_Cards.application.service.HeroService;
import com.esgi.al.cleancode.project.Super_Cards.domain.HeroId;

public class HeroController {
    private final HeroService heroService;

    public HeroController(HeroService heroService) {
        this.heroService = heroService;
    }

    public HeroId createHero(String name, String speciality){
        return heroService.createHero(name, speciality);
    }
}
