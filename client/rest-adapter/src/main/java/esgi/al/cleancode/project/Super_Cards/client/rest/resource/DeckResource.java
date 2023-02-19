package esgi.al.cleancode.project.Super_Cards.client.rest.resource;

import esgi.al.cleancode.project.Super_Cards.client.rest.dto.PlayerCreatorDto;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Deck;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Player;
import esgi.al.cleancode.project.Super_Cards.domain.ports.client.DeckCreatorApi;
import esgi.al.cleancode.project.Super_Cards.domain.ports.client.PlayerCreatorApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/deck")
public class DeckResource {

  private final DeckCreatorApi deckCreatorApi ;

  @PostMapping("")
  public ResponseEntity<Object> createAndSavePlayerHeroFromDefaultHero() {

    Optional<Deck> deck = deckCreatorApi.create();
    return deck.<ResponseEntity<Object>>map(value -> ResponseEntity.ok().body(value))
            .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
  }
}
