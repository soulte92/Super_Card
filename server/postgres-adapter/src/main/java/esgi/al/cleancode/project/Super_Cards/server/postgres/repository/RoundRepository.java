package esgi.al.cleancode.project.Super_Cards.server.postgres.repository;

import esgi.al.cleancode.project.Super_Cards.server.postgres.entity.RoundEntity;
import esgi.al.cleancode.project.Super_Cards.server.postgres.entity.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;

@Repository
@Transactional(propagation = MANDATORY)
public interface RoundRepository extends JpaRepository<RoundEntity, UUID> {
    @Transactional(propagation = REQUIRED)
    @Query("select r from RoundEntity r where r.firstPlayerHeroId = ?1 or r.secondPlayerHeroId = ?1")
    List<RoundEntity> findByFirstPlayerHeroIdOrSecondPlayerHeroId(UUID playerHeroId);
}