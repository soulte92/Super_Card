package esgi.al.cleancode.project.Super_Cards.server.postgres.adapter;

import esgi.al.cleancode.project.Super_Cards.domain.ApplicationError;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Session;
import esgi.al.cleancode.project.Super_Cards.domain.ports.server.SessionPersistenceSpi;
import esgi.al.cleancode.project.Super_Cards.server.postgres.mapper.SessionEntityMapper;
import esgi.al.cleancode.project.Super_Cards.server.postgres.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static esgi.al.cleancode.project.Super_Cards.server.postgres.mapper.SessionEntityMapper.fromDomain;


@Service
@RequiredArgsConstructor
public class SessionDatabaseAdapter implements SessionPersistenceSpi {

    private final SessionRepository repository;

    @Override
    public Session save(Session o) {
        val entity = fromDomain(o);
        try {
            return SessionEntityMapper.toDomain(repository.save(entity));
        } catch (Throwable t) {
            // TODO to verify
            new ApplicationError("Unable to save Session", null, o, t);
        }
        return o;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Session> findById(UUID id) {
        return repository.findById(id).map(SessionEntityMapper::toDomain);
    }
}
