package esgi.al.cleancode.project.Super_Cards.domain.functional.service;


import esgi.al.cleancode.project.Super_Cards.domain.exceptions.HeroException;
import esgi.al.cleancode.project.Super_Cards.domain.exceptions.PlayerException;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Hero;
import esgi.al.cleancode.project.Super_Cards.domain.ports.client.PlayerHeroCreatorApi;
import esgi.al.cleancode.project.Super_Cards.domain.ports.server.DefaultHeroPersistenceSpi;
import esgi.al.cleancode.project.Super_Cards.domain.ports.server.PlayerHeroPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class PlayerHeroCreatorService implements PlayerHeroCreatorApi {
    private final DefaultHeroPersistenceSpi defaultHeroPersistenceSpi;
    private final PlayerHeroPersistenceSpi playerHeroPersistenceSpi;

    @Override
    public Hero pickHeroFromDefaultHero(String speciality, String rarity) {
        Optional<Hero> hero = defaultHeroPersistenceSpi.findBySpecialityAndRarity(speciality, rarity);
        if (hero.isEmpty()){
            throw HeroException.notFoundHeroBySpecialityAndRarity(speciality, rarity);
        }
        return playerHeroPersistenceSpi.save(hero.get());
    }

    @Override
    public Optional<List<Hero>> getAliveHeroes() {
        return playerHeroPersistenceSpi.findAliveHeroes();
    }
}
