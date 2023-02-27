package esgi.al.cleancode.project.Super_Cards.client.rest.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@JsonAutoDetect(fieldVisibility = ANY)
public record SessionCreatorDto(
        @JsonProperty("playerIds") ArrayList<String> playerIds
) {
}
