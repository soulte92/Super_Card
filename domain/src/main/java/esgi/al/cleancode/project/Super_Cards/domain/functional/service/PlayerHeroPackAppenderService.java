package esgi.al.cleancode.project.Super_Cards.domain.functional.service;

import esgi.al.cleancode.project.Super_Cards.domain.exceptions.ApplicationException;
import esgi.al.cleancode.project.Super_Cards.domain.exceptions.HeroException;
import esgi.al.cleancode.project.Super_Cards.domain.exceptions.PlayerException;
import esgi.al.cleancode.project.Super_Cards.domain.functional.enums.PackType;
import esgi.al.cleancode.project.Super_Cards.domain.functional.enums.RarityGenerator;
import esgi.al.cleancode.project.Super_Cards.domain.functional.enums.SpecialityGenerator;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Hero;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Player;
import esgi.al.cleancode.project.Super_Cards.domain.ports.client.PlayerHeroPackAppenderApi;
import esgi.al.cleancode.project.Super_Cards.domain.ports.server.PlayerHeroPersistenceSpi;
import esgi.al.cleancode.project.Super_Cards.domain.ports.server.PlayerPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class PlayerHeroPackAppenderService implements PlayerHeroPackAppenderApi {
    private final PlayerPersistenceSpi playerPersistenceSpi;
    private final PlayerHeroPersistenceSpi playerHeroPersistenceSpi;
    private final PlayerHeroAppenderInDeckService playerHeroAppenderInDeckService;

    @Override
    public Optional<List<Hero>> createAndAppendPack(UUID playerId, String packType) {
        Optional<Player> player = playerPersistenceSpi.findById(playerId);
        if (player.isEmpty()) {
            throw PlayerException.notFoundPlayer(playerId);
        }

        List<UUID> createdHeroids = new ArrayList<>();
        Player newPlayer = null;
        if (!packType.equals(PackType.SILVER.label) && !packType.equals(PackType.DIAMOND.label)) {
            throw new ApplicationException(String.format("createAndAppendPack error: Invalid packType, you should choose packType between '%s' or '%s'",
                    PackType.SILVER, PackType.DIAMOND));
        } else if (packType.equals(PackType.SILVER.label) && player.get().getNbToken() >= 1) {
            newPlayer = Player.builder()
                    .playerId(player.get().getPlayerId())
                    .pseudo(player.get().getPseudo())
                    .deckId(player.get().getDeckId())
                    .nbToken(player.get().getNbToken() - 1)
                    .build();
            playerHeroAppenderInDeckService.appendHero(playerId,
                    SpecialityGenerator.generateRandomSpeciality(), RarityGenerator.generateSilverCardRarity()).get();
            playerHeroAppenderInDeckService.appendHero(playerId,
                    SpecialityGenerator.generateRandomSpeciality(), RarityGenerator.generateSilverCardRarity()).get();
            createdHeroids = playerHeroAppenderInDeckService.appendHero(playerId,
                    SpecialityGenerator.generateRandomSpeciality(), RarityGenerator.generateSilverCardRarity()).get();
        } else {
            newPlayer = Player.builder()
                    .playerId(player.get().getPlayerId())
                    .pseudo(player.get().getPseudo())
                    .deckId(player.get().getDeckId())
                    .nbToken(player.get().getNbToken() - 2)
                    .build();
            playerHeroAppenderInDeckService.appendHero(playerId,
                    SpecialityGenerator.generateRandomSpeciality(), RarityGenerator.generateDiamondCardRarity()).get();
            playerHeroAppenderInDeckService.appendHero(playerId,
                    SpecialityGenerator.generateRandomSpeciality(), RarityGenerator.generateDiamondCardRarity()).get();
            playerHeroAppenderInDeckService.appendHero(playerId,
                    SpecialityGenerator.generateRandomSpeciality(), RarityGenerator.generateDiamondCardRarity()).get();
            playerHeroAppenderInDeckService.appendHero(playerId,
                    SpecialityGenerator.generateRandomSpeciality(), RarityGenerator.generateDiamondCardRarity()).get();
            createdHeroids = playerHeroAppenderInDeckService.appendHero(playerId,
                    SpecialityGenerator.generateRandomSpeciality(), RarityGenerator.generateDiamondCardRarity()).get();
        }
        Player createdHeroids_player = playerPersistenceSpi.save(newPlayer);
        if (createdHeroids_player == null) {
            throw new PlayerException(String.format("createAndAppendPack error: Save playerId [%s] error ", newPlayer.playerId.toString()));
        }

        List<Hero> createdHeroes = new ArrayList<>();
        for (UUID heroId : createdHeroids) {
            Optional<Hero> hero = playerHeroPersistenceSpi.findById(heroId);
            if (hero.isEmpty()) {
                throw HeroException.notFoundHero(heroId);
            }
            createdHeroes.add(hero.get());
        }

        return Optional.of(createdHeroes);
    }
}
