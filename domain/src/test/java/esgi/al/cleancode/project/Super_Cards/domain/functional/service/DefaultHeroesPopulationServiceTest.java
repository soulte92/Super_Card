package esgi.al.cleancode.project.Super_Cards.domain.functional.service;

import esgi.al.cleancode.project.Super_Cards.domain.exceptions.HeroException;
import esgi.al.cleancode.project.Super_Cards.domain.functional.enums.Rarity;
import esgi.al.cleancode.project.Super_Cards.domain.functional.enums.Speciality;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Hero;
import esgi.al.cleancode.project.Super_Cards.domain.ports.server.DefaultHeroPersistenceSpi;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static esgi.al.cleancode.project.Super_Cards.domain.functional.service.HeroUtils.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultHeroesPopulationServiceTest {
    @InjectMocks
    private DefaultHeroesPopulationService service;
    @Mock
    private DefaultHeroPersistenceSpi spi;

    @Test
    void should_create_and_save_heroes_in_db() {
        val heroes = service.createAndSaveDefaultHeroes();
        assertThat(heroes).isPresent();
        assertThat(heroes.get().size()).isEqualTo(9);
    }

    @Test
    public void should_create_and_save_hero_in_db(){
        val givenName = "super-boy";
        val givenRarity = Rarity.COMMON.label;
        val givenTankSpeciality = Speciality.TANK.label;
        val givenHero = Hero.builder()
                .name(givenName)
                .speciality(givenTankSpeciality)
                .rarity(givenRarity)
                .build();

        when(spi.save(any(Hero.class))).thenReturn(givenHero);

        val actualHero = service.createAndSaveHero(givenName, givenTankSpeciality, givenRarity);

        assertThat(actualHero)
                .usingRecursiveComparison()
                .isEqualTo(givenHero);
        verifyNoMoreInteractions(spi);
    }
    @Test
    public void should_not_create_and_save_hero_in_db_with_wrong_speciality(){
        val givenName = "super-boy";
        val givenRarity = Rarity.COMMON.label;
        val givenSpeciality = "GOAT";

        Assertions.assertThrows(HeroException.class,
                ()-> service.createAndSaveHero(givenName, givenSpeciality, givenRarity));
    }
    @Test
    public void should_not_create_and_save_hero_in_db_with_wrong_rarity(){
        val givenName = "super-boy";
        val givenRarity = "JOKER";
        val givenSpeciality = Speciality.TANK.label;

        Assertions.assertThrows(HeroException.class,
                ()-> service.createAndSaveHero(givenName, givenSpeciality, givenRarity));
    }
}
