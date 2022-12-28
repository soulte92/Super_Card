package com.esgi.al.cleancode.project.Super_Cards.application.service;

import com.esgi.al.cleancode.project.Super_Cards.adapter.out.InMemoryDeckRepository;
import com.esgi.al.cleancode.project.Super_Cards.adapter.out.InMemoryHeroRepository;
import com.esgi.al.cleancode.project.Super_Cards.adapter.out.InMemoryPlayerRepository;
import com.esgi.al.cleancode.project.Super_Cards.application.DeckApplicationException;
import com.esgi.al.cleancode.project.Super_Cards.domain.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class PlayerServiceTest {

    @Test
    public void should_create_and_save_player_in_db_with_existing_deckId(){
        InMemoryHeroRepository inMemoryHeroRepository = new InMemoryHeroRepository();
        HeroConfiguration heroConfiguration = new HeroConfiguration();
        HeroService heroService = new HeroService(inMemoryHeroRepository,heroConfiguration);

        InMemoryDeckRepository inMemoryDeckRepository = new InMemoryDeckRepository();
        DeckService deckService = new DeckService(inMemoryDeckRepository,heroService);

        InMemoryPlayerRepository inMemoryPlayerRepository = new InMemoryPlayerRepository();
        PlayerService playerService = new PlayerService(inMemoryPlayerRepository, inMemoryDeckRepository);

        DeckId deckId = deckService.createDeck();
        String pseudo = "Zoro";
        PlayerId playerId = playerService.createPlayer(deckId, pseudo);
        Player player = inMemoryPlayerRepository.findById(playerId);
        Assertions.assertNotNull(player);
        Assertions.assertEquals(player.pseudo, pseudo);
    }

    @Test
    public void should_not_create_and_save_player_in_db_with_non_existing_deckId (){
        InMemoryHeroRepository inMemoryHeroRepository = new InMemoryHeroRepository();
        HeroConfiguration heroConfiguration = new HeroConfiguration();
        HeroService heroService = new HeroService(inMemoryHeroRepository,heroConfiguration);

        InMemoryDeckRepository inMemoryDeckRepository = new InMemoryDeckRepository();

        InMemoryPlayerRepository inMemoryPlayerRepository = new InMemoryPlayerRepository();
        PlayerService playerService = new PlayerService(inMemoryPlayerRepository, inMemoryDeckRepository);

        String pseudo = "Zoro";
        Assertions.assertThrows(DeckException.class, () -> {
            PlayerId playerId = playerService.createPlayer(inMemoryDeckRepository.nextId(), pseudo);
        });
    }

    @Test
    public void should_return_all_players_in_db(){
        InMemoryHeroRepository inMemoryHeroRepository = new InMemoryHeroRepository();
        HeroConfiguration heroConfiguration = new HeroConfiguration();
        HeroService heroService = new HeroService(inMemoryHeroRepository,heroConfiguration);

        InMemoryDeckRepository inMemoryDeckRepository = new InMemoryDeckRepository();
        DeckService deckService = new DeckService(inMemoryDeckRepository,heroService);

        InMemoryPlayerRepository inMemoryPlayerRepository = new InMemoryPlayerRepository();
        PlayerService playerService = new PlayerService(inMemoryPlayerRepository, inMemoryDeckRepository);

        DeckId deckId = deckService.createDeck();
        DeckId deckId2 = deckService.createDeck();
        String pseudo = "Zoro";
        String pseud2 = "toto";
        PlayerId playerId = playerService.createPlayer(deckId, pseudo);
        PlayerId playerId2 = playerService.createPlayer(deckId2, pseudo);

        ArrayList<Player> players = playerService.getPlayers();
        Assertions.assertNotNull(players);
        Assertions.assertEquals(players.size(), 2);
    }

    @Test
    public void should_return_a_player_by_PlayerId_from_db(){
        InMemoryHeroRepository inMemoryHeroRepository = new InMemoryHeroRepository();
        HeroConfiguration heroConfiguration = new HeroConfiguration();
        HeroService heroService = new HeroService(inMemoryHeroRepository,heroConfiguration);

        InMemoryDeckRepository inMemoryDeckRepository = new InMemoryDeckRepository();
        DeckService deckService = new DeckService(inMemoryDeckRepository,heroService);

        InMemoryPlayerRepository inMemoryPlayerRepository = new InMemoryPlayerRepository();
        PlayerService playerService = new PlayerService(inMemoryPlayerRepository, inMemoryDeckRepository);

        DeckId deckId = deckService.createDeck();
        String pseudo = "Zoro";
        PlayerId playerId = playerService.createPlayer(deckId, pseudo);

        Player searchedPlayer = playerService.getPlayer(playerId);
        Assertions.assertNotNull(searchedPlayer);
        Assertions.assertEquals(searchedPlayer.playerId.value(), playerId.value());
    }

    @Test
    public void should_not_return_a_player_by_PlayerId_from_db(){
        InMemoryHeroRepository inMemoryHeroRepository = new InMemoryHeroRepository();
        HeroConfiguration heroConfiguration = new HeroConfiguration();
        HeroService heroService = new HeroService(inMemoryHeroRepository,heroConfiguration);

        InMemoryDeckRepository inMemoryDeckRepository = new InMemoryDeckRepository();
        DeckService deckService = new DeckService(inMemoryDeckRepository,heroService);

        InMemoryPlayerRepository inMemoryPlayerRepository = new InMemoryPlayerRepository();
        PlayerService playerService = new PlayerService(inMemoryPlayerRepository, inMemoryDeckRepository);

        DeckId deckId = deckService.createDeck();
        String pseudo = "Zoro";
        PlayerId playerId = playerService.createPlayer(deckId, pseudo);

        Assertions.assertThrows(PlayerException.class, () -> {
            playerService.getPlayer(inMemoryPlayerRepository.nextId());
        });
    }


}
