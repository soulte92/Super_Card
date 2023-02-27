package esgi.al.cleancode.project.Super_Cards.domain.functional.service;

import esgi.al.cleancode.project.Super_Cards.domain.exceptions.ApplicationException;
import esgi.al.cleancode.project.Super_Cards.domain.exceptions.DeckException;
import esgi.al.cleancode.project.Super_Cards.domain.exceptions.HeroException;
import esgi.al.cleancode.project.Super_Cards.domain.exceptions.RoundException;
import esgi.al.cleancode.project.Super_Cards.domain.functional.enums.Winner;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Hero;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Round;
import esgi.al.cleancode.project.Super_Cards.domain.ports.client.BattleApi;
import esgi.al.cleancode.project.Super_Cards.domain.ports.server.PlayerHeroPersistenceSpi;
import esgi.al.cleancode.project.Super_Cards.domain.ports.server.RoundPersistenceSpi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class BattleService implements BattleApi {
    private final RoundCreatorService roundCreatorService;
    private final PlayerHeroPersistenceSpi playerHeroPersistenceSpi;
    private final RoundPersistenceSpi roundPersistenceSpi;

    @Override
    public Round attack(UUID sessionId, UUID firstPlayerId, UUID secondPlayerId, UUID firstPlayerHeroId, UUID secondPlayerHeroId) {
        Optional<Hero> firstPlayerHero = playerHeroPersistenceSpi.findById(firstPlayerHeroId);
        Optional<Hero> secondPlayerHero = playerHeroPersistenceSpi.findById(secondPlayerHeroId);

        // Check Heroes existence
        if (firstPlayerHero.isEmpty()) {
            throw HeroException.notFoundHero(firstPlayerHeroId);
        } else if (secondPlayerHero.isEmpty()) {
            throw HeroException.notFoundHero(secondPlayerHeroId);
        }
        // Get Heroes
        Hero heroFighter = firstPlayerHero.get();
        Hero heroDefender = secondPlayerHero.get();

        // Create a new round
        Round round = roundCreatorService.create(sessionId, firstPlayerId, secondPlayerId, firstPlayerHeroId, secondPlayerHeroId);
        if (round == null) {
            throw new RoundException("attack error: Create round error");
        }

        // Hero fighter and defender with each others
        Round roundResult = this.attackHeroesEachOtherInRound(round, heroFighter, heroDefender);

        // Save the battle round results
        roundResult = roundPersistenceSpi.save(roundResult);
        if (roundResult == null){
            throw new ApplicationException("Battle Error: Saving round error");
        } else {
            return roundResult;
        }
    }

    public Round attackHeroesEachOtherInRound(Round round, Hero heroFighter, Hero heroDefender) {
        // Initialize stats variables
        String winner = "";
        int firstPlayerHeroNbHit = 0;
        int secondPlayerHeroNbHit = 0;

        boolean switchState = false;
        boolean deadHero = false;
        // Attack heroes each others until we have a dead hero
        while (!deadHero) {
            // The hero Fighter attacks the hero Defender
            if (switchState) {
                ArrayList<Hero> result = this.attackHeroesEachOther(heroFighter, heroDefender);
                heroFighter = playerHeroPersistenceSpi.save(result.get(0));
                heroDefender = playerHeroPersistenceSpi.save(result.get(1));
                firstPlayerHeroNbHit++;
                winner = HeroUtils.isHeroDead(heroDefender) ? Winner.FIRSTPLAYERHERO.label : null;
                switchState = false;
            }
            // The hero Defender attacks the hero Fighter
            else {
                ArrayList<Hero> result = this.attackHeroesEachOther(heroDefender, heroFighter);
                heroDefender = playerHeroPersistenceSpi.save(result.get(0));
                heroFighter = playerHeroPersistenceSpi.save(result.get(1));
                secondPlayerHeroNbHit++;
                winner = HeroUtils.isHeroDead(heroFighter) ? Winner.SECONDPLAYERHERO.label : null;
                switchState = true;
            }
            // Change the status to break the loop
            if (winner != null) {
                deadHero = true;
            }
        }

        // Return the battle round results
        return Round.builder()
                .roundId(round.getRoundId())
                .sessionId(round.getSessionId())
                .firstPlayerId(round.getFirstPlayerId())
                .secondPlayerId(round.getSecondPlayerId())
                .firstPlayerHeroId(round.getFirstPlayerHeroId())
                .secondPlayerHeroId(round.getSecondPlayerHeroId())
                .firstPlayerHeroNbHit(firstPlayerHeroNbHit)
                .secondPlayerHeroNbHit(secondPlayerHeroNbHit)
                .winner(winner)
                .creationDate(round.getCreationDate())
                .build();
    }

    public ArrayList<Hero> attackHeroesEachOther(Hero heroFighter, Hero heroDefender) {
        // heroFighter don't attack if heroDefender is dead
        if (HeroUtils.isHeroDead(heroDefender)) {
            ArrayList<Hero> result = new ArrayList<>();
            result.add(heroFighter);
            result.add(heroDefender);
            return result;
        }

        // Decrease heroDefender hp
        int hpToRetrieve = (int) ((heroFighter.power + getPowerAdvantage(heroFighter.speciality, heroFighter.rarity)) - heroDefender.armor);
        heroDefender = HeroUtils.retrieveHeroHp(heroDefender, hpToRetrieve);

        // Increase heroFighter xp and update characteristics
        if (HeroUtils.isHeroDead(heroDefender)) {

            // Increase xp
            int xpToIncrease = 1;
            heroFighter = HeroUtils.increaseHeroXp(heroFighter, xpToIncrease);

            // Update heroFighter level
            heroFighter = HeroUtils.updateHeroLevel(heroFighter);

            // Increase characteristics
            double percent = 0.1;
            heroFighter = HeroUtils.enhanceCharacteristicsByPerCent(heroFighter, percent);
            ArrayList<Hero> result = new ArrayList<>();
            result.add(heroFighter);
            result.add(heroDefender);
            return result;
        }
        ArrayList<Hero> result = new ArrayList<>();
        result.add(heroFighter);
        result.add(heroDefender);
        return result;
    }

    public int getPowerAdvantage(String heroFighterSpeciality, String heroDefenderSpeciality){
        return HeroUtils.configSpecialPowerMap().get(heroFighterSpeciality).get(heroDefenderSpeciality);
    }
}
