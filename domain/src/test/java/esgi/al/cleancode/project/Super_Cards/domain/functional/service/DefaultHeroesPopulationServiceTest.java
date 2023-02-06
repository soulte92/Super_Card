package esgi.al.cleancode.project.Super_Cards.domain.functional.service;

import esgi.al.cleancode.project.Super_Cards.domain.ports.server.DefaultHeroPersistenceSpi;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static io.vavr.API.Left;
import static io.vavr.API.Right;
import static org.assertj.vavr.api.VavrAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)

// TODO finish tests
class DefaultHeroesPopulationServiceTest {
    @InjectMocks
    private DefaultHeroesPopulationService service;
    @Mock
    private DefaultHeroPersistenceSpi spi;

    @Test
    void createAndSaveHeroes() {
    }

    @Test
    void createAndSaveHero() {

    }

    @Test
    void initCharacteristicsBySpeciality() {
    }

    @Test
    void enhaceCharacteriticsByRarety() {
    }

    @Test
    void enhanceCharacteristicsByPerCent() {
    }
}
