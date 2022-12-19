package com.esgi.al.cleancode.project.Super_Cards.application.port.out;

import com.esgi.al.cleancode.project.Super_Cards.domain.Hero;
import com.esgi.al.cleancode.project.Super_Cards.domain.HeroId;

import java.util.ArrayList;

public interface HeroRepository {
    Hero findById(HeroId heroId);

    ArrayList<Hero> findAll();

    HeroId nextId();

    void save(Hero hero);
}
