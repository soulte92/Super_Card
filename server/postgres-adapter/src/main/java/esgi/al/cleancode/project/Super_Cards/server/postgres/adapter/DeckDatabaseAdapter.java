package esgi.al.cleancode.project.Super_Cards.server.postgres.adapter;

import esgi.al.cleancode.project.Super_Cards.domain.ApplicationError;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Deck;
import esgi.al.cleancode.project.Super_Cards.domain.ports.server.DeckPersistenceSpi;
import esgi.al.cleancode.project.Super_Cards.server.postgres.mapper.DeckEntityMapper;
import esgi.al.cleancode.project.Super_Cards.server.postgres.repository.DeckRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static esgi.al.cleancode.project.Super_Cards.server.postgres.mapper.DeckEntityMapper.fromDomain;


@Service
@RequiredArgsConstructor
public class DeckDatabaseAdapter implements DeckPersistenceSpi {

    private final DeckRepository repository;

    @Override
    public Deck save(Deck o) {
        val entity = fromDomain(o);
        try {
            return DeckEntityMapper.toDomain(repository.save(entity));
        } catch (Throwable t) {
            // TODO to verify
            new ApplicationError("Unable to save Deck", null, o, t);
        }
        return o;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Deck> findById(UUID id) {
        return repository.findById(id).map(DeckEntityMapper::toDomain);
    }
}
