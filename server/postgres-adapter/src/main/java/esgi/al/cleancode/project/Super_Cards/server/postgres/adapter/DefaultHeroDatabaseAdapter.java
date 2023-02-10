package esgi.al.cleancode.project.Super_Cards.server.postgres.adapter;

import esgi.al.cleancode.project.Super_Cards.domain.ApplicationError;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Hero;
import esgi.al.cleancode.project.Super_Cards.domain.ports.server.HeroPersistenceSpi;
import esgi.al.cleancode.project.Super_Cards.server.postgres.mapper.DefaultHeroEntityMapper;
import esgi.al.cleancode.project.Super_Cards.server.postgres.repository.DefaultHeroRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static esgi.al.cleancode.project.Super_Cards.server.postgres.mapper.DefaultHeroEntityMapper.fromDomain;


@Service
@RequiredArgsConstructor
public class DefaultHeroDatabaseAdapter implements HeroPersistenceSpi {

    private final DefaultHeroRepository repository;

    @Override
    @Transactional
    public Hero save(Hero o) {
        val entity = fromDomain(o);
        try {
            return DefaultHeroEntityMapper.toDomain(repository.save(entity));
        } catch (Throwable t){
            // TODO to verify
            new ApplicationError("Unable to save default hero", null, o, t);
            return null;
        }

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Hero> findById(UUID id) {
        return repository.findDefaultHeroEntityById(id).map(DefaultHeroEntityMapper::toDomain);
    }
}
