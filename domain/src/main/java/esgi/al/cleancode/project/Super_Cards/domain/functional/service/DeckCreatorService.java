package esgi.al.cleancode.project.Super_Cards.domain.functional.service;

import esgi.al.cleancode.project.Super_Cards.domain.exceptions.DeckException;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Deck;
import esgi.al.cleancode.project.Super_Cards.domain.ports.client.DeckCreatorApi;
import esgi.al.cleancode.project.Super_Cards.domain.ports.server.DeckPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class DeckCreatorService implements DeckCreatorApi {
    private final DeckPersistenceSpi deckPersistenceSpi;

    @Override
    public Deck create() {
        Deck deck = Deck.builder().build();
        deck = deckPersistenceSpi.save(deck);
        if (deck == null){
            throw new DeckException("Deck Creation error");
        }
        return deck;
    }

}
