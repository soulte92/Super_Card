package esgi.al.cleancode.project.Super_Cards.domain.functional.service;

import esgi.al.cleancode.project.Super_Cards.domain.exceptions.SessionException;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Session;
import esgi.al.cleancode.project.Super_Cards.domain.ports.server.SessionPersistenceSpi;
import lombok.val;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SessionCreatorServiceTest {
    @InjectMocks
    private SessionCreatorService service;
    @Mock
    private SessionPersistenceSpi spi;

    @Test
    void should_create_and_save_session() {
        val givenPlayerIds = new ArrayList<UUID>();
        givenPlayerIds.add(UUID.randomUUID());

        val givenSession = Session.builder().build();
        when(spi.save(any(Session.class))).thenReturn(givenSession);

        val actualSession = service.create(givenPlayerIds);

        AssertionsForClassTypes.assertThat(actualSession)
                .usingRecursiveComparison()
                .isEqualTo(givenSession);
        verifyNoMoreInteractions(spi);
    }

    @Test
    void should_not_create_and_save_session() {
        val givenPlayerIds = new ArrayList<UUID>();

        when(spi.save(any(Session.class))).thenReturn(null);

        assertThatExceptionOfType(SessionException.class).isThrownBy(()->
            service.create(givenPlayerIds)
        );
        verifyNoMoreInteractions(spi);
    }
}
