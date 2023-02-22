package esgi.al.cleancode.project.Super_Cards.domain.ports.server;

import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Player;

import java.util.Optional;
import java.util.UUID;

public interface PlayerPersistenceSpi extends PersistenceSpi<Player, UUID> {
    @Override
    Optional<Player> findById(UUID uuid);

    Optional<Player> findByPlayerUsername(String playerUsername);
}
