package esgi.al.cleancode.project.Super_Cards.client.rest.resource;

import esgi.al.cleancode.project.Super_Cards.client.rest.mapper.DefaultHeroDtoMapper;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Hero;
import esgi.al.cleancode.project.Super_Cards.domain.ports.client.DefaultHeroesPopulationApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/default_hero")
public class DefaultHeroResource {

    private final DefaultHeroesPopulationApi defaultHeroesPopulationApi;

    @PostMapping("/populate_heroes")
    public ResponseEntity<Object> createHero() {
        Optional<List<Hero>> defaultCreatedHeroes = defaultHeroesPopulationApi.createAndSaveDefaultHeroes();
        return defaultCreatedHeroes.<ResponseEntity<Object>>map(heroes -> ResponseEntity.ok().body(heroes.stream()
                .map(DefaultHeroDtoMapper::toDto).toList())).orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @PostMapping("/populate_one-hero")
    public ResponseEntity<Object> createOneHero() {
        // TODO maybe to remove
        Optional<Hero> hero = defaultHeroesPopulationApi.createAndSaveOneHero();
        return hero.<ResponseEntity<Object>>map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @GetMapping("")
    public ResponseEntity<Object> getDefaultHeroes() {
        Optional<List<Hero>> defaultHeroes = defaultHeroesPopulationApi.getDefaultHeroes();
        return defaultHeroes.<ResponseEntity<Object>>map(heroes -> ResponseEntity.ok().body(heroes.stream()
                        .map(DefaultHeroDtoMapper::toDto).toList()))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }
}
