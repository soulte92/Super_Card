package esgi.al.cleancode.project.Super_Cards.bootstrap.config.domain;
import esgi.al.cleancode.project.Super_Cards.domain.functional.service.*;
import esgi.al.cleancode.project.Super_Cards.domain.ports.client.*;
import esgi.al.cleancode.project.Super_Cards.domain.ports.server.*;
import esgi.al.cleancode.project.Super_Cards.server.postgres.entity.DefaultHeroEntity;
import esgi.al.cleancode.project.Super_Cards.server.postgres.entity.PlayerHeroEntity;
import esgi.al.cleancode.project.Super_Cards.server.postgres.repository.DefaultHeroRepository;
import esgi.al.cleancode.project.Super_Cards.server.postgres.repository.PlayerHeroRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
  public PlayerCreatorApi playerService(@Qualifier("playerDatabaseAdapter") PlayerPersistenceSpi playerPersistenceSpi, DeckPersistenceSpi deckPersistenceSpi) {
    return new PlayerCreatorService(playerPersistenceSpi, new DeckCreatorService(deckPersistenceSpi));
  }
  @Bean
  public DeckCreatorApi deckService(@Qualifier("deckDatabaseAdapter") DeckPersistenceSpi spi) {
    return new DeckCreatorService(spi);
  }
  @Bean
  public PlayerHeroAppenderInDeckApi playerHeroAppenderService(@Qualifier("playerDatabaseAdapter")PlayerPersistenceSpi playerPersistenceSpi,
                                                               @Qualifier("deckDatabaseAdapter") DeckPersistenceSpi deckPersistenceSpi,
                                                               @Qualifier("defaultHeroDatabaseAdapter") DefaultHeroPersistenceSpi defaultHeroPersistenceSpi,
                                                               @Qualifier("playerHeroDatabaseAdapter") PlayerHeroPersistenceSpi playerHeroPersistenceSpi) {
    return new PlayerHeroAppenderInDeck(playerPersistenceSpi, deckPersistenceSpi, new PlayerHeroCreatorService(defaultHeroPersistenceSpi, playerHeroPersistenceSpi));
  }

}
