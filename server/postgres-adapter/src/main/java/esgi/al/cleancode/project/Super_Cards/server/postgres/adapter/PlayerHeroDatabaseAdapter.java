package esgi.al.cleancode.project.Super_Cards.server.postgres.adapter;

import esgi.al.cleancode.project.Super_Cards.domain.ApplicationError;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Hero;
import esgi.al.cleancode.project.Super_Cards.domain.ports.server.HeroPersistenceSpi;
import esgi.al.cleancode.project.Super_Cards.server.postgres.mapper.PlayerHeroEntityMapper;
import esgi.al.cleancode.project.Super_Cards.server.postgres.repository.PlayerHeroRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static esgi.al.cleancode.project.Super_Cards.server.postgres.mapper.PlayerHeroEntityMapper.fromDomain;


@Service
@RequiredArgsConstructor
public class PlayerHeroDatabaseAdapter implements HeroPersistenceSpi {

    private final PlayerHeroRepository repository;

    @Override
    @Transactional
    public Hero save(Hero o) {
        val entity = fromDomain(o);
        try {
            return PlayerHeroEntityMapper.toDomain(repository.save(entity));
        } catch (Throwable t){
            // TODO to verify
            new ApplicationError("Unable to save player hero", null, o, t);
            return null;
        }

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Hero> findById(UUID id) {
        return repository.findPlayerHeroEntityById(id).map(PlayerHeroEntityMapper::toDomain);
    }
}
