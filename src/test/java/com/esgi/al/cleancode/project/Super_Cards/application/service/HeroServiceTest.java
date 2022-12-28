package com.esgi.al.cleancode.project.Super_Cards.application.service;

import com.esgi.al.cleancode.project.Super_Cards.adapter.out.InMemoryHeroRepository;
import com.esgi.al.cleancode.project.Super_Cards.application.service.HeroService;
import com.esgi.al.cleancode.project.Super_Cards.domain.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.UUID;

public class HeroServiceTest {

    @Test
    public void should_create_and_save_hero_in_db(){
        InMemoryHeroRepository inMemoryHeroRepository = new InMemoryHeroRepository();
        HeroConfiguration heroConfiguration = new HeroConfiguration();
        HeroService heroService = new HeroService(inMemoryHeroRepository, heroConfiguration);

        String speciality = SpecialityGenerator.generateRandomSpeciality();
        String rarety = RaretyGenerator.generateSilverCardRarety();
        var heroId = heroService.createHero("toto", speciality, rarety);

        var hero = new Hero(heroId, "toto", speciality, rarety);
        hero = HeroConfiguration.initCarateristicsBySpeciality(hero);

        Assertions.assertTrue(inMemoryHeroRepository.findById(heroId).equals(hero));
        //when(inMemoryHeroRepository.findById(heroId)).thenReturn(hero);
    }

    @Test
    public void should_return_all_alive_heroes_in_db(){
        InMemoryHeroRepository inMemoryHeroRepository = new InMemoryHeroRepository();
        HeroConfiguration heroConfiguration = new HeroConfiguration();
        HeroService heroService = new HeroService(inMemoryHeroRepository, heroConfiguration);
        HeroGenerator heroGenerator = new HeroGenerator();
        // Create 2 alive heroes and 1 dead hero
        Hero hero1 = heroGenerator.generateRandomHeroWithSilverPack(HeroId.of(UUID.randomUUID()), "TOTO");
        Hero hero2 = heroGenerator.generateRandomHeroWithSilverPack(HeroId.of(UUID.randomUUID()), "TOTO");
        Hero hero3 = heroGenerator.generateRandomHeroWithSilverPack(HeroId.of(UUID.randomUUID()), "TOTO");
        hero3.hp = 0;

        // Save heroes in DB
        inMemoryHeroRepository.save(hero1);
        inMemoryHeroRepository.save(hero2);
        inMemoryHeroRepository.save(hero3);

        // Get all alive heroes
        ArrayList<Hero> heroArrayList = heroService.getAllAliveHeroes();

        // Verify that there is 2 alive heroes
        Assertions.assertEquals(heroArrayList.size(), 2);

        // Verify that it is the correct alive heroes id
        Assertions.assertTrue(heroArrayList.contains(hero1));
        Assertions.assertTrue(heroArrayList.contains(hero2));

        //when(inMemoryHeroRepository.findById(heroId)).thenReturn(hero);
    }

    @Test
    public void should_not_return_any_alive_hero_in_db(){
        InMemoryHeroRepository inMemoryHeroRepository = new InMemoryHeroRepository();
        HeroConfiguration heroConfiguration = new HeroConfiguration();
        HeroService heroService = new HeroService(inMemoryHeroRepository, heroConfiguration);
        HeroGenerator heroGenerator = new HeroGenerator();

        // Create 1 dead hero
        Hero hero = heroGenerator.generateRandomHeroWithSilverPack(HeroId.of(UUID.randomUUID()), "TOTO");
        hero.hp = 0;

        // Save heroes in DB
        inMemoryHeroRepository.save(hero);

        Assertions.assertEquals(heroService.getAllAliveHeroes().size(), 0);

        //when(inMemoryHeroRepository.findById(heroId)).thenReturn(hero);
    }
}
