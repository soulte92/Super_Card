package com.esgi.al.cleancode.project.Super_Cards.adapter.out;

import com.esgi.al.cleancode.project.Super_Cards.application.port.out.RoundRepository;
import com.esgi.al.cleancode.project.Super_Cards.domain.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InMemoryRoundRepository implements RoundRepository {
    private final Map<RoundId, Round> registry = new HashMap<>();

    @Override
    public ArrayList<Round> findAll() {
        ArrayList<Round> roundArrayList = new ArrayList<>();
        for (Map.Entry<RoundId, Round> set : registry.entrySet()){
            roundArrayList.add(set.getValue());
        }
        return roundArrayList;
    }

    @Override
    public ArrayList<Round> findByFirstHeroId(HeroId heroId) {
        ArrayList<Round> roundArrayList = new ArrayList<>();
        for (Map.Entry<RoundId, Round> set : registry.entrySet()){
            if (set.getValue().firstPlayerHeroId.equals(heroId)){
                roundArrayList.add(set.getValue());
            }
        }
        return roundArrayList;
    }

    @Override
    public Round findById(RoundId roundId) {
        return registry.computeIfAbsent(roundId,
                key -> {
                    throw RoundException.notFoundPlayer(roundId);
                });
    }

    @Override
    public void save(Round round) {
        registry.put(round.roundId, round);
    }
}
