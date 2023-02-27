package esgi.al.cleancode.project.Super_Cards.server.postgres.entity;

import lombok.*;
import lombok.EqualsAndHashCode.Include;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "deck")
public class DeckEntity {

    @Id
    @Include
    private UUID deckId;
    @ElementCollection
    @CollectionTable(name = "deck_hero_ids", joinColumns = @JoinColumn(name = "deck_id"))
    @Column(name = "hero_id", columnDefinition = "uuid")
    private List<UUID> heroIds;
}
