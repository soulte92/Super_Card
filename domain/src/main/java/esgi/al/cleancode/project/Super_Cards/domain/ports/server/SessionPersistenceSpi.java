package esgi.al.cleancode.project.Super_Cards.domain.ports.server;

import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Deck;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Session;

import java.util.Optional;
import java.util.UUID;

public interface SessionPersistenceSpi extends PersistenceSpi<Session, UUID> {
    @Override
    Optional<Session> findById(UUID uuid);
}
