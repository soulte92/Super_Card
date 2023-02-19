package esgi.al.cleancode.project.Super_Cards.domain.functional.service;

import esgi.al.cleancode.project.Super_Cards.domain.functional.enums.PackType;
import esgi.al.cleancode.project.Super_Cards.domain.functional.enums.RarityGenerator;
import esgi.al.cleancode.project.Super_Cards.domain.functional.enums.SpecialityGenerator;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Deck;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Hero;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Player;
import esgi.al.cleancode.project.Super_Cards.domain.ports.client.PlayerHeroPackAppenderApi;
import esgi.al.cleancode.project.Super_Cards.domain.ports.server.DeckPersistenceSpi;
import esgi.al.cleancode.project.Super_Cards.domain.ports.server.PlayerPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class PlayerHeroPackAppenderService implements PlayerHeroPackAppenderApi {
    private final PlayerPersistenceSpi playerPersistenceSpi;
    private final PlayerHeroAppenderInDeckService playerHeroAppenderInDeckService;

    @Override
    public Optional<List<UUID>> createAndAppendPack(UUID playerId, String packType) {

        List<UUID> result = new ArrayList<>();

        Optional<Player> player = playerPersistenceSpi.findById(playerId);
        if (player.isEmpty()){
            return Optional.empty();
        }

        Player newPlayer = null;
        if ( !packType.equals(PackType.SILVER.label) && !packType.equals(PackType.DIAMOND.label)){
            return Optional.empty();
        }
        else if (packType.equals(PackType.SILVER.label) && player.get().getNbToken()>=1){
            newPlayer = Player.builder()
                    .playerId(player.get().getPlayerId())
                    .pseudo(player.get().getPseudo())
                    .deckId(player.get().getDeckId())
                    .nbToken(player.get().getNbToken() - 1)
                    .build();
            result.addAll(playerHeroAppenderInDeckService.appendHero(playerId,
                    SpecialityGenerator.generateRandomSpeciality(), RarityGenerator.generateSilverCardRarity()).get());
            result.addAll(playerHeroAppenderInDeckService.appendHero(playerId,
                    SpecialityGenerator.generateRandomSpeciality(), RarityGenerator.generateSilverCardRarity()).get());
            result.addAll(playerHeroAppenderInDeckService.appendHero(playerId,
                    SpecialityGenerator.generateRandomSpeciality(), RarityGenerator.generateSilverCardRarity()).get());
        }
        else{
            newPlayer = Player.builder()
                    .playerId(player.get().getPlayerId())
                    .pseudo(player.get().getPseudo())
                    .deckId(player.get().getDeckId())
                    .nbToken(player.get().getNbToken() - 2)
                    .build();
            result.addAll(playerHeroAppenderInDeckService.appendHero(playerId,
                    SpecialityGenerator.generateRandomSpeciality(), RarityGenerator.generateDiamondCardRarity()).get());
            result.addAll(playerHeroAppenderInDeckService.appendHero(playerId,
                    SpecialityGenerator.generateRandomSpeciality(), RarityGenerator.generateDiamondCardRarity()).get());
            result.addAll(playerHeroAppenderInDeckService.appendHero(playerId,
                    SpecialityGenerator.generateRandomSpeciality(), RarityGenerator.generateDiamondCardRarity()).get());
            result.addAll(playerHeroAppenderInDeckService.appendHero(playerId,
                    SpecialityGenerator.generateRandomSpeciality(), RarityGenerator.generateDiamondCardRarity()).get());
            result.addAll(playerHeroAppenderInDeckService.appendHero(playerId,
                    SpecialityGenerator.generateRandomSpeciality(), RarityGenerator.generateDiamondCardRarity()).get());
        }
        playerPersistenceSpi.save(newPlayer);

        return Optional.of(result);
    }
}
