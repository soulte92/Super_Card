package esgi.al.cleancode.project.Super_Cards.domain.ports.client;

import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Deck;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Player;

import java.util.Optional;
import java.util.UUID;

public interface PlayerFinderApi {
    Optional<Player> findPlayer(UUID playerId);
    Optional<Player> findByPlayerUsername(String playerUsername);
}
