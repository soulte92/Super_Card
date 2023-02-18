package esgi.al.cleancode.project.Super_Cards.domain.functional.model;

import lombok.Builder;
import lombok.Builder.Default;
import lombok.Value;
import lombok.With;

import java.util.UUID;
@Value
@Builder
public class Hero {
    @Default
    UUID heroId = UUID.randomUUID();
    public String name;
    public double hp;
    @With
    @Default
    public int xp = 0;
    public double power;
    public double armor;
    public String speciality;
    public String rarity;
    @With
    @Default
    public int level = 1;
}

