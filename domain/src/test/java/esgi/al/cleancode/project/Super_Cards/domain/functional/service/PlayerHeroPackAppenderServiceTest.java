package esgi.al.cleancode.project.Super_Cards.domain.functional.service;

import esgi.al.cleancode.project.Super_Cards.domain.exceptions.ApplicationException;
import esgi.al.cleancode.project.Super_Cards.domain.exceptions.DeckException;
import esgi.al.cleancode.project.Super_Cards.domain.exceptions.PlayerException;
import esgi.al.cleancode.project.Super_Cards.domain.functional.enums.PackType;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Hero;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Player;
import esgi.al.cleancode.project.Super_Cards.domain.ports.server.PlayerHeroPersistenceSpi;
import esgi.al.cleancode.project.Super_Cards.domain.ports.server.PlayerPersistenceSpi;
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

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlayerHeroPackAppenderServiceTest {
    @InjectMocks
    private PlayerHeroPackAppenderService service;
    @Mock
    private PlayerPersistenceSpi playerPersistenceSpi;
    @Mock
    private PlayerHeroPersistenceSpi playerHeroPersistenceSpi;
    @Mock
    private PlayerHeroAppenderInDeckService playerHeroAppenderInDeckService;
    @Test
    void should_create_and_append_pack() {
        val givenPlayerId = UUID.randomUUID();
        val givenPackType = PackType.DIAMOND.label;
        val givenPlayer = Player.builder().build();
        val givenHero = Hero.builder().build();
        val givenHeroIds = new ArrayList<>();

        when(playerPersistenceSpi.findById(any(UUID.class))).thenReturn(Optional.of(givenPlayer));
        when(playerPersistenceSpi.save(any(Player.class))).thenReturn(givenPlayer);
        when(playerHeroAppenderInDeckService.appendHero(any(UUID.class), anyString(), anyString())).thenReturn(Optional.of((List)givenHeroIds));

        val actualHeroes = service.createAndAppendPack(givenPlayerId, givenPackType);
        assertThat(actualHeroes)
                .usingRecursiveComparison()
                .isEqualTo(Optional.of((List)givenHeroIds));
        verifyNoMoreInteractions(playerPersistenceSpi);
        verifyNoMoreInteractions(playerHeroPersistenceSpi);
        verifyNoMoreInteractions(playerHeroAppenderInDeckService);
    }
    @Test
    void should_not_create_and_append_pack_with_no_found_player() {
        val givenPlayerId = UUID.randomUUID();
        val givenPackType = PackType.DIAMOND.label;
        val givenPlayer = Player.builder().build();

        when(playerPersistenceSpi.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThatExceptionOfType(PlayerException.class).isThrownBy(()->
                service.createAndAppendPack(givenPlayerId, givenPackType)
        );
        verifyNoMoreInteractions(playerPersistenceSpi);
    }
    @Test
    void should_not_create_and_append_pack_with_incorrect_packType() {
        val givenPlayerId = UUID.randomUUID();
        val givenPackType = "GOLD";
        val givenPlayer = Player.builder().build();

        when(playerPersistenceSpi.findById(any(UUID.class))).thenReturn(Optional.of(givenPlayer));

        assertThatExceptionOfType(ApplicationException.class).isThrownBy(()->
                service.createAndAppendPack(givenPlayerId, givenPackType)
        );
    }
}
