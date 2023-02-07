package esgi.al.cleancode.project.Super_Cards.domain.functional.service;

import esgi.al.cleancode.project.Super_Cards.domain.exceptions.HeroException;
import esgi.al.cleancode.project.Super_Cards.domain.functional.enums.Rarity;
import esgi.al.cleancode.project.Super_Cards.domain.functional.enums.Speciality;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Hero;
import esgi.al.cleancode.project.Super_Cards.domain.ports.client.DefaultHeroesPopulationApi;
import esgi.al.cleancode.project.Super_Cards.domain.ports.server.DefaultHeroPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class DefaultHeroesPopulationService implements DefaultHeroesPopulationApi {

    private final DefaultHeroPersistenceSpi heroPersistenceSpi;

    @Override
    public void createAndSaveHeroes() {
        createAndSaveHero("Le Guerrier Intrépide", Speciality.TANK.label, Rarity.COMMON.label);
        createAndSaveHero("La Magicienne Suprême", Speciality.MAGICIAN.label, Rarity.COMMON.label);
        createAndSaveHero("L'Archange du Destin", Speciality.KILLER.label, Rarity.COMMON.label);

        createAndSaveHero("Gardien de l'Espoir", Speciality.TANK.label, Rarity.RARE.label);
        createAndSaveHero("La Déesse de la Guerre", Speciality.MAGICIAN.label, Rarity.RARE.label);
        createAndSaveHero("Le Roi des Ombres", Speciality.KILLER.label, Rarity.RARE.label);

        createAndSaveHero("Légendaire Lumière", Speciality.TANK.label, Rarity.LEGENDARY.label);
        createAndSaveHero("Briseur de Chaînes", Speciality.MAGICIAN.label, Rarity.LEGENDARY.label);
        createAndSaveHero("La Reine des Éléments", Speciality.KILLER.label, Rarity.LEGENDARY.label);
    }

    public void createAndSaveHero(String name, String speciality, String rarety){
        Hero hero = initCharacteristicsBySpeciality(name, speciality, rarety);
        hero = enhaceCharacteriticsByRarity(hero);
        heroPersistenceSpi.save(hero);
    }

    public Hero initCharacteristicsBySpeciality(String name, String speciality, String rarety){
        Hero newHero;
        if(speciality.equals(Speciality.TANK.label)){
            newHero = Hero.builder()
                    .name(name)
                    .speciality(speciality)
                    .rarety(rarety).hp(1000.).power(100.).armor(20.).build();
        }
        else if(speciality.equals(Speciality.KILLER.label)){
            newHero = Hero.builder()
                    .name(name)
                    .speciality(speciality)
                    .rarety(rarety).hp(800.).power(200.).armor(5.).build();
        }
        else if(speciality.equals(Speciality.MAGICIAN.label)){
            newHero = Hero.builder()
                    .name(name)
                    .speciality(speciality)
                    .rarety(rarety).hp(700.).power(150.).armor(10.).build();
        }
        else{
            throw HeroException.notSupportedSpeciality(speciality);
        }
        return newHero;
    }

    public Hero enhaceCharacteriticsByRarity(Hero hero){
        Hero newHero;
        if(hero.rarety.equals(Rarity.COMMON.label)){
            newHero = enhanceCharacteristicsByPerCent( hero, 0.);
        }
        else if(hero.rarety.equals(Rarity.RARE.label)){
            newHero = enhanceCharacteristicsByPerCent( hero, 0.1);
        }
        else if(hero.rarety.equals(Rarity.LEGENDARY.label)){
            newHero = enhanceCharacteristicsByPerCent( hero, 0.2);
        }
        else{
            throw HeroException.notSupportedRarety(hero.rarety);
        }
        return newHero;
    }

    public Hero enhanceCharacteristicsByPerCent(Hero hero, double perCent){
        if ((0>perCent) || (perCent>1)){
            throw HeroException.enhaceCaracteriticsByPerCentException(perCent);
        }
        return Hero.builder().id(hero.getId())
                .name(hero.name)
                .xp(hero.xp)
                .level(hero.level)
                .speciality(hero.speciality)
                .rarety(hero.rarety)
                .hp(hero.hp + (hero.hp * perCent))
                .power(hero.power + (hero.power * perCent))
                .armor(hero.armor + (hero.armor * perCent)).build();
    }
}
