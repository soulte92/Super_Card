package com.esgi.al.cleancode.project.Super_Cards.application.service;

import com.esgi.al.cleancode.project.Super_Cards.application.port.out.HeroRepository;
import com.esgi.al.cleancode.project.Super_Cards.domain.Hero;
import com.esgi.al.cleancode.project.Super_Cards.domain.HeroConfiguration;
import com.esgi.al.cleancode.project.Super_Cards.domain.HeroId;

import java.util.ArrayList;

public class HeroService {
    private final HeroRepository heroRepository;

    private final HeroConfiguration heroConfiguration;

    public HeroService(HeroRepository heroRepository, HeroConfiguration heroConfiguration) {
        this.heroRepository = heroRepository;
        this.heroConfiguration = heroConfiguration;
    }

    public HeroId createHero(String name, String speciality, String rarety){
        var heroId = heroRepository.nextId();
        var hero = new Hero(heroId, name, speciality, rarety);
        hero = HeroConfiguration.initCarateristicsBySpeciality(hero);
        hero = HeroConfiguration.enhaceCaracteriticsByRarety(hero);

        heroRepository.save(hero);
        return heroId;
    }

    public ArrayList<Hero> getAllAliveHeroes(){
        ArrayList<Hero> heroes = new ArrayList<>();
        ArrayList<HeroId> heroIdArrayList = heroRepository.findAllAlive();
        for (HeroId heroId: heroIdArrayList) {
            heroes.add(heroRepository.findById(heroId));
        }
        return heroes;
    }

    public Hero getHero(HeroId heroId){
        return heroRepository.findById(heroId);
    }
}
