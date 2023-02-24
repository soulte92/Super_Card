package esgi.al.cleancode.project.Super_Cards.domain.functional.service;


import esgi.al.cleancode.project.Super_Cards.domain.exceptions.HeroException;
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
        Optional<Hero> hero = findDefaultHero(speciality, rarity);
        return hero.map(this::savePlayerHero).orElse(null);
    }

    @Override
    public Optional<List<Hero>> getAliveHeroes() {
        return playerHeroPersistenceSpi.findAliveHeroes();
    }

    public Optional<Hero> findDefaultHero(String speciality, String rarity) {
        // TODO Add speciality and rarity validators
        Optional<Hero> hero = defaultHeroPersistenceSpi.findBySpecialityAndRarity(speciality, rarity);
        if (hero.isEmpty()) {
            throw HeroException.notFoundHeroBySpecialityAndRarity(speciality, rarity);
        }
        return hero;
    }

    public Hero savePlayerHero(Hero newHero) {
        try {
            Hero hero = Hero.builder()
                    .name(newHero.getName())
                    .xp(newHero.getXp())
                    .level(newHero.getLevel())
                    .speciality(newHero.getSpeciality())
                    .rarity(newHero.getRarity())
                    .hp(newHero.getHp())
                    .power(newHero.getPower())
                    .armor(newHero.getArmor()).build();
            return playerHeroPersistenceSpi.save(hero);
        } catch (HeroException e) {
            throw new HeroException("Player Hero creation error !");
        }
    }
}
