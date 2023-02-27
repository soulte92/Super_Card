package esgi.al.cleancode.project.Super_Cards.domain.functional.service;

import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Deck;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Player;
import esgi.al.cleancode.project.Super_Cards.domain.ports.server.PlayerPersistenceSpi;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PlayerCreatorServiceTest {
    @InjectMocks
    private PlayerCreatorService playerCreatorService;
    @Mock
    private DeckCreatorService deckCreatorService;
    @Mock
    private PlayerPersistenceSpi playerPersistenceSpi;

    @Test
    void should_create_and_save_player() {
        val givenDeck = Deck.builder().build();
        when(deckCreatorService.create()).thenReturn(givenDeck);

        val givenPlayer = Player.builder().build();
        when(playerPersistenceSpi.save(any(Player.class))).thenReturn(givenPlayer);

        val pseudo = "soso";
        val actualPlayer = playerCreatorService.create(pseudo);

        assertThat(actualPlayer)
                .usingRecursiveComparison()
                .isEqualTo(givenPlayer);
        verifyNoMoreInteractions(playerPersistenceSpi);
    }
    @Test
    void should_not_create_and_save_player_with_empty_deck() {
        when(deckCreatorService.create()).thenReturn(null);

        val actualPlayer = playerCreatorService.create("soso");
        assertThat(actualPlayer)
                .usingRecursiveComparison()
                .isEqualTo(null);

        verifyNoMoreInteractions(deckCreatorService);
    }
}
