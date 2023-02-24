package esgi.al.cleancode.project.Super_Cards.domain.functional.service;

import esgi.al.cleancode.project.Super_Cards.domain.functional.enums.Rarity;
import esgi.al.cleancode.project.Super_Cards.domain.functional.enums.Speciality;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Hero;
import esgi.al.cleancode.project.Super_Cards.domain.ports.client.DefaultHeroesPopulationApi;
import esgi.al.cleancode.project.Super_Cards.domain.ports.server.DefaultHeroPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static esgi.al.cleancode.project.Super_Cards.domain.functional.service.HeroUtils.enhaceCharacteriticsByRarity;
import static esgi.al.cleancode.project.Super_Cards.domain.functional.service.HeroUtils.initCharacteristicsBySpeciality;

@Slf4j
@RequiredArgsConstructor
public class DefaultHeroesPopulationService implements DefaultHeroesPopulationApi {

    private final DefaultHeroPersistenceSpi defaultHeroPersistenceSpi;

    @Override
    public Optional<List<Hero>> createAndSaveDefaultHeroes() {
        List<Hero> heroes = new ArrayList<>();
        heroes.add(createAndSaveHero("Le Guerrier Intrépide", Speciality.TANK.label, Rarity.COMMON.label));
        heroes.add(createAndSaveHero("La Magicienne Suprême", Speciality.MAGICIAN.label, Rarity.COMMON.label));
        heroes.add(createAndSaveHero("L'Archange du Destin", Speciality.KILLER.label, Rarity.COMMON.label));

        heroes.add(createAndSaveHero("Gardien de l'Espoir", Speciality.TANK.label, Rarity.RARE.label));
        heroes.add(createAndSaveHero("La Déesse de la Guerre", Speciality.MAGICIAN.label, Rarity.RARE.label));
        heroes.add(createAndSaveHero("Le Roi des Ombres", Speciality.KILLER.label, Rarity.RARE.label));

        heroes.add(createAndSaveHero("Légendaire Lumière", Speciality.TANK.label, Rarity.LEGENDARY.label));
        heroes.add(createAndSaveHero("Briseur de Chaînes", Speciality.MAGICIAN.label, Rarity.LEGENDARY.label));
        heroes.add(createAndSaveHero("La Reine des Éléments", Speciality.KILLER.label, Rarity.LEGENDARY.label));
        return Optional.of(heroes);
    }

    @Override
    public Optional<List<Hero>> getDefaultHeroes() {
        return Optional.ofNullable(defaultHeroPersistenceSpi.findAll());
    }

    @Override
    public Optional<Hero> createAndSaveOneHero() {
        String speciality = Speciality.TANK.label;
        String rarity = Rarity.RARE.label;
        String name = Rarity.RARE.label;
        Hero hero = initCharacteristicsBySpeciality(name, speciality, rarity);
        hero = enhaceCharacteriticsByRarity(hero);
        if (hero != null) {
            return Optional.ofNullable(defaultHeroPersistenceSpi.save(hero));
        }
        return Optional.empty();
    }

    public Hero createAndSaveHero(String name, String speciality, String rarity) {
        Hero hero = initCharacteristicsBySpeciality(name, speciality, rarity);
        hero = enhaceCharacteriticsByRarity(hero);
        return defaultHeroPersistenceSpi.save(hero);
    }

}
