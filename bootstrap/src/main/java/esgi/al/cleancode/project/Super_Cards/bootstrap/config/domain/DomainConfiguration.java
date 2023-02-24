package esgi.al.cleancode.project.Super_Cards.bootstrap.config.domain;
import esgi.al.cleancode.project.Super_Cards.domain.functional.service.*;
import esgi.al.cleancode.project.Super_Cards.domain.ports.client.*;
import esgi.al.cleancode.project.Super_Cards.domain.ports.server.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfiguration {

  @Bean
  public DefaultHeroesPopulationApi defaultHeroService(@Qualifier("defaultHeroDatabaseAdapter") DefaultHeroPersistenceSpi defaultHeroPersistenceSpi) {
    return new DefaultHeroesPopulationService(defaultHeroPersistenceSpi);
  }
  @Bean
  public PlayerHeroCreatorApi playerHeroService(@Qualifier("defaultHeroDatabaseAdapter") DefaultHeroPersistenceSpi defaultHeroPersistenceSpi,
                                                @Qualifier("playerHeroDatabaseAdapter") PlayerHeroPersistenceSpi playerHeroPersistenceSpi) {
    return new PlayerHeroCreatorService(defaultHeroPersistenceSpi, playerHeroPersistenceSpi);
  }
  @Bean
  public PlayerCreatorApi playerCreatorService(@Qualifier("playerDatabaseAdapter") PlayerPersistenceSpi playerPersistenceSpi, DeckPersistenceSpi deckPersistenceSpi) {
    return new PlayerCreatorService(playerPersistenceSpi, new DeckCreatorService(deckPersistenceSpi));
  }
  @Bean
  public DeckCreatorApi deckCreatorService(@Qualifier("deckDatabaseAdapter") DeckPersistenceSpi spi) {
    return new DeckCreatorService(spi);
  }
  @Bean
  public PlayerHeroAppenderInDeckApi playerHeroAppenderService(@Qualifier("playerDatabaseAdapter")PlayerPersistenceSpi playerPersistenceSpi,
                                                               @Qualifier("deckDatabaseAdapter") DeckPersistenceSpi deckPersistenceSpi,
                                                               @Qualifier("defaultHeroDatabaseAdapter") DefaultHeroPersistenceSpi defaultHeroPersistenceSpi,
                                                               @Qualifier("playerHeroDatabaseAdapter") PlayerHeroPersistenceSpi playerHeroPersistenceSpi) {
    return new PlayerHeroAppenderInDeckService(playerPersistenceSpi, deckPersistenceSpi, new PlayerHeroCreatorService(defaultHeroPersistenceSpi, playerHeroPersistenceSpi));
  }
  @Bean
  public PlayerHeroPackAppenderApi playerHeroPackAppenderService(@Qualifier("playerDatabaseAdapter")PlayerPersistenceSpi playerPersistenceSpi,
                                                                 @Qualifier("deckDatabaseAdapter") DeckPersistenceSpi deckPersistenceSpi,
                                                                 @Qualifier("defaultHeroDatabaseAdapter") DefaultHeroPersistenceSpi defaultHeroPersistenceSpi,
                                                                 @Qualifier("playerHeroDatabaseAdapter") PlayerHeroPersistenceSpi playerHeroPersistenceSpi) {
    return new PlayerHeroPackAppenderService(playerPersistenceSpi, playerHeroPersistenceSpi, new PlayerHeroAppenderInDeckService(playerPersistenceSpi,
            deckPersistenceSpi, new PlayerHeroCreatorService(defaultHeroPersistenceSpi, playerHeroPersistenceSpi)));
  }
  @Bean
  public PlayerDeckDisplayerApi PlayerDeckDisplayerService(@Qualifier("playerDatabaseAdapter")PlayerPersistenceSpi playerPersistenceSpi,
                                                               @Qualifier("deckDatabaseAdapter") DeckPersistenceSpi deckPersistenceSpi,
                                                               @Qualifier("playerHeroDatabaseAdapter") PlayerHeroPersistenceSpi playerHeroPersistenceSpi) {
    return new PlayerDeckDisplayerService(playerPersistenceSpi, deckPersistenceSpi, playerHeroPersistenceSpi);
  }
  @Bean
  public SessionCreatorApi sessionCreatorService(@Qualifier("sessionDatabaseAdapter") SessionPersistenceSpi sessionPersistenceSpi) {
    return new SessionCreatorService(sessionPersistenceSpi);
  }
  @Bean
  public RoundCreatorApi roundCreatorService(@Qualifier("roundDatabaseAdapter") RoundPersistenceSpi roundPersistenceSpi) {
    return new RoundCreatorService(roundPersistenceSpi);
  }
  @Bean
  public BattleApi BattleService(@Qualifier("roundDatabaseAdapter") RoundPersistenceSpi roundPersistenceSpi,
                                 @Qualifier("playerHeroDatabaseAdapter") PlayerHeroPersistenceSpi playerHeroPersistenceSpi) {
    return new BattleService(new RoundCreatorService(roundPersistenceSpi), playerHeroPersistenceSpi, roundPersistenceSpi);
  }
  @Bean
  public PlayerHeroRoundFinderApi playerHeroRoundFinderService(@Qualifier("roundDatabaseAdapter") RoundPersistenceSpi roundPersistenceSpi) {
    return new PlayerHeroRoundFinderService(roundPersistenceSpi);
  }
  @Bean
  public PlayerFinderApi playerFinderService(@Qualifier("playerDatabaseAdapter") PlayerPersistenceSpi playerPersistenceSpi) {
    return new PlayerFinderService(playerPersistenceSpi);
  }
}
