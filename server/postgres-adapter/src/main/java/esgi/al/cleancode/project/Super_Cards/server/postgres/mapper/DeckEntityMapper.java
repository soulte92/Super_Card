package esgi.al.cleancode.project.Super_Cards.server.postgres.mapper;

import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Deck;
import esgi.al.cleancode.project.Super_Cards.server.postgres.entity.DeckEntity;

public interface DeckEntityMapper {

    static Deck toDomain(DeckEntity entity) {
        return Deck.builder()
                .deckId(entity.getDeckId())
                .heroIds(entity.getHeroIds())
                .build();
    }

    static DeckEntity fromDomain(Deck domain) {
        return DeckEntity.builder()
                .deckId(domain.getDeckId())
                .heroIds(domain.getHeroIds())
                .build();
    }
}

