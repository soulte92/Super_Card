package esgi.al.cleancode.project.Super_Cards.server.postgres.mapper;

import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Player;
import esgi.al.cleancode.project.Super_Cards.server.postgres.entity.PlayerEntity;

public interface PlayerEntityMapper {

    static Player toDomain(PlayerEntity entity) {
        return Player.builder().playerId(entity.getPlayerId())
                .pseudo(entity.getPseudo())
                .deckId(entity.getDeckId())
                .nbToken(entity.getNbToken())
                .build();
    }

    static PlayerEntity fromDomain(Player domain) {
        return PlayerEntity.builder().playerId(domain.getPlayerId())
                .pseudo(domain.getPseudo())
                .deckId(domain.getDeckId())
                .nbToken(domain.getNbToken())
                .build();
    }
}

