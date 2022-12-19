package com.esgi.al.cleancode.project.Super_Cards.service;

import com.esgi.al.cleancode.project.Super_Cards.adapter.out.InMemoryHeroRepository;
import com.esgi.al.cleancode.project.Super_Cards.application.service.HeroService;
import com.esgi.al.cleancode.project.Super_Cards.domain.Hero;
import com.esgi.al.cleancode.project.Super_Cards.domain.HeroConfiguration;
import com.esgi.al.cleancode.project.Super_Cards.domain.RaretyGenerator;
import com.esgi.al.cleancode.project.Super_Cards.domain.SpecialityGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HeroServiceTest {

    @Test
    public void should_create_and_save_hero_in_db(){
        InMemoryHeroRepository inMemoryHeroRepository = new InMemoryHeroRepository();
        HeroConfiguration heroConfiguration = new HeroConfiguration();
        HeroService heroService = new HeroService(inMemoryHeroRepository, heroConfiguration);
        String speciality = SpecialityGenerator.generateRandomSpeciality();
        String rarety = RaretyGenerator.generateSilverCardRarety();
        var heroId = heroService.createHero("toto", speciality);
        var hero = new Hero(heroId, "toto", speciality);
        hero = HeroConfiguration.initCarateristicsBySpeciality(hero);

        Assertions.assertEquals(inMemoryHeroRepository.findById(heroId),hero);
        //when(inMemoryHeroRepository.findById(heroId)).thenReturn(hero);
    }
}
