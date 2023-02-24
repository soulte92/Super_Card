package esgi.al.cleancode.project.Super_Cards.client.rest.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.vavr.collection.Seq;
import lombok.Builder;
import lombok.With;

import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@JsonAutoDetect(fieldVisibility = ANY)
public record DefaultHeroDto(
    UUID heroId,
    String name,
    double hp,
    int xp,
    double power,
    double armor,
    String speciality,
    String rarity,
    int level
) {}
