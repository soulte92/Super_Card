package esgi.al.cleancode.project.Super_Cards.client.rest.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@JsonAutoDetect(fieldVisibility = ANY)
public record RoundCreatorDto(
        @JsonProperty("sessionId") String sessionId,
        @JsonProperty("firstPlayerId") String firstPlayerId,
        @JsonProperty("secondPlayerId") String secondPlayerId,
        @JsonProperty("firstPlayerHeroId") String firstPlayerHeroId,
        @JsonProperty("secondPlayerHeroId") String secondPlayerHeroId
) {}
