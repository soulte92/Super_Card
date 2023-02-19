package esgi.al.cleancode.project.Super_Cards.domain.functional.model;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Value
@Builder
public class Deck {
    @Builder.Default
    public UUID deckId = UUID.randomUUID();
    @With
    @Builder.Default
    public List<UUID> heroIds;
}
