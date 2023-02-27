package esgi.al.cleancode.project.Super_Cards.domain.functional.service;

import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Deck;
import esgi.al.cleancode.project.Super_Cards.domain.ports.server.DeckPersistenceSpi;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeckCreatorServiceTest {

    @InjectMocks
    private DeckCreatorService service;
    @Mock
    private DeckPersistenceSpi spi;
    @Test
    void should_create_and_save_deck() {
        val givenDeck = Deck.builder().build();

        when(spi.save(any(Deck.class))).thenReturn(givenDeck);

        val actualDeck = service.create();

        assertThat(actualDeck)
                .usingRecursiveComparison()
                .isEqualTo(givenDeck);
        verifyNoMoreInteractions(spi);
    }

    @Test
    void should_not_create_and_save_deck() {
        when(spi.save(any(Deck.class))).thenThrow(new IllegalArgumentException());

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() ->
                        service.create());
        verifyNoMoreInteractions(spi);
    }
}
