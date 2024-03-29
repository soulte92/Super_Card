package esgi.al.cleancode.project.Super_Cards.client.rest.resource;

import esgi.al.cleancode.project.Super_Cards.client.rest.dto.RoundCreatorDto;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Round;
import esgi.al.cleancode.project.Super_Cards.domain.ports.client.BattleApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/battle")
public class BattleResource {

    private final BattleApi battleApi;

    @PostMapping("/attack")
    public ResponseEntity<Object> createBattle(
            @RequestBody RoundCreatorDto roundCreatorDto
    ) {
        Round round = battleApi.attack(UUID.fromString(roundCreatorDto.sessionId()),
                UUID.fromString(roundCreatorDto.firstPlayerId()),
                UUID.fromString(roundCreatorDto.secondPlayerId()),
                UUID.fromString(roundCreatorDto.firstPlayerHeroId()),
                UUID.fromString(roundCreatorDto.secondPlayerHeroId()));
        if (round != null) {
            return ResponseEntity.ok().body(round);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
