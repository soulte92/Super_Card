package esgi.al.cleancode.project.Super_Cards.client.rest.mapper;

import esgi.al.cleancode.project.Super_Cards.client.rest.dto.DefaultHeroDto;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Hero;

public interface DefaultHeroDtoMapper {

  static DefaultHeroDto toDto(Hero hero) {
    return new DefaultHeroDto(hero.getId(),
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
