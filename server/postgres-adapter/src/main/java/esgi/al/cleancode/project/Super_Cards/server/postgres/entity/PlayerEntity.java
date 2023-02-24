package esgi.al.cleancode.project.Super_Cards.server.postgres.entity;

import lombok.*;
import lombok.EqualsAndHashCode.Include;

import javax.persistence.*;
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
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "deck_id", referencedColumnName = "player_deck_id")
//    private DeckEntity deckEntity;
    private UUID deckId;
    private String pseudo;
    private int nbToken;
}
