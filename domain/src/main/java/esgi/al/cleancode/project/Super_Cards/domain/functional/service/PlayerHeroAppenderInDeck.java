package esgi.al.cleancode.project.Super_Cards.domain.functional.service;

import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Deck;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Hero;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Player;
import esgi.al.cleancode.project.Super_Cards.domain.ports.client.PlayerHeroAppenderInDeckApi;
import esgi.al.cleancode.project.Super_Cards.domain.ports.server.DeckPersistenceSpi;
import esgi.al.cleancode.project.Super_Cards.domain.ports.server.PlayerPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class PlayerHeroAppenderInDeck implements PlayerHeroAppenderInDeckApi {
    private final PlayerPersistenceSpi playerPersistenceSpi;
    private final DeckPersistenceSpi deckPersistenceSpi;
    private final PlayerHeroCreatorService playerHeroCreatorService;

    @Override
    public Optional<List<UUID>> appendHero(UUID playerId, String speciality, String rarity) {
        Optional<Player> player = playerPersistenceSpi.findById(playerId);
        if (player.isEmpty()){
            //TODO throw exception
            return Optional.empty();
        }

        Optional<Deck> deck = deckPersistenceSpi.findById(player.get().getDeckId());
        if (deck.isEmpty()){
            //TODO throw exception
            return Optional.empty();
        }

        Hero hero = playerHeroCreatorService.pickHeroFromDefaultHero(speciality, rarity);

        Deck newDeck = deck.get();
        newDeck.heroIds.add(hero.getHeroId());

        deckPersistenceSpi.save(newDeck);

        return Optional.of(newDeck.heroIds);
    }

}
