package esgi.al.cleancode.project.Super_Cards.domain.ports.client;

import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Session;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SessionCreatorApi {
    Session create(List<UUID> playerIds);
}
