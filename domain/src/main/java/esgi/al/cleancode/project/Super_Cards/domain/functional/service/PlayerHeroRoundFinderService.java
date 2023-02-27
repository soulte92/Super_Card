package esgi.al.cleancode.project.Super_Cards.domain.functional.service;

import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Round;
import esgi.al.cleancode.project.Super_Cards.domain.ports.client.PlayerHeroRoundFinderApi;
import esgi.al.cleancode.project.Super_Cards.domain.ports.server.RoundPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class PlayerHeroRoundFinderService implements PlayerHeroRoundFinderApi {
    private final RoundPersistenceSpi roundPersistenceSpi;

    @Override
    public Optional<List<Round>> findPlayerHeroRounds(UUID playerHeroId) {
        return roundPersistenceSpi.findByPlayerHeroId(playerHeroId);
    }
}
