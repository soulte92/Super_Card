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
