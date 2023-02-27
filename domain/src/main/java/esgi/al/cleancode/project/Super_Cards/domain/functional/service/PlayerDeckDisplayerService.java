package esgi.al.cleancode.project.Super_Cards.domain.functional.service;

import esgi.al.cleancode.project.Super_Cards.domain.exceptions.DeckException;
import esgi.al.cleancode.project.Super_Cards.domain.exceptions.HeroException;
import esgi.al.cleancode.project.Super_Cards.domain.exceptions.PlayerException;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Deck;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Hero;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Player;
import esgi.al.cleancode.project.Super_Cards.domain.ports.client.PlayerDeckDisplayerApi;
import esgi.al.cleancode.project.Super_Cards.domain.ports.server.DeckPersistenceSpi;
import esgi.al.cleancode.project.Super_Cards.domain.ports.server.PlayerHeroPersistenceSpi;
import esgi.al.cleancode.project.Super_Cards.domain.ports.server.PlayerPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class PlayerDeckDisplayerService implements PlayerDeckDisplayerApi {

    private final PlayerPersistenceSpi playerPersistenceSpi;
    private final DeckPersistenceSpi deckPersistenceSpi;
    private final PlayerHeroPersistenceSpi playerHeroPersistenceSpi;

    @Override
    public Optional<List<Hero>> displayDeckContent(UUID playerId) {
        Optional<Player> player = playerPersistenceSpi.findById(playerId);
        if (player.isEmpty()) {
            throw PlayerException.notFoundPlayer(playerId);
        }
        Optional<Deck> deck = deckPersistenceSpi.findById(player.get().getDeckId());
        if (deck.isEmpty()) {
            throw DeckException.notFoundDeck(player.get().getDeckId());
        }
        List<Hero> heroList = new ArrayList<>();

        for (UUID heroId : deck.get().heroIds) {
            Optional<Hero> hero = playerHeroPersistenceSpi.findById(heroId);
            if (hero.isEmpty()) {
                throw HeroException.notFoundHero(heroId);
            }
            heroList.add(hero.get());
        }

        return Optional.of(heroList);
    }
}
