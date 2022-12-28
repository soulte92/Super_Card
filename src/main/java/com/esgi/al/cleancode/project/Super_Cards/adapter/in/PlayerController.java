package com.esgi.al.cleancode.project.Super_Cards.adapter.in;

import com.esgi.al.cleancode.project.Super_Cards.application.service.DeckService;
import com.esgi.al.cleancode.project.Super_Cards.application.service.PlayerService;
import com.esgi.al.cleancode.project.Super_Cards.domain.*;

import java.util.ArrayList;

public class PlayerController {
    private final PlayerService playerService;

    private final DeckService deckService;

    public PlayerController(PlayerService playerService, DeckService deckService) {
        this.playerService = playerService;
        this.deckService = deckService;
    }

    public PlayerId createPlayer(String pseudo){
        DeckId deckId = deckService.createDeck();
        return playerService.createPlayer(deckId, pseudo);
    }

    public Player getPlayer(PlayerId playerId){
        return playerService.getPlayer(playerId);
    }

    public ArrayList<Player> getPlayers(){
        return playerService.getPlayers();
    }
}
