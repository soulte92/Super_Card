package esgi.al.cleancode.project.Super_Cards.domain.functional.service;

import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Round;
import esgi.al.cleancode.project.Super_Cards.domain.ports.server.RoundPersistenceSpi;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class RoundCreatorServiceTest {
    @InjectMocks
    private RoundCreatorService service;
    @Mock
    private RoundPersistenceSpi spi;

    @Test
    void should_create_and_save_round() {
        val givenRound = Round.builder().build();
        when(spi.save(any(Round.class))).thenReturn(givenRound);

        val givenUuid = UUID.randomUUID();
        val actualRound = service.create(givenUuid, givenUuid, givenUuid, givenUuid, givenUuid);
        assertThat(actualRound).isNotNull();

        assertThat(givenRound)
                .usingRecursiveComparison()
                .isEqualTo(givenRound);
        verifyNoMoreInteractions(spi);
    }

    @Test
    void should_not_create_and_save_round() {
        val throwable = new IllegalArgumentException();
        when(spi.save(any(Round.class))).thenThrow(throwable);

        val givenUuid = UUID.randomUUID();
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(()->
                service.create(givenUuid, givenUuid, givenUuid, givenUuid, givenUuid)
        );
        verifyNoMoreInteractions(spi);
    }
}
