package com.esgi.al.cleancode.project.Super_Cards.application.service;

import com.esgi.al.cleancode.project.Super_Cards.application.port.out.PlayerRepository;
import com.esgi.al.cleancode.project.Super_Cards.domain.DeckId;
import com.esgi.al.cleancode.project.Super_Cards.domain.Player;
import com.esgi.al.cleancode.project.Super_Cards.domain.PlayerId;

public class PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }
    
    public PlayerId createPlayer(DeckId deckId, String pseudo){
        PlayerId playerId = playerRepository.nextId();
        Player player = new Player(playerId, deckId, pseudo);
        playerRepository.save(player);
        return playerId;
    }

    public Player getPlayer(PlayerId playerId){
        return playerRepository.findById(playerId);
    }
}
