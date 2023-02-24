package esgi.al.cleancode.project.Super_Cards.domain.functional.service;

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
    public Optional<Deck> create() {
        Deck deck = Deck.builder().build();
        return Optional.ofNullable(deckPersistenceSpi.save(deck));
    }

}
