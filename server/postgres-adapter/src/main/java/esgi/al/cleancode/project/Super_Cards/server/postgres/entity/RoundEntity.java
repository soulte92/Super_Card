package esgi.al.cleancode.project.Super_Cards.server.postgres.entity;

import lombok.*;
import lombok.EqualsAndHashCode.Include;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "round")
public class RoundEntity {

    @Id
    @Include
    public UUID roundId = UUID.randomUUID();
    public UUID sessionId;
    public UUID firstPlayerId;
    public UUID secondPlayerId;
    public UUID firstPlayerHeroId;
    public UUID secondPlayerHeroId;
    public String winner;
    public int firstPlayerHeroNbHit;
    public int secondPlayerHeroNbHit;
    public Date creationDate;
}
