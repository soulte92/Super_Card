package esgi.al.cleancode.project.Super_Cards.domain.functional.service;

import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Session;
import esgi.al.cleancode.project.Super_Cards.domain.ports.client.SessionCreatorApi;
import esgi.al.cleancode.project.Super_Cards.domain.ports.server.SessionPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class SessionCreatorService implements SessionCreatorApi {

    private final SessionPersistenceSpi sessionPersistenceSpi;

    @Override
    public Optional<Session> create(List<UUID> playerIds) {
        Session session = Session.builder().playerIds(playerIds).build();
        return Optional.of(sessionPersistenceSpi.save(session));
    }
}
