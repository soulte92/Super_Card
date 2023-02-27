package esgi.al.cleancode.project.Super_Cards.domain.functional.service;

import esgi.al.cleancode.project.Super_Cards.domain.functional.enums.Rarity;
import esgi.al.cleancode.project.Super_Cards.domain.functional.enums.Speciality;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Hero;
import esgi.al.cleancode.project.Super_Cards.domain.ports.server.PlayerHeroPersistenceSpi;
import esgi.al.cleancode.project.Super_Cards.domain.ports.server.RoundPersistenceSpi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;

public class BattleServiceTest {
    @InjectMocks
    private BattleService battleService;
    @Mock
    private PlayerHeroPersistenceSpi playerHeroPersistenceSpi;
    @Mock
    private RoundPersistenceSpi roundPersistenceSpi;

    @Test
    void should_attack() {
    }
    @Test
    void should_not_attack_with_dead_hero_defender() {
    }

    @Test
    void attackHeroesEachOtherInRound() {
    }
    @Test
    void not_attackHeroesEachOtherInRound() {

    }

    @Test
    void attackHeroesEachOther() {
    }

    @Test
    void should_not_attackHeroesEachOther_with_dead_hero_defender() {
        Hero heroFighter = Hero.builder()
                .name("super-boy")
                .hp(30)
                .xp(100)
                .power(20)
                .armor(5)
                .level(6)
                .speciality(Speciality.TANK.label)
                .rarity(Rarity.COMMON.label).build();
        Hero heroDefender = Hero.builder()
                .name("super-duck")
                .hp(0)
                .xp(100)
                .power(20)
                .armor(5)
                .level(6)
                .speciality(Speciality.TANK.label)
                .rarity(Rarity.COMMON.label).build();

//        ArrayList<Hero> givenHeroes = new ArrayList<>();
//        givenHeroes.add(heroFighter);
//        givenHeroes.add(heroDefender);

        ArrayList<Hero> actualHeroes = battleService.attackHeroesEachOther(heroFighter, heroDefender);

        Assertions.assertEquals(heroDefender, actualHeroes.get(1));
        Assertions.assertEquals(heroFighter, actualHeroes.get(0));



    }

}
