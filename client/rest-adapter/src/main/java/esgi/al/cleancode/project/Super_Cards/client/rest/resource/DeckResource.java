package esgi.al.cleancode.project.Super_Cards.client.rest.resource;

import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Deck;
import esgi.al.cleancode.project.Super_Cards.domain.ports.client.DeckCreatorApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/deck")
public class DeckResource {

    private final DeckCreatorApi deckCreatorApi;

    @PostMapping("")
    public ResponseEntity<Object> createAndSavePlayerHeroFromDefaultHero() {

        Deck deck = deckCreatorApi.create();
        if (deck != null) {
            return ResponseEntity.ok().body(deck);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
