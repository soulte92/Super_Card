package esgi.al.cleancode.project.Super_Cards.domain.functional.service;

import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Deck;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Player;
import esgi.al.cleancode.project.Super_Cards.domain.ports.client.PlayerCreatorApi;
import esgi.al.cleancode.project.Super_Cards.domain.ports.server.PlayerPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class PlayerCreatorService implements PlayerCreatorApi {
    private final PlayerPersistenceSpi playerPersistenceSpi;
    private final DeckCreatorService deckCreatorService;

    @Override
    public Player create(String pseudo) {
        Deck deck = deckCreatorService.create();
        if (deck == null){
            return null;
        }
        UUID deckId = deck.deckId;
        Player player = Player.builder().pseudo(pseudo).deckId(deckId).build();
        return playerPersistenceSpi.save(player);
    }

}
