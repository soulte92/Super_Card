package esgi.al.cleancode.project.Super_Cards.domain.functional.service;

import esgi.al.cleancode.project.Super_Cards.domain.exceptions.HeroException;
import esgi.al.cleancode.project.Super_Cards.domain.functional.enums.Rarity;
import esgi.al.cleancode.project.Super_Cards.domain.functional.enums.Speciality;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Hero;
import esgi.al.cleancode.project.Super_Cards.domain.ports.server.DefaultHeroPersistenceSpi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static esgi.al.cleancode.project.Super_Cards.domain.functional.service.HeroUtils.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)

// TODO finish tests
class DefaultHeroesPopulationServiceTest {
    @InjectMocks
    private DefaultHeroesPopulationService service;
    @Mock
    private DefaultHeroPersistenceSpi spi;

    @Test
    void should_create_and_save_heroes_in_db() {
        Optional<List<Hero>> heroes = service.createAndSaveDefaultHeroes();
        if (heroes.isPresent()){
            Assertions.assertEquals(heroes.get().size(), 9);
        } else {
            Assertions.fail();
        }
        //TODO add test
    }

    // TODO : should correct this test
    @Test
    public void should_create_and_save_hero_in_db(){
        Hero givenHero = Hero.builder().build();
        String givenName = "super-boy";
        String givenRarity = Rarity.COMMON.label;
        String givenTankSpeciality = Speciality.TANK.label;

//        when(spi.save(givenHero)).thenReturn(givenHero);
//        when(service.initCharacteristicsBySpeciality(givenName, givenTankSpeciality, givenRarity)).thenReturn(givenHero);
//        when(service.enhaceCharacteriticsByRarity(givenHero)).thenReturn(givenHero);
//
//        Hero actualHero = service.createAndSaveHero(anyString(), anyString(), anyString());
//        Assertions.assertSame(actualHero,givenHero);
    }

    @Test
    void should_initialize_hero_carateristics_by_speciality() {
        String givenName = "super-boy";
        String givenRarity = Rarity.COMMON.label;

        String givenTankSpeciality = Speciality.TANK.label;
        Hero actualTankHero = initCharacteristicsBySpeciality(givenName, givenTankSpeciality, givenRarity);
        assertEquals(actualTankHero.hp, 1000);
        assertEquals(actualTankHero.power, 100);
        assertEquals(actualTankHero.armor, 20);

        String givenKillerSpeciality = Speciality.KILLER.label;
        Hero actualKillerHero = initCharacteristicsBySpeciality(givenName, givenKillerSpeciality, givenRarity);
        assertEquals(actualKillerHero.hp, 800);
        assertEquals(actualKillerHero.power, 200);
        assertEquals(actualKillerHero.armor, 5);

        String givenMagicianSpeciality = Speciality.MAGICIAN.label;
        Hero actualMagicianHero = initCharacteristicsBySpeciality(givenName, givenMagicianSpeciality, givenRarity);
        assertEquals(actualMagicianHero.hp, 700);
        assertEquals(actualMagicianHero.power, 150);
        assertEquals(actualMagicianHero.armor, 10);
    }

    @Test
    void should_not_initialize_hero_carateristics_by_speciality(){
        String givenName = "super-boy";
        String givenRarity = Rarity.COMMON.label;
        String givenSpeciality = "lune";
        Assertions.assertThrows(HeroException.class, () -> {
            initCharacteristicsBySpeciality(givenName, givenRarity, givenSpeciality);
        });
    }

    @Test
    void should_enhace_hero_caracteritics_by_rarity() {
        Hero givenCommonHero = Hero.builder()
                .name("super-boy")
                .hp(30)
                .xp(100)
                .power(20)
                .armor(5)
                .level(6)
                .speciality(Speciality.TANK.label)
                .rarity(Rarity.COMMON.label).build();
        Hero actualCommonHero = enhaceCharacteriticsByRarity(givenCommonHero);
        assertEquals(actualCommonHero.hp, 30.0);
        assertEquals(actualCommonHero.power, 20.0);
        assertEquals(actualCommonHero.armor, 5.0);

        Hero givenRareHero = Hero.builder()
                .name("super-boy")
                .hp(30)
                .xp(100)
                .power(20)
                .armor(5)
                .level(6)
                .speciality(Speciality.TANK.label)
                .rarity(Rarity.RARE.label).build();
        Hero actualRareHero = enhaceCharacteriticsByRarity(givenRareHero);
        assertEquals(actualRareHero.hp, 33.0);
        assertEquals(actualRareHero.power, 22.0);
        assertEquals(actualRareHero.armor, 5.5);

        Hero givenLegendaryHero = Hero.builder()
                .name("super-boy")
                .hp(30)
                .xp(100)
                .power(20)
                .armor(5)
                .level(6)
                .speciality(Speciality.TANK.label)
                .rarity(Rarity.LEGENDARY.label).build();
        Hero actualLegendaryHero = enhaceCharacteriticsByRarity(givenLegendaryHero);
        assertEquals(actualLegendaryHero.hp, 36.0);
        assertEquals(actualLegendaryHero.power, 24.0);
        assertEquals(actualLegendaryHero.armor, 6.0);
    }

    @Test
    void should_not_enhace_hero_caracteritics_by_not_supported_rarity(){
        Hero givenHero = Hero.builder()
                .name("super-boy")
                .hp(30)
                .xp(100)
                .power(20)
                .armor(5)
                .level(6)
                .speciality(Speciality.TANK.label)
                .rarity("BIGBOSS").build();
        Assertions.assertThrows(HeroException.class, () -> {
            enhaceCharacteriticsByRarity(givenHero);
        });
    }

    @Test
    void should_enhance_hero_characteristic_by_perCent() {
        Hero givenHero = Hero.builder()
                .name("super-boy")
                .hp(30)
                .xp(100)
                .power(20)
                .armor(5)
                .level(6)
                .speciality(Speciality.TANK.label)
                .rarity(Rarity.COMMON.label).build();

        double perCent = 0.5;
        Hero actualHero = enhanceCharacteristicsByPerCent(givenHero, perCent);

        assertEquals(actualHero.hp, 45.0);
        assertEquals(actualHero.power, 30.0);
        assertEquals(actualHero.armor, 7.5);
    }

    @Test
    void should_not_enhance_hero_characteristic_by_perCent(){
        Hero givenHero = Hero.builder()
                .name("super-boy")
                .hp(30)
                .xp(100)
                .power(20)
                .armor(5)
                .level(6)
                .speciality(Speciality.TANK.label)
                .rarity(Rarity.COMMON.label).build();

        double perCent = 0.0;
        Hero actualHero = enhanceCharacteristicsByPerCent(givenHero, perCent);

        assertEquals(actualHero.hp, 30.);
        assertEquals(actualHero.power, 20.);
        assertEquals(actualHero.armor, 5.);
    }

    @Test
    void should_throw_when_enhance_hero_characteristic_with_negative_perCent(){
        Hero givenHero = Hero.builder()
                .name("super-boy")
                .hp(30)
                .xp(100)
                .power(20)
                .armor(5)
                .level(6)
                .speciality(Speciality.TANK.label)
                .rarity(Rarity.COMMON.label).build();

        // Should throw exception when percent is not in ]0,1[
        Assertions.assertThrows(HeroException.class, () -> {
            enhanceCharacteristicsByPerCent(givenHero, -15.);
        });
        Assertions.assertThrows(HeroException.class, () -> {
            enhanceCharacteristicsByPerCent(givenHero, 5.);
        });
    }
}
