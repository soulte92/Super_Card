package esgi.al.cleancode.project.Super_Cards.server.postgres.repository;

import esgi.al.cleancode.project.Super_Cards.server.postgres.entity.PlayerHeroEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;

@Repository
@Transactional(propagation = MANDATORY)
public interface PlayerHeroRepository extends JpaRepository<PlayerHeroEntity, UUID> {
    @Transactional(propagation = REQUIRED)
    @Query("select p from PlayerHeroEntity p where p.hp >= ?1")
    List<PlayerHeroEntity> findByHpGreaterThanEqual(double hp);

    @EntityGraph(attributePaths = "player_hero")
    Optional<PlayerHeroEntity> findPlayerHeroEntityByHeroId(UUID id);
}