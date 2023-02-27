package esgi.al.cleancode.project.Super_Cards.domain.functional.service;

import esgi.al.cleancode.project.Super_Cards.domain.ports.server.RoundPersistenceSpi;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlayerHeroRoundFinderServiceTest {
    @InjectMocks
    private PlayerHeroRoundFinderService service;
    @Mock
    private RoundPersistenceSpi spi;
    @Test
    void should_find_player_hero_rounds() {
        val givenPlayerHeroId = UUID.randomUUID();
        val givenRoundList = new ArrayList<>();

        when(spi.findByPlayerHeroId(any(UUID.class))).thenReturn(Optional.of((List)givenRoundList));

        val actualRoundList = service.findPlayerHeroRounds(givenPlayerHeroId);
        assertThat(actualRoundList)
                .usingRecursiveComparison()
                .isEqualTo(Optional.of((List)givenRoundList));
        verifyNoMoreInteractions(spi);
    }

    @Test
    void should_not_find_player_hero_rounds() {
        val givenPlayerHeroId = UUID.randomUUID();

        when(spi.findByPlayerHeroId(any(UUID.class))).thenReturn(Optional.empty());

        val actualRoundList = service.findPlayerHeroRounds(givenPlayerHeroId);
        assertThat(actualRoundList)
                .usingRecursiveComparison()
                .isEqualTo(Optional.empty());
        verifyNoMoreInteractions(spi);
    }
}
