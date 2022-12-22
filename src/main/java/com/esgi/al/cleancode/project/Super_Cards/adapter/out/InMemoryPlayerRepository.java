package com.esgi.al.cleancode.project.Super_Cards.adapter.out;

import com.esgi.al.cleancode.project.Super_Cards.application.port.out.PlayerRepository;
import com.esgi.al.cleancode.project.Super_Cards.domain.Player;
import com.esgi.al.cleancode.project.Super_Cards.domain.PlayerException;
import com.esgi.al.cleancode.project.Super_Cards.domain.PlayerId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InMemoryPlayerRepository implements PlayerRepository {
    private final Map<PlayerId, Player> registry = new HashMap<>();

    @Override
    public ArrayList<Player> findAll() {
        return null;
    }

    public Player findById(PlayerId playerId) {
        return registry.computeIfAbsent(playerId,
                key -> {
                    throw PlayerException.notFoundPlayer(playerId);
                });
    }

    @Override
    public PlayerId nextId() {
        return PlayerId.of(UUID.randomUUID());
    }

    @Override
    public void save(Player player) {
        registry.put(player.playerId, player);
    }
}
