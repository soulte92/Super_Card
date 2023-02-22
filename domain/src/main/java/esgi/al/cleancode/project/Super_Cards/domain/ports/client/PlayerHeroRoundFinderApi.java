package esgi.al.cleancode.project.Super_Cards.domain.ports.client;

import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Deck;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Hero;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Round;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PlayerHeroRoundFinderApi {
    Optional<List<Round>> findPlayerHeroRounds(UUID playerHeroId);
}
