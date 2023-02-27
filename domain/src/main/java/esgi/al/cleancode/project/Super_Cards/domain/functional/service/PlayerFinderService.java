package esgi.al.cleancode.project.Super_Cards.domain.functional.service;

import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Player;
import esgi.al.cleancode.project.Super_Cards.domain.ports.client.PlayerFinderApi;
import esgi.al.cleancode.project.Super_Cards.domain.ports.server.PlayerPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class PlayerFinderService implements PlayerFinderApi {
    private final PlayerPersistenceSpi playerPersistenceSpi;

    @Override
    public Optional<Player> findPlayer(UUID playerId) {
        return playerPersistenceSpi.findById(playerId);
    }

    @Override
    public Optional<Player> findByPlayerUsername(String playerUsername) {
        return playerPersistenceSpi.findByPlayerUsername(playerUsername);
    }
}
