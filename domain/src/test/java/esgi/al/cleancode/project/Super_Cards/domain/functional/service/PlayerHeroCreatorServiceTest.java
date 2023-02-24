package esgi.al.cleancode.project.Super_Cards.domain.functional.service;

import esgi.al.cleancode.project.Super_Cards.domain.exceptions.HeroException;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Hero;
import esgi.al.cleancode.project.Super_Cards.domain.ports.server.DefaultHeroPersistenceSpi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class PlayerHeroCreatorServiceTest {
    @InjectMocks
    private PlayerHeroCreatorService service;
    @Mock
    private DefaultHeroPersistenceSpi spi;

    @Test
    void createAndSavePlayerHeroFromDefaultHero() {

        Hero givenHero = Hero.builder().build();
        when(spi.findBySpecialityAndRarity(anyString(), anyString())).thenReturn(Optional.ofNullable(givenHero));
        givenHero = spi.findBySpecialityAndRarity(anyString(), anyString()).get();
        when(service.savePlayerHero(givenHero)).thenReturn(givenHero);

        Hero actualHero = service.pickHeroFromDefaultHero(anyString(), anyString());
        Assertions.assertSame(actualHero, givenHero);

    }

    @Test
    void should_find_default_hero() {
        Hero givenHero = Hero.builder().build();

        when(spi.findBySpecialityAndRarity(anyString(), anyString())).thenReturn(Optional.ofNullable(givenHero));

        Optional<Hero> actual = service.findDefaultHero(anyString(), anyString());
        Assertions.assertSame(actual.get(), givenHero);
    }

    @Test
    void should_not_find_default_hero() {
        when(spi.findBySpecialityAndRarity(anyString(), anyString())).thenReturn(Optional.empty());

        Assertions.assertThrows(HeroException.class, () -> {
            service.findDefaultHero("ff", "fff");
        });
    }

    @Test
    void savePlayerHero() {
        Hero givenHero = Hero.builder().build();

        when(spi.findBySpecialityAndRarity(anyString(), anyString())).thenReturn(Optional.empty());

        Assertions.assertThrows(HeroException.class, () -> {
            service.findDefaultHero(anyString(), anyString());
        });
    }
}