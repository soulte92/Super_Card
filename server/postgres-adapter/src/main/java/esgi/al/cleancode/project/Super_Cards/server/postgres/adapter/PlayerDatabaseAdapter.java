package esgi.al.cleancode.project.Super_Cards.server.postgres.adapter;

import esgi.al.cleancode.project.Super_Cards.domain.ApplicationError;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Player;
import esgi.al.cleancode.project.Super_Cards.domain.ports.server.PlayerPersistenceSpi;
import esgi.al.cleancode.project.Super_Cards.server.postgres.mapper.DefaultHeroEntityMapper;
import esgi.al.cleancode.project.Super_Cards.server.postgres.mapper.PlayerEntityMapper;
import esgi.al.cleancode.project.Super_Cards.server.postgres.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static esgi.al.cleancode.project.Super_Cards.server.postgres.mapper.PlayerEntityMapper.fromDomain;


@Service
@RequiredArgsConstructor
public class PlayerDatabaseAdapter implements PlayerPersistenceSpi {

    private final PlayerRepository repository;

    @Override
    public Player save(Player o) {
        val entity = fromDomain(o);
        try {
            return PlayerEntityMapper.toDomain(repository.save(entity));
        } catch (Throwable t){
            // TODO to verify
            new ApplicationError("Unable to save Player", null, o, t);
        }
        return o;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Player> findById(UUID id) {
        return repository.findById(id).map(PlayerEntityMapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Player> findByPlayerUsername(String playerUsername) {
        return Optional.ofNullable(PlayerEntityMapper.toDomain(repository.findByPseudo(playerUsername)));
    }

}
