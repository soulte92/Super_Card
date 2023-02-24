package esgi.al.cleancode.project.Super_Cards.client.rest.resource;

import esgi.al.cleancode.project.Super_Cards.client.rest.dto.PlayerHeroDto;
import esgi.al.cleancode.project.Super_Cards.client.rest.mapper.PlayerHeroDtoMapper;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Hero;
import esgi.al.cleancode.project.Super_Cards.domain.ports.client.PlayerHeroCreatorApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/player_hero")
public class PlayerHeroResource {

    private final PlayerHeroCreatorApi playerHeroCreatorApi;

    @PostMapping("/speciality/{speciality}/rarity/{rarity}/add_hero")
    public ResponseEntity<Object> createAndSavePlayerHeroFromDefaultHero(
            @PathVariable("speciality") String speciality,
            @PathVariable("rarity") String rarity) {
        try {
            //TODO maybe to remove
            PlayerHeroDto heroDto = PlayerHeroDtoMapper.toDto(playerHeroCreatorApi.pickHeroFromDefaultHero(speciality, rarity));
            return ResponseEntity.ok().body(heroDto);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/alive_heroes")
    public ResponseEntity<Object> getAliveHeroes() {
        Optional<List<Hero>> heroList = playerHeroCreatorApi.getAliveHeroes();
        return heroList.<ResponseEntity<Object>>map(heroes -> ResponseEntity.ok().body(heroes.stream()
                        .map(PlayerHeroDtoMapper::toDto).toList()))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }
}
