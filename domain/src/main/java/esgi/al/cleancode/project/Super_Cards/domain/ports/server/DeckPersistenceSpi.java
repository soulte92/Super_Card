package esgi.al.cleancode.project.Super_Cards.domain.ports.server;

import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Deck;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Player;

import java.util.Optional;
import java.util.UUID;

public interface DeckPersistenceSpi extends PersistenceSpi<Deck, UUID> {
    @Override
    Optional<Deck> findById(UUID uuid);
}
