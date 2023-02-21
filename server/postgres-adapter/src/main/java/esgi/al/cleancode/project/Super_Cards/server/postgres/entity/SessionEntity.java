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
@Table(name = "session")
public class SessionEntity {

    @Id
    @Include
    public UUID sessionId;

    public Date creationDate;
    @ElementCollection
    @CollectionTable(name = "session_player_ids", joinColumns = @JoinColumn(name = "sessionId"))
    @Column(name = "player_id", columnDefinition = "uuid")
    public List<UUID> playerIds;

}
