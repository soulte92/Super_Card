package esgi.al.cleancode.project.Super_Cards.server.postgres.repository;

import esgi.al.cleancode.project.Super_Cards.server.postgres.entity.DefaultHeroEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Repository
@Transactional(propagation = MANDATORY)
public interface DefaultHeroRepository extends JpaRepository<DefaultHeroEntity, UUID> {
    @Query("select d from DefaultHeroEntity d where d.speciality = ?1 and d.rarity = ?2")
    DefaultHeroEntity findBySpecialityAndRarity(String speciality, String rarity);

    @EntityGraph(attributePaths = "default_hero")
    Optional<DefaultHeroEntity> findDefaultHeroEntityByHeroId(UUID id);
}