package esgi.al.cleancode.project.Super_Cards.domain.ports.client;

import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Player;
import java.util.Optional;

public interface PlayerCreatorApi {
    Optional<Player> create(String pseudo);
}
