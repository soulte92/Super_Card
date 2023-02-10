package esgi.al.cleancode.project.Super_Cards.server.postgres.entity;

import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Hero;
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
@Table(name = "default_heroes")
public class DefaultHeroEntity {

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
