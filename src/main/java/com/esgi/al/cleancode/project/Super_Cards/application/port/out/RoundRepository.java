package com.esgi.al.cleancode.project.Super_Cards.application.port.out;

import com.esgi.al.cleancode.project.Super_Cards.domain.HeroId;
import com.esgi.al.cleancode.project.Super_Cards.domain.Round;
import com.esgi.al.cleancode.project.Super_Cards.domain.RoundId;

import java.util.ArrayList;

public interface RoundRepository {
    ArrayList<Round> findAll();

    Round findByHeroId(HeroId heroId);

    Round findById(RoundId roundId);

    void save(Round round);
}
