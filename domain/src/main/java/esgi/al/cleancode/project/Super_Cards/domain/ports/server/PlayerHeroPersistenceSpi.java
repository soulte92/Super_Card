package esgi.al.cleancode.project.Super_Cards.domain.ports.server;

import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Hero;

import java.util.UUID;

public interface PlayerHeroPersistenceSpi extends PersistenceSpi<Hero, UUID> {}
