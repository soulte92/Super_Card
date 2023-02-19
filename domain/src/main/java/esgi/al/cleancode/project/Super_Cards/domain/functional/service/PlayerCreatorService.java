package esgi.al.cleancode.project.Super_Cards.domain.functional.service;

import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Player;
import esgi.al.cleancode.project.Super_Cards.domain.ports.client.PlayerCreatorApi;
import esgi.al.cleancode.project.Super_Cards.domain.ports.server.PlayerPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class PlayerCreatorService implements PlayerCreatorApi {
    private final PlayerPersistenceSpi playerPersistenceSpi;

    @Override
    public Optional<Player> create(String pseudo) {
        UUID deckId = UUID.randomUUID(); // TODO to replace with real create deckId
        Player player = Player.builder().pseudo(pseudo).deckId(deckId).build();
        return Optional.ofNullable(playerPersistenceSpi.save(player));
    }

}
