package esgi.al.cleancode.project.Super_Cards.server.postgres.entity;

import lombok.*;
import lombok.EqualsAndHashCode.Include;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "player")
public class PlayerEntity {

    @Id
    @Include
    private UUID playerId;
    private UUID deckId;
    private String pseudo;
    private int nbToken;
}
