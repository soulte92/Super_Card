package esgi.al.cleancode.project.Super_Cards.domain.functional.model;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.util.Date;
import java.util.UUID;

@Value
@Builder
public class Round {
    @Builder.Default
    public UUID roundId = UUID.randomUUID();
    public UUID sessionId;
    public UUID firstPlayerId;
    public UUID secondPlayerId;
    public UUID firstPlayerHeroId;
    public UUID secondPlayerHeroId;

    @With
    @Builder.Default
    public String winner = "";
    @With
    @Builder.Default
    public int firstPlayerHeroNbHit = 0;
    @With
    @Builder.Default
    public int secondPlayerHeroNbHit = 0;
    @With
    @Builder.Default
    public Date creationDate = new Date();
}
