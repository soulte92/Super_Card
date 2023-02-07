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
    void enhaceCharacteriticsByRarety() {
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
