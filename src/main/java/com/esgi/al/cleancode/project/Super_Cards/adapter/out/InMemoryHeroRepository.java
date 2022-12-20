package com.esgi.al.cleancode.project.Super_Cards.adapter.out;

import com.esgi.al.cleancode.project.Super_Cards.adapter.in.HeroController;
import com.esgi.al.cleancode.project.Super_Cards.application.port.out.HeroRepository;
import com.esgi.al.cleancode.project.Super_Cards.domain.Hero;
import com.esgi.al.cleancode.project.Super_Cards.domain.HeroException;
import com.esgi.al.cleancode.project.Super_Cards.domain.HeroId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class InMemoryHeroRepository implements HeroRepository {

    private final Map<HeroId, Hero> registry = new HashMap<>();

    @Override
    public Hero findById(HeroId heroId) {
        return registry.computeIfAbsent(heroId,
                key -> {
                    throw HeroException.notFoundHero(heroId);
                });
    }

    @Override
    public ArrayList<HeroId> findAllAlive() {
        ArrayList<HeroId> heroIdArrayList = new ArrayList<>();
        for (Map.Entry<HeroId, Hero> set : registry.entrySet()){
            if (!set.getValue().isDead()){
                heroIdArrayList.add(set.getKey());
            }
        }
        return heroIdArrayList;
    }

    @Override
    public HeroId nextId() {
        return HeroId.of(UUID.randomUUID());
    }

    @Override
    public void save(Hero hero) {
        registry.put(hero.heroId, hero);
    }
}
