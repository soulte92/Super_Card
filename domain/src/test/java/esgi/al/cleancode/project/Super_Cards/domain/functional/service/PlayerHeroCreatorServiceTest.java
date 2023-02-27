package esgi.al.cleancode.project.Super_Cards.domain.functional.service;

import esgi.al.cleancode.project.Super_Cards.domain.exceptions.HeroException;
import esgi.al.cleancode.project.Super_Cards.domain.functional.enums.Rarity;
import esgi.al.cleancode.project.Super_Cards.domain.functional.enums.Speciality;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Hero;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Player;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Session;
import esgi.al.cleancode.project.Super_Cards.domain.ports.server.DefaultHeroPersistenceSpi;
import esgi.al.cleancode.project.Super_Cards.domain.ports.server.PlayerHeroPersistenceSpi;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayerHeroCreatorServiceTest {
    @InjectMocks
    private PlayerHeroCreatorService service;
    @Mock
    private DefaultHeroPersistenceSpi defaultHeroPersistenceSpi;
    @Mock
    private PlayerHeroPersistenceSpi playerHeroPersistenceSpi;

    @Test
    void should_pick_and_save_player_hero_from_default_hero() {
        val givenHero = Hero.builder().build();

        when(defaultHeroPersistenceSpi.findBySpecialityAndRarity(anyString(), anyString())).thenReturn(Optional.of(givenHero));
        when(playerHeroPersistenceSpi.save(any(Hero.class))).thenReturn(givenHero);

        val speciality = Speciality.TANK.label;
        val rarity = Rarity.RARE.label;
        val actualHero = service.pickHeroFromDefaultHero(speciality, rarity);
        assertThat(actualHero)
                .usingRecursiveComparison()
                .isEqualTo(givenHero);
        verifyNoMoreInteractions(defaultHeroPersistenceSpi);
        verifyNoMoreInteractions(playerHeroPersistenceSpi);
    }

    @Test
    void should_not_pick_and_save_player_hero_from_default_hero_with_no_found_hero() {
        when(defaultHeroPersistenceSpi.findBySpecialityAndRarity(anyString(), anyString())).thenReturn(Optional.empty());

        val speciality = Speciality.TANK.label;
        val rarity = Rarity.RARE.label;
        val actualHero = service.pickHeroFromDefaultHero(speciality, rarity);
        assertThat(actualHero)
                .usingRecursiveComparison()
                .isEqualTo(null);
        verifyNoMoreInteractions(defaultHeroPersistenceSpi);
    }

    @Test
    void should_get_alive_hero() {
        val givenHeroes = new ArrayList<Hero>();

        when(playerHeroPersistenceSpi.findAliveHeroes()).thenReturn(Optional.of(givenHeroes));

        val actualHeroes = service.getAliveHeroes();
        assertThat(actualHeroes)
                .usingRecursiveComparison()
                .isEqualTo(Optional.of(givenHeroes));
        verifyNoMoreInteractions(playerHeroPersistenceSpi);
    }

    @Test
    void should_not_get_alive_hero() {
        when(playerHeroPersistenceSpi.findAliveHeroes()).thenReturn(Optional.empty());

        val actualHeroes = service.getAliveHeroes();
        assertThat(actualHeroes)
                .usingRecursiveComparison()
                .isEqualTo(Optional.empty());
        verifyNoMoreInteractions(playerHeroPersistenceSpi);
    }
}