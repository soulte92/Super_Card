package com.esgi.al.cleancode.project.Super_Cards.application.service;

import com.esgi.al.cleancode.project.Super_Cards.adapter.out.InMemoryDeckRepository;
import com.esgi.al.cleancode.project.Super_Cards.adapter.out.InMemoryHeroRepository;
import com.esgi.al.cleancode.project.Super_Cards.adapter.out.InMemoryPlayerRepository;
import com.esgi.al.cleancode.project.Super_Cards.application.port.out.HeroRepository;
import com.esgi.al.cleancode.project.Super_Cards.domain.DeckId;
import com.esgi.al.cleancode.project.Super_Cards.domain.HeroConfiguration;
import com.esgi.al.cleancode.project.Super_Cards.domain.Player;
import com.esgi.al.cleancode.project.Super_Cards.domain.PlayerId;
import org.checkerframework.checker.nullness.qual.AssertNonNullIfNonNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerServiceTest {

    @Test
    public void should_create_and_save_player_in_db(){
        InMemoryHeroRepository inMemoryHeroRepository = new InMemoryHeroRepository();
        HeroConfiguration heroConfiguration = new HeroConfiguration();
        HeroService heroService = new HeroService(inMemoryHeroRepository,heroConfiguration);

        InMemoryDeckRepository inMemoryDeckRepository = new InMemoryDeckRepository();
        DeckService deckService = new DeckService(inMemoryDeckRepository,heroService);

        InMemoryPlayerRepository inMemoryPlayerRepository = new InMemoryPlayerRepository();
        PlayerService playerService = new PlayerService(inMemoryPlayerRepository);

        DeckId deckId = deckService.createDeck();
        String pseudo = "Zoro";
        PlayerId playerId = playerService.createPlayer(deckId, pseudo);
        Player player = inMemoryPlayerRepository.findById(playerId);
        Assertions.assertNotNull(player);
        Assertions.assertEquals(player.pseudo, pseudo);
    }
}
