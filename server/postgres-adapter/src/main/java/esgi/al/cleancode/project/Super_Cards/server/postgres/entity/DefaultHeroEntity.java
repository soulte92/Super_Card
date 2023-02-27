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
@Table(name = "default_hero")
public class DefaultHeroEntity {

    @Id
    @Include
    private UUID heroId;

    private String name;

    private double hp;

    private int xp;

    private double power;

    private double armor;

    private String speciality;

    private String rarity;

    private int level;
}
