package esgi.al.cleancode.project.Super_Cards.server.postgres.mapper;

import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Round;
import esgi.al.cleancode.project.Super_Cards.server.postgres.entity.RoundEntity;

public interface RoundEntityMapper {

    static Round toDomain(RoundEntity entity) {
        return Round.builder()
                .roundId(entity.getRoundId())
                .sessionId(entity.getSessionId())
                .firstPlayerId(entity.getFirstPlayerId())
                .secondPlayerId(entity.getSecondPlayerId())
                .firstPlayerHeroId(entity.getFirstPlayerHeroId())
                .secondPlayerHeroId(entity.getSecondPlayerHeroId())
                .firstPlayerHeroNbHit(entity.getFirstPlayerHeroNbHit())
                .secondPlayerHeroNbHit(entity.getSecondPlayerHeroNbHit())
                .winner(entity.getWinner())
                .creationDate(entity.getCreationDate())
                .build();
    }

    static RoundEntity fromDomain(Round domain) {
        return RoundEntity.builder()
                .roundId(domain.getRoundId())
                .sessionId(domain.getSessionId())
                .firstPlayerId(domain.getFirstPlayerId())
                .secondPlayerId(domain.getSecondPlayerId())
                .firstPlayerHeroId(domain.getFirstPlayerHeroId())
                .secondPlayerHeroId(domain.getSecondPlayerHeroId())
                .firstPlayerHeroNbHit(domain.getFirstPlayerHeroNbHit())
                .secondPlayerHeroNbHit(domain.getSecondPlayerHeroNbHit())
                .winner(domain.getWinner())
                .creationDate(domain.creationDate)
                .build();
    }
}

