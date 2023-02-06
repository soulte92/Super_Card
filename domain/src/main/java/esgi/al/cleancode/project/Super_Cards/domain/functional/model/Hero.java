package esgi.al.cleancode.project.Super_Cards.domain.functional.model;

import esgi.al.cleancode.project.Super_Cards.domain.exceptions.HeroException;
import io.vavr.API;
import io.vavr.collection.Seq;
import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;
@Value
@Builder
public class Hero {
    @Builder.Default
    UUID id = UUID.randomUUID();
    public String name;
    public int hp;
    @With
    @Builder.Default
    public int xp = 0;
    public int power;
    public int armor;
    public String speciality;
    public String rarety;
    @With
    @Builder.Default
    public int level = 1;
}

