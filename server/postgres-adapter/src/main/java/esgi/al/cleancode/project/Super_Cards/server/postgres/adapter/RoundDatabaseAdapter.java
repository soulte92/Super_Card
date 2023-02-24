package esgi.al.cleancode.project.Super_Cards.server.postgres.adapter;

import esgi.al.cleancode.project.Super_Cards.domain.ApplicationError;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Round;
import esgi.al.cleancode.project.Super_Cards.domain.ports.server.RoundPersistenceSpi;
import esgi.al.cleancode.project.Super_Cards.server.postgres.mapper.RoundEntityMapper;
import esgi.al.cleancode.project.Super_Cards.server.postgres.repository.RoundRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static esgi.al.cleancode.project.Super_Cards.server.postgres.mapper.RoundEntityMapper.fromDomain;


@Service
@RequiredArgsConstructor
public class RoundDatabaseAdapter implements RoundPersistenceSpi {

    private final RoundRepository repository;

    @Override
    public Round save(Round o) {
        val entity = fromDomain(o);
        try {
            return RoundEntityMapper.toDomain(repository.save(entity));
        } catch (Throwable t) {
            // TODO to verify
            new ApplicationError("Unable to save Round", null, o, t);
        }
        return o;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Round> findById(UUID id) {
        return repository.findById(id).map(RoundEntityMapper::toDomain);
    }

    @Override
    public Optional<List<Round>> findByPlayerHeroId(UUID playerHeroId) {
        return Optional.of(repository.findByFirstPlayerHeroIdOrSecondPlayerHeroId(playerHeroId).stream().map(RoundEntityMapper::toDomain).toList());
    }
}
