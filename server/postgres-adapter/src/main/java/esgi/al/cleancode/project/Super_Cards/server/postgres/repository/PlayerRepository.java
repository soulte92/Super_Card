package esgi.al.cleancode.project.Super_Cards.server.postgres.repository;

import esgi.al.cleancode.project.Super_Cards.server.postgres.entity.DefaultHeroEntity;
import esgi.al.cleancode.project.Super_Cards.server.postgres.entity.PlayerEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Repository
@Transactional(propagation = MANDATORY)
public interface PlayerRepository extends JpaRepository<PlayerEntity, UUID> {
    @Query("select p from PlayerEntity p where p.pseudo = ?1")
    PlayerEntity findByPseudo(String pseudo);
}