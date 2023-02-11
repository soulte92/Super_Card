package esgi.al.cleancode.project.Super_Cards.client.rest.resource;

import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Hero;
import esgi.al.cleancode.project.Super_Cards.domain.ports.client.DefaultHeroesPopulationApi;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/default_hero")
public class DefaultHeroResource {

  private final DefaultHeroesPopulationApi defaultHeroesPopulationApi;

  @PostMapping("/populate_heroes")
  public ResponseEntity<Object> createHero() {
    try {
      List<Hero> createdHeroes = defaultHeroesPopulationApi.createAndSaveHeroes();
      return ResponseEntity.ok().body(createdHeroes);
    } catch (Exception ex) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
  }
}
