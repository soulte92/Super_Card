package esgi.al.cleancode.project.Super_Cards.domain.functional.service;

import esgi.al.cleancode.project.Super_Cards.domain.exceptions.HeroException;
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
        if (hero != null){
            return Optional.ofNullable(defaultHeroPersistenceSpi.save(hero));
        }
        return Optional.empty();
    }

    public Hero createAndSaveHero(String name, String speciality, String rarity){
        Hero hero = initCharacteristicsBySpeciality(name, speciality, rarity);
        hero = enhaceCharacteriticsByRarity(hero);
        return defaultHeroPersistenceSpi.save(hero);
    }

    public Hero initCharacteristicsBySpeciality(String name, String speciality, String rarity){
        Hero newHero;
        if(speciality.equals(Speciality.TANK.label)){
            newHero = Hero.builder()
                    .name(name)
                    .speciality(speciality)
                    .rarity(rarity).hp(1000.).power(100.).armor(20.).build();
        }
        else if(speciality.equals(Speciality.KILLER.label)){
            newHero = Hero.builder()
                    .name(name)
                    .speciality(speciality)
                    .rarity(rarity).hp(800.).power(200.).armor(5.).build();
        }
        else if(speciality.equals(Speciality.MAGICIAN.label)){
            newHero = Hero.builder()
                    .name(name)
                    .speciality(speciality)
                    .rarity(rarity).hp(700.).power(150.).armor(10.).build();
        }
        else{
            throw HeroException.notSupportedSpeciality(speciality);
        }
        return newHero;
    }

    public Hero enhaceCharacteriticsByRarity(Hero hero){
        Hero newHero;
        if(hero.rarity.equals(Rarity.COMMON.label)){
            newHero = enhanceCharacteristicsByPerCent( hero, 0.);
        }
        else if(hero.rarity.equals(Rarity.RARE.label)){
            newHero = enhanceCharacteristicsByPerCent( hero, 0.1);
        }
        else if(hero.rarity.equals(Rarity.LEGENDARY.label)){
            newHero = enhanceCharacteristicsByPerCent( hero, 0.2);
        }
        else{
            throw HeroException.notSupportedRarety(hero.rarity);
        }
        return newHero;
    }

    public Hero enhanceCharacteristicsByPerCent(Hero hero, double perCent){
        if ((0>perCent) || (perCent>1)){
            throw HeroException.enhaceCaracteriticsByPerCentException(perCent);
        }
        return Hero.builder().heroId(hero.getHeroId())
                .name(hero.name)
                .xp(hero.xp)
                .level(hero.level)
                .speciality(hero.speciality)
                .rarity(hero.rarity)
                .hp(hero.hp + (hero.hp * perCent))
                .power(hero.power + (hero.power * perCent))
                .armor(hero.armor + (hero.armor * perCent)).build();
    }
}
