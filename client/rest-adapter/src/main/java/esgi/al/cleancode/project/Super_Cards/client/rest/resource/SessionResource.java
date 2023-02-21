package esgi.al.cleancode.project.Super_Cards.client.rest.resource;

import esgi.al.cleancode.project.Super_Cards.client.rest.dto.SessionCreatorDto;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Deck;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Session;
import esgi.al.cleancode.project.Super_Cards.domain.ports.client.DeckCreatorApi;
import esgi.al.cleancode.project.Super_Cards.domain.ports.client.SessionCreatorApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/session")
public class SessionResource {

  private final SessionCreatorApi sessionCreatorApi ;

  @PostMapping("")
  public ResponseEntity<Object> createSession(
          @RequestBody SessionCreatorDto sessionCreatorDto
          ) {

    Optional<Session> session = sessionCreatorApi.create(sessionCreatorDto.playerIds().stream().map(UUID::fromString).collect(Collectors.toList()));
    return session.<ResponseEntity<Object>>map(value -> ResponseEntity.ok().body(value))
            .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
  }
}
