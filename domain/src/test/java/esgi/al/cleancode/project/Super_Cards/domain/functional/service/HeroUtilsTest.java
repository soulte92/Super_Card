package esgi.al.cleancode.project.Super_Cards.domain.functional.service;

import esgi.al.cleancode.project.Super_Cards.domain.exceptions.HeroException;
import esgi.al.cleancode.project.Super_Cards.domain.functional.enums.Rarity;
import esgi.al.cleancode.project.Super_Cards.domain.functional.enums.Speciality;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Hero;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HeroUtilsTest {

    @Test
    void is_hero_dead_should_return_true() {
        val givenHero = Hero.builder()
                .name("super-boy")
                .hp(0).build();
        Assertions.assertTrue(HeroUtils.isHeroDead(givenHero));
    }

    @Test
    void is_hero_dead_should_return_false() {
        val givenHero = Hero.builder()
                .name("super-boy")
                .hp(30).build();
        Assertions.assertFalse(HeroUtils.isHeroDead(givenHero));
    }

    @Test
    void should_retrieve_hero_hp() {
        val givenHero = Hero.builder()
            .name("super-boy")
            .hp(30).build();
        val actualHero = HeroUtils.retrieveHeroHp(givenHero, 10);
        Assertions.assertEquals(20, actualHero.getHp());
    }

    @Test
    void should_not_retrieve_hero_hp() {
        // With dead hero with hp = 0
        val givenHero = Hero.builder()
            .name("super-boy")
            .hp(0).build();
        val actualHero = HeroUtils.retrieveHeroHp(givenHero, 10);
        Assertions.assertEquals(0, actualHero.getHp());
    }

    @Test
    void should_increase_hero_xp() {
        val givenHero = Hero.builder()
                .name("super-boy")
                .xp(30).build();
        val actualHero = HeroUtils.increaseHeroXp(givenHero, 10);
        Assertions.assertEquals(40, actualHero.getXp());
    }

    @Test
    void should_increase_hero_xp_infinitely() {
        val givenHero = Hero.builder()
                .name("super-boy")
                .xp(30).build();
        val actualHero = HeroUtils.increaseHeroXp(givenHero, 10000);
        Assertions.assertEquals(10030, actualHero.getXp());
    }

    @Test
    void should_update_hero_level() {
        val givenHero = Hero.builder()
                .name("super-boy")
                .xp(30)
                .level(5).build();
        // Before update level
        Assertions.assertEquals(5, givenHero.getLevel());
        val actualHero = HeroUtils.updateHeroLevel(givenHero);

        //After update level
        Assertions.assertEquals(6, actualHero.getLevel());
    }

    @Test
    void should_config_special_power_map() {
        val givenSpecialPowerMap = HeroUtils.configSpecialPowerMap();

        // Check Advantages points
        Assertions.assertEquals(20, givenSpecialPowerMap.get(Speciality.TANK.label).get(Speciality.MAGICIAN.label));
        Assertions.assertEquals(30, givenSpecialPowerMap.get(Speciality.KILLER.label).get(Speciality.TANK.label));
        Assertions.assertEquals(25, givenSpecialPowerMap.get(Speciality.MAGICIAN.label).get(Speciality.KILLER.label));

        // Check 0 zeros advantages points
        Assertions.assertEquals(0, givenSpecialPowerMap.get(Speciality.TANK.label).get(Speciality.TANK.label));
        Assertions.assertEquals(0, givenSpecialPowerMap.get(Speciality.TANK.label).get(Speciality.KILLER.label));
        Assertions.assertEquals(0, givenSpecialPowerMap.get(Speciality.KILLER.label).get(Speciality.KILLER.label));
        Assertions.assertEquals(0, givenSpecialPowerMap.get(Speciality.KILLER.label).get(Speciality.MAGICIAN.label));
        Assertions.assertEquals(0, givenSpecialPowerMap.get(Speciality.MAGICIAN.label).get(Speciality.TANK.label));
        Assertions.assertEquals(0, givenSpecialPowerMap.get(Speciality.MAGICIAN.label).get(Speciality.MAGICIAN.label));
    }

    @Test
    void should_initialize_characteristics_by_tank_speciality() {
        val givenName = "super-boy";
        val givenSpeciality = Speciality.TANK.label;
        val givenRarity = Rarity.COMMON.label;
        val actualHero = HeroUtils.initCharacteristicsBySpeciality(givenName, givenSpeciality, givenRarity);

        Assertions.assertEquals(1000, actualHero.getHp());
        Assertions.assertEquals(100, actualHero.getPower());
        Assertions.assertEquals(20, actualHero.getArmor());
    }

    @Test
    void should_initialize_characteristics_by_killer_speciality() {
        val givenName = "super-boy";
        val givenSpeciality = Speciality.KILLER.label;
        val givenRarity = Rarity.COMMON.label;
        val actualHero = HeroUtils.initCharacteristicsBySpeciality(givenName, givenSpeciality, givenRarity);

        Assertions.assertEquals(800, actualHero.getHp());
        Assertions.assertEquals(200, actualHero.getPower());
        Assertions.assertEquals(5, actualHero.getArmor());
    }

    @Test
    void should_initialize_characteristics_by_magician_speciality() {
        val givenName = "super-boy";
        val givenSpeciality = Speciality.MAGICIAN.label;
        val givenRarity = Rarity.COMMON.label;
        val actualHero = HeroUtils.initCharacteristicsBySpeciality(givenName, givenSpeciality, givenRarity);

        Assertions.assertEquals(700, actualHero.getHp());
        Assertions.assertEquals(150, actualHero.getPower());
        Assertions.assertEquals(10, actualHero.getArmor());
    }

    @Test
    void should_throw_exception_initialize_characteristics_by_tank_speciality() {
        val givenName = "super-boy";
        val givenSpeciality = "TOTO";
        val givenRarity = Rarity.COMMON.label;

        Assertions.assertThrows(HeroException.class, () -> {
            HeroUtils.initCharacteristicsBySpeciality(givenName, givenSpeciality, givenRarity);
        });
    }

    @Test
    void should_enhance_characteristic_by_common_rarity() {
        val givenHero = Hero.builder()
                .name("super-boy")
                .hp(700)
                .power(150)
                .armor(10)
                .rarity(Rarity.COMMON.label)
                .build();

        Assertions.assertEquals(700, givenHero.getHp());
        Assertions.assertEquals(150, givenHero.getPower());
        Assertions.assertEquals(10, givenHero.getArmor());

        val actualHero = HeroUtils.enhanceCharacteristicsByRarity(givenHero);

        Assertions.assertEquals(700, actualHero.getHp());
        Assertions.assertEquals(150, actualHero.getPower());
        Assertions.assertEquals(10, actualHero.getArmor());
    }

    @Test
    void should_enhance_characteristic_by_rare_rarity() {
        val givenHero = Hero.builder()
                .name("super-boy")
                .hp(700)
                .power(150)
                .armor(10)
                .rarity(Rarity.RARE.label)
                .build();

        Assertions.assertEquals(700, givenHero.getHp());
        Assertions.assertEquals(150, givenHero.getPower());
        Assertions.assertEquals(10, givenHero.getArmor());

        val actualHero = HeroUtils.enhanceCharacteristicsByRarity(givenHero);

        Assertions.assertEquals(770, actualHero.getHp());
        Assertions.assertEquals(165, actualHero.getPower());
        Assertions.assertEquals(11, actualHero.getArmor());
    }

    @Test
    void should_enhance_characteristic_by_legendary_rarity() {
        val givenHero = Hero.builder()
                .name("super-boy")
                .hp(700)
                .power(150)
                .armor(10)
                .rarity(Rarity.LEGENDARY.label)
                .build();

        Assertions.assertEquals(700, givenHero.getHp());
        Assertions.assertEquals(150, givenHero.getPower());
        Assertions.assertEquals(10, givenHero.getArmor());

        val actualHero = HeroUtils.enhanceCharacteristicsByRarity(givenHero);

        Assertions.assertEquals(840, actualHero.getHp());
        Assertions.assertEquals(180, actualHero.getPower());
        Assertions.assertEquals(12, actualHero.getArmor());
    }

    @Test
    void should_throw_exception_enhance_characteristic_by_rarity() {
        val givenHero = Hero.builder()
                .name("super-boy")
                .hp(700)
                .power(150)
                .armor(10)
                .rarity("JOKER")
                .build();
        Assertions.assertThrows(HeroException.class, () -> {
            HeroUtils.enhanceCharacteristicsByRarity(givenHero);
        });
    }

    @Test
    void should_enhance_characteristics_by_percent() {
        val givenHero = Hero.builder()
                .name("super-boy")
                .hp(700)
                .power(150)
                .armor(10)
                .rarity(Rarity.LEGENDARY.label)
                .build();

        Assertions.assertEquals(700, givenHero.getHp());
        Assertions.assertEquals(150, givenHero.getPower());
        Assertions.assertEquals(10, givenHero.getArmor());

        val actualHero = HeroUtils.enhanceCharacteristicsByPerCent(givenHero, 0.2);

        Assertions.assertEquals(840, actualHero.getHp());
        Assertions.assertEquals(180, actualHero.getPower());
        Assertions.assertEquals(12, actualHero.getArmor());
    }
    @Test
    void should_throw_enhance_characteristics_by_percent() {
        val givenHero = Hero.builder()
                .name("super-boy")
                .hp(700)
                .power(150)
                .armor(10)
                .rarity("JOKER")
                .build();
        // with percent < 0
        Assertions.assertThrows(HeroException.class, () -> {
            HeroUtils.enhanceCharacteristicsByPerCent(givenHero, -0.01);
        });
        // with percent > 0
        Assertions.assertThrows(HeroException.class, () -> {
            HeroUtils.enhanceCharacteristicsByPerCent(givenHero, 1.01);
        });

    }
}