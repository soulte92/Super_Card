package com.esgi.al.cleancode.project.Super_Cards.application.service;

import com.esgi.al.cleancode.project.Super_Cards.adapter.out.InMemoryHeroRepository;
import com.esgi.al.cleancode.project.Super_Cards.adapter.out.InMemoryRoundRepository;
import com.esgi.al.cleancode.project.Super_Cards.domain.HeroConfiguration;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoundServiceTest {

    @Test
    void getHeroFighterRounds() {
        InMemoryHeroRepository inMemoryHeroRepository = new InMemoryHeroRepository();
        HeroConfiguration heroConfiguration = new HeroConfiguration();
        HeroService heroService = new HeroService(inMemoryHeroRepository, heroConfiguration);
        InMemoryRoundRepository inMemoryRoundRepository = new InMemoryRoundRepository();
        RoundService roundService = new RoundService(inMemoryRoundRepository, heroService);

        // Do a mock
    }
}