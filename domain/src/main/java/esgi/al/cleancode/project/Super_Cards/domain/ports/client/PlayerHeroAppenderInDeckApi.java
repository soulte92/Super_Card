package esgi.al.cleancode.project.Super_Cards.domain.ports.client;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PlayerHeroAppenderInDeckApi {
    Optional<List<UUID>> appendHero(UUID playerId, String speciality, String rarity);
}
