package esgi.al.cleancode.project.Super_Cards.domain.ports.client;

import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Hero;

import java.util.List;
import java.util.Optional;

public interface PlayerHeroCreatorApi {
    Hero pickHeroFromDefaultHero(String speciality, String rarity);

    Optional<List<Hero>> getAliveHeroes();
}
