package com.esgi.al.cleancode.project.Super_Cards.application.service;

import com.esgi.al.cleancode.project.Super_Cards.application.port.out.HeroRepository;
import com.esgi.al.cleancode.project.Super_Cards.domain.Hero;
import com.esgi.al.cleancode.project.Super_Cards.domain.HeroConfiguration;
import com.esgi.al.cleancode.project.Super_Cards.domain.HeroId;

public class HeroService {
    private final HeroRepository heroRepository;

    private final HeroConfiguration heroConfiguration;

    public HeroService(HeroRepository heroRepository, HeroConfiguration heroConfiguration) {
        this.heroRepository = heroRepository;
        this.heroConfiguration = heroConfiguration;
    }

    public HeroId createHero(String name, String speciality){
        var heroId = heroRepository.nextId();
        var hero = new Hero(heroId, name, speciality);
        hero = HeroConfiguration.initCarateristicsBySpeciality(hero);
        heroRepository.save(hero);
        return heroId;
    }
}
