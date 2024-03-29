package esgi.al.cleancode.project.Super_Cards.client.rest.resource;

import esgi.al.cleancode.project.Super_Cards.client.rest.dto.PlayerCreatorDto;
import esgi.al.cleancode.project.Super_Cards.client.rest.dto.PlayerDeckDisplayerDto;
import esgi.al.cleancode.project.Super_Cards.client.rest.dto.PlayerHeroAppenderInDeckDto;
import esgi.al.cleancode.project.Super_Cards.client.rest.dto.PlayerHeroPackAppenderDto;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Hero;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Player;
import esgi.al.cleancode.project.Super_Cards.domain.ports.client.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/player")
public class PlayerResource {

    private final PlayerCreatorApi playerCreatorApi;
    private final PlayerHeroAppenderInDeckApi playerHeroAppenderInDeckApi;
    private final PlayerHeroPackAppenderApi playerHeroPackAppenderApi;
    private final PlayerDeckDisplayerApi playerDeckDisplayerApi;
    private final PlayerFinderApi playerFinderApi;

    @PostMapping("")
    public ResponseEntity<Object> createAndSavePlayer(
            @RequestBody PlayerCreatorDto dto) {

        Player player = playerCreatorApi.create(dto.pseudo());
        if (player != null) {
            return ResponseEntity.ok().body(player);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/appendHero")
    public ResponseEntity<Object> appendPlayerHero(
            @RequestBody PlayerHeroAppenderInDeckDto dto) {
        Optional<List<UUID>> heroUuids = playerHeroAppenderInDeckApi.appendHero(UUID.fromString(dto.playerId()),
                dto.speciality(),
                dto.rarity());
        return heroUuids.<ResponseEntity<Object>>map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @PostMapping("/appendHeroPack")
    public ResponseEntity<Object> appendPlayerHeroPack(
            @RequestBody PlayerHeroPackAppenderDto dto) {
        Optional<List<Hero>> heroUuids = playerHeroPackAppenderApi.createAndAppendPack(UUID.fromString(dto.playerId()), dto.packType());
        return heroUuids.<ResponseEntity<Object>>map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @GetMapping("/displayDeck")
    public ResponseEntity<Object> displayDeck(
            @RequestBody PlayerDeckDisplayerDto dto) {
        Optional<List<Hero>> heroes = playerDeckDisplayerApi.displayDeckContent(UUID.fromString(dto.playerId()));
        return heroes.<ResponseEntity<Object>>map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @GetMapping("/findPlayer")
    public ResponseEntity<Object> findPlayer(
            @RequestBody PlayerDeckDisplayerDto dto) {
        Optional<Player> player = playerFinderApi.findPlayer(UUID.fromString(dto.playerId()));
        return player.<ResponseEntity<Object>>map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @GetMapping("/findPlayerByUsername")
    public ResponseEntity<Object> findPlayerByUsername(
            @RequestBody PlayerCreatorDto dto) {
        Optional<Player> player = playerFinderApi.findByPlayerUsername(dto.pseudo());
        return player.<ResponseEntity<Object>>map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }
}
