package com.esgi.al.cleancode.project.Super_Cards.application.port.out;

import com.esgi.al.cleancode.project.Super_Cards.domain.Player;
import com.esgi.al.cleancode.project.Super_Cards.domain.PlayerId;

import java.util.ArrayList;

public interface PlayerRepository {
    ArrayList<Player> findAll();

    Player findById(PlayerId playerId);

    void save(Player player);
}
