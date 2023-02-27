package esgi.al.cleancode.project.Super_Cards.domain.functional.service;

import esgi.al.cleancode.project.Super_Cards.domain.exceptions.RoundException;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Round;
import esgi.al.cleancode.project.Super_Cards.domain.ports.client.RoundCreatorApi;
import esgi.al.cleancode.project.Super_Cards.domain.ports.server.RoundPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class RoundCreatorService implements RoundCreatorApi {

    private final RoundPersistenceSpi roundPersistenceSpi;

    @Override
    public Round create(UUID sessionId, UUID firstPlayerId, UUID secondPlayerId, UUID firstPlayerHeroId, UUID secondPlayerHeroId) {
        Round round = Round.builder()
                .sessionId(sessionId)
                .firstPlayerId(firstPlayerId)
                .secondPlayerId(secondPlayerId)
                .firstPlayerHeroId(firstPlayerHeroId)
                .secondPlayerHeroId(secondPlayerHeroId)
                .build();
        round = roundPersistenceSpi.save(round);
        if (round == null){
            throw new RoundException("Round saving error");
        }
        return round;
    }
}
