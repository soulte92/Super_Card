package esgi.al.cleancode.project.Super_Cards.domain.functional.service;

import esgi.al.cleancode.project.Super_Cards.domain.exceptions.DeckException;
import esgi.al.cleancode.project.Super_Cards.domain.exceptions.HeroException;
import esgi.al.cleancode.project.Super_Cards.domain.exceptions.PlayerException;
import esgi.al.cleancode.project.Super_Cards.domain.exceptions.RoundException;
import esgi.al.cleancode.project.Super_Cards.domain.functional.enums.Rarity;
import esgi.al.cleancode.project.Super_Cards.domain.functional.enums.Speciality;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Deck;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Hero;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Player;
import esgi.al.cleancode.project.Super_Cards.domain.ports.server.DeckPersistenceSpi;
import esgi.al.cleancode.project.Super_Cards.domain.ports.server.PlayerPersistenceSpi;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlayerHeroAppenderInDeckServiceTest {
    @InjectMocks
    private PlayerHeroAppenderInDeckService service;
    @Mock
    private PlayerPersistenceSpi playerPersistenceSpi;
    @Mock
    private DeckPersistenceSpi deckPersistenceSpi;
    @Mock
    private PlayerHeroCreatorService playerHeroCreatorService;
    @Test
    void should_append_hero() {
        val givenPlayer = Player.builder().deckId(UUID.randomUUID()).build();
        val giveHero = Hero.builder().build();
        val givenDeck = Deck.builder().heroIds(new ArrayList<>()).build();
        givenDeck.heroIds.add(giveHero.getHeroId());

        when(playerPersistenceSpi.findById(any(UUID.class))).thenReturn(Optional.of(givenPlayer));
        when(deckPersistenceSpi.findById(any(UUID.class))).thenReturn(Optional.of(givenDeck));
        when(playerHeroCreatorService.pickHeroFromDefaultHero(anyString(), anyString())).thenReturn(giveHero);

        when(deckPersistenceSpi.save(any(Deck.class))).thenReturn(givenDeck);

        val givenPlayerId = UUID.randomUUID();
        val givenSpeciality = Speciality.TANK.label;
        val givenRarity = Rarity.RARE.label;

        val actualHeroes = service.appendHero(givenPlayerId, givenSpeciality, givenRarity);
        assertThat(actualHeroes).isPresent();
        assertThat(actualHeroes).containsSame(givenDeck.heroIds);

        verifyNoMoreInteractions(deckPersistenceSpi);
        verifyNoMoreInteractions(playerPersistenceSpi);
        verifyNoMoreInteractions(playerHeroCreatorService);
    }
    @Test
    void should_not_append_hero_with_not_found_player() {
        when(playerPersistenceSpi.findById(any(UUID.class))).thenReturn(Optional.empty());

        val givenPlayerId = UUID.randomUUID();
        val givenSpeciality = Speciality.TANK.label;
        val givenRarity = Rarity.RARE.label;

        assertThatExceptionOfType(PlayerException.class).isThrownBy(()->
                service.appendHero(givenPlayerId, givenSpeciality, givenRarity)
        );
        verifyNoMoreInteractions(playerPersistenceSpi);
    }

    @Test
    void should_not_append_hero_with_not_found_deck() {
        val givenPlayer = Player.builder().deckId(UUID.randomUUID()).build();
        val giveHero = Hero.builder().build();
        val givenDeck = Deck.builder().heroIds(new ArrayList<>()).build();
        givenDeck.heroIds.add(giveHero.getHeroId());

        when(playerPersistenceSpi.findById(any(UUID.class))).thenReturn(Optional.of(givenPlayer));
        when(deckPersistenceSpi.findById(any(UUID.class))).thenReturn(Optional.empty());

        val givenPlayerId = UUID.randomUUID();
        val givenSpeciality = Speciality.TANK.label;
        val givenRarity = Rarity.RARE.label;

        assertThatExceptionOfType(DeckException.class).isThrownBy(()->
                service.appendHero(givenPlayerId, givenSpeciality, givenRarity)
        );
        verifyNoMoreInteractions(playerPersistenceSpi);
        verifyNoMoreInteractions(deckPersistenceSpi);
    }

    @Test
    void should_not_append_hero_with_not_found_hero() {
        val givenPlayer = Player.builder().deckId(UUID.randomUUID()).build();
        val giveHero = Hero.builder().build();
        val givenDeck = Deck.builder().heroIds(new ArrayList<>()).build();
        givenDeck.heroIds.add(giveHero.getHeroId());

        when(playerPersistenceSpi.findById(any(UUID.class))).thenReturn(Optional.of(givenPlayer));
        when(deckPersistenceSpi.findById(any(UUID.class))).thenReturn(Optional.of(givenDeck));
        when(playerHeroCreatorService.pickHeroFromDefaultHero(anyString(), anyString())).thenReturn(null);

        val givenPlayerId = UUID.randomUUID();
        val givenSpeciality = Speciality.TANK.label;
        val givenRarity = Rarity.RARE.label;

        assertThatExceptionOfType(HeroException.class).isThrownBy(()->
                service.appendHero(givenPlayerId, givenSpeciality, givenRarity)
        );
        verifyNoMoreInteractions(playerPersistenceSpi);
    }
}
