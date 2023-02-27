package esgi.al.cleancode.project.Super_Cards.domain.functional.service;

import esgi.al.cleancode.project.Super_Cards.domain.exceptions.DeckException;
import esgi.al.cleancode.project.Super_Cards.domain.exceptions.HeroException;
import esgi.al.cleancode.project.Super_Cards.domain.exceptions.PlayerException;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Deck;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Hero;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Player;
import esgi.al.cleancode.project.Super_Cards.domain.ports.server.DeckPersistenceSpi;
import esgi.al.cleancode.project.Super_Cards.domain.ports.server.PlayerHeroPersistenceSpi;
import esgi.al.cleancode.project.Super_Cards.domain.ports.server.PlayerPersistenceSpi;
import lombok.val;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlayerDeckDisplayerServiceTest {
    @InjectMocks
    private PlayerDeckDisplayerService service;
    @Mock
    private PlayerPersistenceSpi playerPersistenceSpi;
    @Mock
    private DeckPersistenceSpi deckPersistenceSpi;
    @Mock
    private PlayerHeroPersistenceSpi playerHeroPersistenceSpi;
    @Test
    void should_display_deck_content() {
        val givenPlayer = Player.builder()
                .deckId(UUID.randomUUID())
                .build();
        val givenPlayerId = givenPlayer.getPlayerId();
        val givenDeck = Deck.builder().heroIds(new ArrayList<>()).build();
        val heroUuid = UUID.randomUUID();
        givenDeck.heroIds.add(heroUuid);
        val givenHero = Hero.builder().build();
        val givenHeroes = new ArrayList<>();
        givenHeroes.add(givenHero);

        when(playerPersistenceSpi.findById(any(UUID.class))).thenReturn(Optional.of(givenPlayer));
        when(deckPersistenceSpi.findById(any(UUID.class))).thenReturn(Optional.of(givenDeck));
        when(playerHeroPersistenceSpi.findById(any(UUID.class))).thenReturn(Optional.of(givenHero));

        val actualHeroIds = service.displayDeckContent(givenPlayerId);
        Assertions.assertThat(actualHeroIds)
                .usingRecursiveComparison()
                .isEqualTo(Optional.of(givenHeroes));

        verifyNoMoreInteractions(playerPersistenceSpi);
        verifyNoMoreInteractions(deckPersistenceSpi);
        verifyNoMoreInteractions(playerHeroPersistenceSpi);
    }

    @Test
    void should_not_display_deck_content_with_not_found_player() {
        val givenPlayer = Player.builder().build();
        val playerId = givenPlayer.getPlayerId();

        when(playerPersistenceSpi.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThatExceptionOfType(PlayerException.class).isThrownBy(()->
                service.displayDeckContent(playerId)
        );
        verifyNoMoreInteractions(playerPersistenceSpi);
    }

    @Test
    void should_not_display_deck_content_with_not_found_deck() {
        val givenPlayer = Player.builder()
                .deckId(UUID.randomUUID())
                .build();
        val givenPlayerId = givenPlayer.getPlayerId();
        val givenDeck = Deck.builder().heroIds(new ArrayList<>()).build();
        val heroUuid = UUID.randomUUID();
        givenDeck.heroIds.add(heroUuid);

        when(playerPersistenceSpi.findById(any(UUID.class))).thenReturn(Optional.of(givenPlayer));
        when(deckPersistenceSpi.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThatExceptionOfType(DeckException.class).isThrownBy(()->
                service.displayDeckContent(givenPlayerId)
        );
        verifyNoMoreInteractions(playerPersistenceSpi);
        verifyNoMoreInteractions(deckPersistenceSpi);
    }
    @Test
    void should_not_display_deck_content_with_not_found_hero() {
        val givenPlayer = Player.builder()
                .deckId(UUID.randomUUID())
                .build();
        val givenPlayerId = givenPlayer.getPlayerId();
        val givenDeck = Deck.builder().heroIds(new ArrayList<>()).build();
        val heroUuid = UUID.randomUUID();
        givenDeck.heroIds.add(heroUuid);

        when(playerPersistenceSpi.findById(any(UUID.class))).thenReturn(Optional.of(givenPlayer));
        when(deckPersistenceSpi.findById(any(UUID.class))).thenReturn(Optional.of(givenDeck));
        when(playerHeroPersistenceSpi.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThatExceptionOfType(HeroException.class).isThrownBy(()->
                service.displayDeckContent(givenPlayerId)
        );

        verifyNoMoreInteractions(playerPersistenceSpi);
        verifyNoMoreInteractions(deckPersistenceSpi);
        verifyNoMoreInteractions(playerHeroPersistenceSpi);
    }
}
