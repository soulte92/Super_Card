package esgi.al.cleancode.project.Super_Cards.client.rest.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@JsonAutoDetect(fieldVisibility = ANY)
public record PlayerHeroPackAppenderDto(
        @JsonProperty("playerId") String playerId,
        @JsonProperty("packType") String packType
) { }
