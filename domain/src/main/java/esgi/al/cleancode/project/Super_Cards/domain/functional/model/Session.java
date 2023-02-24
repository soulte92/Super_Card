package esgi.al.cleancode.project.Super_Cards.domain.functional.model;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Value
@Builder
public class Session {
    @Builder.Default
    public UUID sessionId = UUID.randomUUID();
    @With
    @Builder.Default
    public Date creationDate = new Date();
    public List<UUID> playerIds;
}
