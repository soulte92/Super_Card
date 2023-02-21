package esgi.al.cleancode.project.Super_Cards.domain.ports.server;

import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Round;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Session;

import java.util.Optional;
import java.util.UUID;

public interface RoundPersistenceSpi extends PersistenceSpi<Round, UUID> {
    @Override
    Optional<Round> findById(UUID uuid);
}
