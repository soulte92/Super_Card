package esgi.al.cleancode.project.Super_Cards.bootstrap.config.domain;
import esgi.al.cleancode.project.Super_Cards.domain.functional.service.DeckCreatorService;
import esgi.al.cleancode.project.Super_Cards.domain.functional.service.DefaultHeroesPopulationService;
import esgi.al.cleancode.project.Super_Cards.domain.functional.service.PlayerCreatorService;
import esgi.al.cleancode.project.Super_Cards.domain.functional.service.PlayerHeroCreatorService;
import esgi.al.cleancode.project.Super_Cards.domain.ports.client.DeckCreatorApi;
import esgi.al.cleancode.project.Super_Cards.domain.ports.client.DefaultHeroesPopulationApi;
import esgi.al.cleancode.project.Super_Cards.domain.ports.client.PlayerCreatorApi;
import esgi.al.cleancode.project.Super_Cards.domain.ports.client.PlayerHeroCreatorApi;
import esgi.al.cleancode.project.Super_Cards.domain.ports.server.DeckPersistenceSpi;
import esgi.al.cleancode.project.Super_Cards.domain.ports.server.HeroPersistenceSpi;
import esgi.al.cleancode.project.Super_Cards.domain.ports.server.PlayerPersistenceSpi;
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
  public DefaultHeroesPopulationApi defaultHeroService(@Qualifier("defaultHeroDatabaseAdapter") HeroPersistenceSpi spi) {
    return new DefaultHeroesPopulationService(spi);
  }
  @Bean
  public PlayerHeroCreatorApi playerHeroService(@Qualifier("playerHeroDatabaseAdapter") HeroPersistenceSpi spi) {
    return new PlayerHeroCreatorService(spi);
  }
  @Bean
  public PlayerCreatorApi playerService(@Qualifier("playerDatabaseAdapter") PlayerPersistenceSpi playerPersistenceSpi, DeckPersistenceSpi deckPersistenceSpi) {
    return new PlayerCreatorService(playerPersistenceSpi, new DeckCreatorService(deckPersistenceSpi));
  }
  @Bean
  public DeckCreatorApi deckService(@Qualifier("deckDatabaseAdapter") DeckPersistenceSpi spi) {
    return new DeckCreatorService(spi);
  }

}
