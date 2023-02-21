package esgi.al.cleancode.project.Super_Cards.domain.functional.service;

import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Round;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Session;
import esgi.al.cleancode.project.Super_Cards.domain.ports.client.RoundCreatorApi;
import esgi.al.cleancode.project.Super_Cards.domain.ports.client.SessionCreatorApi;
import esgi.al.cleancode.project.Super_Cards.domain.ports.server.RoundPersistenceSpi;
import esgi.al.cleancode.project.Super_Cards.domain.ports.server.SessionPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class RoundCreatorService implements RoundCreatorApi {

    private final RoundPersistenceSpi roundPersistenceSpi;

    @Override
    public Optional<Round> create(UUID sessionId, UUID firstPlayerId, UUID secondPlayerId, UUID firstPlayerHeroId, UUID secondPlayerHeroId) {
        Round round = Round.builder()
                .sessionId(sessionId)
                .firstPlayerId(firstPlayerId)
                .secondPlayerId(secondPlayerId)
                .firstPlayerHeroId(firstPlayerHeroId)
                .secondPlayerHeroId(secondPlayerHeroId)
                .build();
        return Optional.of(roundPersistenceSpi.save(round));
    }
}