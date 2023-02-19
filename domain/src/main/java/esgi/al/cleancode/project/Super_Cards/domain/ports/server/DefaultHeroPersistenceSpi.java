package esgi.al.cleancode.project.Super_Cards.domain.ports.server;

import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Hero;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DefaultHeroPersistenceSpi extends PersistenceSpi<Hero, UUID> {
    @Override
    Optional<Hero> findById(UUID uuid);

    Optional<Hero> findBySpecialityAndRarity(String speciality, String rarity);

    List<Hero> findAll();
}
