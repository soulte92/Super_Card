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

import java.util.UUID;

import static org.assertj.vavr.api.VavrAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)

// TODO finish tests
class DefaultHeroesPopulationServiceTest {
    @InjectMocks
    private DefaultHeroesPopulationService service;
    @Mock
    private DefaultHeroPersistenceSpi spi;

    @Test
    void createAndSaveHeroes() {

    }

    @Test
    void createAndSaveHero() {

    }

    @Test
    void initCharacteristicsBySpeciality() {
    }

    @Test
    void should_enhace_hero_caracteritics_by_rarety() {
        Hero givenCommonHero = Hero.builder()
                .name("super-boy")
                .hp(30)
                .xp(100)
                .power(20)
                .armor(5)
                .level(6)
                .speciality(Speciality.TANK.label)
                .rarety(Rarity.COMMON.label).build();
        Hero actualCommonHero = service.enhaceCharacteriticsByRarity(givenCommonHero);
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
                .rarety(Rarity.RARE.label).build();
        Hero actualRareHero = service.enhaceCharacteriticsByRarity(givenRareHero);
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
                .rarety(Rarity.LEGENDARY.label).build();
        Hero actualLegendaryHero = service.enhaceCharacteriticsByRarity(givenLegendaryHero);
        assertEquals(actualLegendaryHero.hp, 36.0);
        assertEquals(actualLegendaryHero.power, 24.0);
        assertEquals(actualLegendaryHero.armor, 6.0);
    }

    @Test
    void should_not_enhace_hero_caracteritics_by_not_supported_rarety(){
        Hero givenHero = Hero.builder()
                .name("super-boy")
                .hp(30)
                .xp(100)
                .power(20)
                .armor(5)
                .level(6)
                .speciality(Speciality.TANK.label)
                .rarety("BIGBOSS").build();
        Assertions.assertThrows(HeroException.class, () -> {
            service.enhaceCharacteriticsByRarity(givenHero);
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
                .rarety(Rarity.COMMON.label).build();

        double perCent = 0.5;
        Hero actualHero = service.enhanceCharacteristicsByPerCent(givenHero, perCent);

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
                .rarety(Rarity.COMMON.label).build();

        double perCent = 0.0;
        Hero actualHero = service.enhanceCharacteristicsByPerCent(givenHero, perCent);

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
                .rarety(Rarity.COMMON.label).build();

        // Should throw exception when percent is not in ]0,1[
        Assertions.assertThrows(HeroException.class, () -> {
            service.enhanceCharacteristicsByPerCent(givenHero, -15.);
        });
        Assertions.assertThrows(HeroException.class, () -> {
            service.enhanceCharacteristicsByPerCent(givenHero, 5.);
        });
    }
}
