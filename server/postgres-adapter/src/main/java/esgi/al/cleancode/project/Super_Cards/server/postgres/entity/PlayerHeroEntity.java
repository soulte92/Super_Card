package esgi.al.cleancode.project.Super_Cards.server.postgres.entity;

import lombok.*;

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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "player_hero")
public class PlayerHeroEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    private String name;

    private double hp;

    private int xp;

    private double power;

    private double armor;

    private String speciality;

    private String rarity;

    private int level;
}
