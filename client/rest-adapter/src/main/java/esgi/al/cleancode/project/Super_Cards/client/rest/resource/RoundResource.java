package esgi.al.cleancode.project.Super_Cards.client.rest.resource;

import esgi.al.cleancode.project.Super_Cards.client.rest.dto.PlayerHeroRoundFinderDto;
import esgi.al.cleancode.project.Super_Cards.client.rest.dto.RoundCreatorDto;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Round;
import esgi.al.cleancode.project.Super_Cards.domain.ports.client.PlayerHeroRoundFinderApi;
import esgi.al.cleancode.project.Super_Cards.domain.ports.client.RoundCreatorApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/round")
public class RoundResource {

    private final RoundCreatorApi roundCreatorApi;
    private final PlayerHeroRoundFinderApi playerHeroRoundFinderApi;

    @PostMapping("")
    public ResponseEntity<Object> createRound(
            @RequestBody RoundCreatorDto roundCreatorDto
    ) {

        Optional<Round> round = roundCreatorApi.create(UUID.fromString(roundCreatorDto.sessionId()),
                UUID.fromString(roundCreatorDto.firstPlayerId()),
                UUID.fromString(roundCreatorDto.secondPlayerId()),
                UUID.fromString(roundCreatorDto.firstPlayerHeroId()),
                UUID.fromString(roundCreatorDto.secondPlayerHeroId()));
        return round.<ResponseEntity<Object>>map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @GetMapping("findHeroRounds")
    public ResponseEntity<Object> createRound(
            @RequestBody PlayerHeroRoundFinderDto dto
    ) {

        Optional<List<Round>> rounds = playerHeroRoundFinderApi.findPlayerHeroRounds(UUID.fromString(dto.playerHeroId()));
        return rounds.<ResponseEntity<Object>>map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }
}
