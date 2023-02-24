package esgi.al.cleancode.project.Super_Cards.server.postgres.mapper;

import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Hero;
import esgi.al.cleancode.project.Super_Cards.server.postgres.entity.PlayerHeroEntity;

public interface PlayerHeroEntityMapper {

    static Hero toDomain(PlayerHeroEntity entity) {
        return Hero.builder().heroId(entity.getHeroId())
                .name(entity.getName())
                .xp(entity.getXp())
                .level(entity.getLevel())
                .speciality(entity.getSpeciality())
                .rarity(entity.getRarity())
                .hp(entity.getHp())
                .power(entity.getPower())
                .armor(entity.getArmor()).build();
    }

    static PlayerHeroEntity fromDomain(Hero domain) {
        return PlayerHeroEntity.builder().heroId(domain.getHeroId())
                .name(domain.getName())
                .xp(domain.getXp())
                .level(domain.getLevel())
                .speciality(domain.getSpeciality())
                .rarity(domain.getRarity())
                .hp(domain.getHp())
                .power(domain.getPower())
                .armor(domain.getArmor()).build();
    }
}

