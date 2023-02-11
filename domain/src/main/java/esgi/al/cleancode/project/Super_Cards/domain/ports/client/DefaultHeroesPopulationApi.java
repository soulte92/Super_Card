package esgi.al.cleancode.project.Super_Cards.domain.ports.client;

import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Hero;

import java.util.List;

public interface DefaultHeroesPopulationApi {
    List<Hero> createAndSaveHeroes();
}
