package esgi.al.cleancode.project.Super_Cards.domain.ports.client;

import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Round;

import java.util.Optional;
import java.util.UUID;

public interface BattleApi {
    Optional<Round> attack(UUID sessionId, UUID firstPlayerId, UUID secondPlayerId, UUID firstPlayerHeroId, UUID secondPlayerHeroId);
}
