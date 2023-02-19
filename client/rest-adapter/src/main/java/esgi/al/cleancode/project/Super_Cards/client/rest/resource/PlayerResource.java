package esgi.al.cleancode.project.Super_Cards.client.rest.resource;

import esgi.al.cleancode.project.Super_Cards.client.rest.dto.PlayerCreatorDto;
import esgi.al.cleancode.project.Super_Cards.client.rest.dto.PlayerHeroAppenderInDeckDto;
import esgi.al.cleancode.project.Super_Cards.client.rest.dto.PlayerHeroPackAppenderDto;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Player;
import esgi.al.cleancode.project.Super_Cards.domain.ports.client.PlayerCreatorApi;
import esgi.al.cleancode.project.Super_Cards.domain.ports.client.PlayerHeroAppenderInDeckApi;
import esgi.al.cleancode.project.Super_Cards.domain.ports.client.PlayerHeroPackAppenderApi;
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

  private final PlayerCreatorApi playerCreatorApi ;
  private final PlayerHeroAppenderInDeckApi playerHeroAppenderInDeckApi;
  private final PlayerHeroPackAppenderApi playerHeroPackAppenderApi;

  @PostMapping("")
  public ResponseEntity<Object> createAndSavePlayerHeroFromDefaultHero(
         @RequestBody PlayerCreatorDto dto) {

    Optional<Player> player = playerCreatorApi.create(dto.pseudo());
    return player.<ResponseEntity<Object>>map(value -> ResponseEntity.ok().body(value))
            .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
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
    Optional<List<UUID>> heroUuids = playerHeroPackAppenderApi.createAndAppendPack(UUID.fromString(dto.playerId()), dto.packType());
    return heroUuids.<ResponseEntity<Object>>map(value -> ResponseEntity.ok().body(value))
            .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
  }
}
