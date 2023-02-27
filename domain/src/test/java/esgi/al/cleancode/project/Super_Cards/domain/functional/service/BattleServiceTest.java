package esgi.al.cleancode.project.Super_Cards.domain.functional.service;

import esgi.al.cleancode.project.Super_Cards.domain.functional.enums.Rarity;
import esgi.al.cleancode.project.Super_Cards.domain.functional.enums.Speciality;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Hero;
import esgi.al.cleancode.project.Super_Cards.domain.ports.server.PlayerHeroPersistenceSpi;
import esgi.al.cleancode.project.Super_Cards.domain.ports.server.RoundPersistenceSpi;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.HashMap;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BattleServiceTest {
    @InjectMocks
    private BattleService battleService;
    @Mock
    private PlayerHeroPersistenceSpi playerHeroPersistenceSpi;
    @Mock
    private RoundPersistenceSpi roundPersistenceSpi;
    @Mock
    private HeroUtils heroUtils;

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
    void should_attack_heroes_each_other() {
        val givenHeroFighter = Hero.builder()
                .hp(15)
                .xp(39)
                .level(7)
                .speciality(Speciality.TANK.label)
                .build();
        val givenHeroDefender = Hero.builder()
                .hp(40)
                .armor(0)
                .speciality(Speciality.TANK.label)
                .build();

//        when(battleService.getPowerAdvantage(givenHeroFighter.speciality, givenHeroDefender.speciality)).thenReturn(0);

        val actualHeroes = battleService.attackHeroesEachOther(givenHeroFighter, givenHeroDefender);
        val givenHeroFighterResult = Hero.builder()
                .hp(15)
                .xp(40)
                .level(8)
                .build();
        val givenHeroDefenderResult = Hero.builder()
                .hp(25)
                .build();

        Assertions.assertEquals(actualHeroes.size(), 2);

        // check hero defender hp decreased
        Assertions.assertEquals(givenHeroDefenderResult.getHp(), actualHeroes.get(1).hp);

        // check hero fighter xp increased
        Assertions.assertEquals(givenHeroFighterResult.getXp(), actualHeroes.get(0).xp);
        Assertions.assertEquals(givenHeroFighterResult.getLevel(), actualHeroes.get(0).level);
    }

    @Test
    void should_not_attack_heroes_each_other_with_dead_hero_defender() {
        val givenHeroFighter = Hero.builder()
                .hp(15)
                .build();
        val givenHeroDefender = Hero.builder()
                .hp(0)
                .build();

        val actualHeroes = battleService.attackHeroesEachOther(givenHeroFighter, givenHeroDefender);

        Assertions.assertEquals(actualHeroes.size(), 2);
        Assertions.assertEquals(givenHeroDefender, actualHeroes.get(1));
        Assertions.assertEquals(givenHeroFighter, actualHeroes.get(0));
    }

}
