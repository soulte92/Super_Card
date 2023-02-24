package esgi.al.cleancode.project.Super_Cards.domain.ports.client;

import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Hero;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DefaultHeroesPopulationApi {
    Optional<List<Hero>> createAndSaveDefaultHeroes();
    Optional<List<Hero>> getDefaultHeroes();
    Optional<Hero> createAndSaveOneHero();
}
