package com.esgi.al.cleancode.project.Super_Cards.application.service;

import com.esgi.al.cleancode.project.Super_Cards.application.DeckApplicationException;
import com.esgi.al.cleancode.project.Super_Cards.application.port.out.DeckRepository;
import com.esgi.al.cleancode.project.Super_Cards.application.port.out.PlayerRepository;
import com.esgi.al.cleancode.project.Super_Cards.domain.DeckException;
import com.esgi.al.cleancode.project.Super_Cards.domain.DeckId;
import com.esgi.al.cleancode.project.Super_Cards.domain.Player;
import com.esgi.al.cleancode.project.Super_Cards.domain.PlayerId;

import java.util.ArrayList;

public class PlayerService {
    private final PlayerRepository playerRepository;
    private final DeckRepository deckRepository;

    public PlayerService(PlayerRepository playerRepository, DeckRepository deckRepository) {
        this.playerRepository = playerRepository;
        this.deckRepository = deckRepository;
    }
    
    public PlayerId createPlayer(DeckId deckId, String pseudo){
        PlayerId playerId = playerRepository.nextId();
        if (deckRepository.findById(deckId) == null){
            // TODO correct
            throw DeckApplicationException.notFoundDeck(deckId);
        }
        Player player = new Player(playerId, deckId, pseudo);
        playerRepository.save(player);
        return playerId;
    }

    public Player getPlayer(PlayerId playerId){
        return playerRepository.findById(playerId);
    }

    public ArrayList<Player> getPlayers(){
        return playerRepository.findAll();
    }
}
