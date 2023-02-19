package esgi.al.cleancode.project.Super_Cards.client.rest.resource;

import esgi.al.cleancode.project.Super_Cards.client.rest.dto.PlayerCreatorDto;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Player;
import esgi.al.cleancode.project.Super_Cards.domain.ports.client.PlayerCreatorApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/player")
public class PlayerResource {

  private final PlayerCreatorApi playerCreatorApi ;

  @PostMapping("")
  public ResponseEntity<Object> createAndSavePlayerHeroFromDefaultHero(
         @RequestBody PlayerCreatorDto dto) {

    Optional<Player> player = playerCreatorApi.create(dto.pseudo());
    return player.<ResponseEntity<Object>>map(value -> ResponseEntity.ok().body(value))
            .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
  }
}
