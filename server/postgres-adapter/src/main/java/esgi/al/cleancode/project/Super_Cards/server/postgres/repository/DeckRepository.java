package esgi.al.cleancode.project.Super_Cards.server.postgres.repository;

import esgi.al.cleancode.project.Super_Cards.server.postgres.entity.DeckEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Repository
@Transactional(propagation = MANDATORY)
public interface DeckRepository extends JpaRepository<DeckEntity, UUID> {
}