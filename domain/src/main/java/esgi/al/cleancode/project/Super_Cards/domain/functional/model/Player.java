package esgi.al.cleancode.project.Super_Cards.domain.functional.model;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.util.UUID;

@Value
@Builder
public class Player {
    @Builder.Default
    public UUID playerId = UUID.randomUUID();
    public UUID deckId;
    public String pseudo;
    @With
    @Builder.Default
    public int nbToken = 4;
}
