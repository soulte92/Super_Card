package esgi.al.cleancode.project.Super_Cards.domain.functional.service;

import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Player;
import esgi.al.cleancode.project.Super_Cards.domain.ports.server.PlayerPersistenceSpi;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlayerFinderServiceTest {
    @InjectMocks
    private PlayerFinderService service;
    @Mock
    private PlayerPersistenceSpi spi;
    @Test
    void should_find_player() {
        val givenPlayer = Player.builder().build();
        when(spi.findById(any(UUID.class))).thenReturn(Optional.of(givenPlayer));

        val actualPlayer = service.findPlayer(UUID.randomUUID());
        assertThat(actualPlayer)
                .usingRecursiveComparison()
                .isEqualTo(Optional.of(givenPlayer));
        verifyNoMoreInteractions(spi);
    }
    @Test
    void should_not_find_player() {
        when(spi.findById(any(UUID.class))).thenReturn(Optional.empty());

        val actualPlayer = service.findPlayer(UUID.randomUUID());
        assertThat(actualPlayer)
                .usingRecursiveComparison()
                .isEqualTo(Optional.empty());
        verifyNoMoreInteractions(spi);
    }

    @Test
    void should_find_by_player_username() {
        val givenPlayer = Player.builder().build();
        when(spi.findByPlayerUsername(anyString())).thenReturn(Optional.of(givenPlayer));

        val givenUsername = "toto";
        val actualPlayer = service.findByPlayerUsername(givenUsername);
        assertThat(actualPlayer)
                .usingRecursiveComparison()
                .isEqualTo(Optional.of(givenPlayer));
        verifyNoMoreInteractions(spi);
    }
    @Test
    void should_not_find_by_player_username() {
        when(spi.findByPlayerUsername(anyString())).thenReturn(Optional.empty());

        val givenUsername = "toto";
        val actualPlayer = service.findByPlayerUsername(givenUsername);
        assertThat(actualPlayer)
                .usingRecursiveComparison()
                .isEqualTo(Optional.empty());
        verifyNoMoreInteractions(spi);
    }
}
