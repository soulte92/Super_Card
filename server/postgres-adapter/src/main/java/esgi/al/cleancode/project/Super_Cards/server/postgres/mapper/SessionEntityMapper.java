package esgi.al.cleancode.project.Super_Cards.server.postgres.mapper;

import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Session;
import esgi.al.cleancode.project.Super_Cards.server.postgres.entity.SessionEntity;

public interface SessionEntityMapper {

    static Session toDomain(SessionEntity entity) {
        return Session.builder()
                .sessionId(entity.getSessionId())
                .playerIds(entity.getPlayerIds())
                .creationDate(entity.getCreationDate())
                .build();
    }

    static SessionEntity fromDomain(Session domain) {
        return SessionEntity.builder()
                .sessionId(domain.getSessionId())
                .playerIds(domain.getPlayerIds())
                .creationDate(domain.getCreationDate())
                .build();
    }
}

