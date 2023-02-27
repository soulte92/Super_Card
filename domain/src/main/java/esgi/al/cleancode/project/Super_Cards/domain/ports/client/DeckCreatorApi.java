package esgi.al.cleancode.project.Super_Cards.domain.ports.client;

import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Deck;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Player;

import java.util.Optional;

public interface DeckCreatorApi {
    Deck create();
}
