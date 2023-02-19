package esgi.al.cleancode.project.Super_Cards.server.postgres.adapter;

import esgi.al.cleancode.project.Super_Cards.domain.ApplicationError;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Hero;
import esgi.al.cleancode.project.Super_Cards.domain.ports.server.DefaultHeroPersistenceSpi;
import esgi.al.cleancode.project.Super_Cards.server.postgres.entity.DefaultHeroEntity;
import esgi.al.cleancode.project.Super_Cards.server.postgres.mapper.DefaultHeroEntityMapper;
import esgi.al.cleancode.project.Super_Cards.server.postgres.repository.DefaultHeroRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static esgi.al.cleancode.project.Super_Cards.server.postgres.mapper.DefaultHeroEntityMapper.fromDomain;


@Service
@RequiredArgsConstructor
public class DefaultHeroDatabaseAdapter implements DefaultHeroPersistenceSpi {

    private final DefaultHeroRepository repository;

    @Override
    @Transactional
    public Hero save(Hero o) {
        val entity = fromDomain(o);
        try {
            return DefaultHeroEntityMapper.toDomain(repository.save(entity));
        } catch (Throwable t){
            // TODO to verify
            new ApplicationError("Unable to save hero", null, o, t);
        }
        return o;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Hero> findById(UUID id) {
        return repository.findDefaultHeroEntityByHeroId(id).map(DefaultHeroEntityMapper::toDomain);
    }

    @Override
    public Optional<Hero> findBySpecialityAndRarity(String speciality, String rarity) {
        DefaultHeroEntity defaultHeroEntity = repository.findBySpecialityAndRarity(speciality, rarity);
        return Optional.ofNullable(DefaultHeroEntityMapper.toDomain(defaultHeroEntity));
    }

    @Override
    public List<Hero> findAll() {
        return repository.findAll().stream().map(DefaultHeroEntityMapper::toDomain).toList();
    }
}
