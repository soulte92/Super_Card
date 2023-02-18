package esgi.al.cleancode.project.Super_Cards.client.rest.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@JsonAutoDetect(fieldVisibility = ANY)
public record PlayerHeroPickerFromDefaultHeroDto (
        String speciality,
        String rarity
){
    public PlayerHeroPickerFromDefaultHeroDto(String speciality, String rarity) {
        this.speciality = speciality;
        this.rarity = rarity;
    }
}

