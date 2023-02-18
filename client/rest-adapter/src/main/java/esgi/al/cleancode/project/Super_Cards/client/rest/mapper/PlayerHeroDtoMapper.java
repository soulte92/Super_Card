package esgi.al.cleancode.project.Super_Cards.client.rest.mapper;

import esgi.al.cleancode.project.Super_Cards.client.rest.dto.DefaultHeroDto;
import esgi.al.cleancode.project.Super_Cards.client.rest.dto.PlayerHeroDto;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Hero;

public interface PlayerHeroDtoMapper {

  static PlayerHeroDto toDto(Hero hero) {
    return new PlayerHeroDto(hero.getHeroId(),
            hero.getName(),
            hero.getHp(),
            hero.getXp(),
            hero.getPower(),
            hero.getArmor(),
            hero.getSpeciality(),
            hero.getRarity(),
            hero.getLevel());
  }
}
