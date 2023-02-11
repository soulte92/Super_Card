package esgi.al.cleancode.project.Super_Cards.bootstrap.config.domain;

import esgi.al.cleancode.project.Super_Cards.domain.functional.service.DefaultHeroesPopulationService;
import esgi.al.cleancode.project.Super_Cards.domain.ports.client.DefaultHeroesPopulationApi;
import esgi.al.cleancode.project.Super_Cards.domain.ports.server.HeroPersistenceSpi;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfiguration {

  @Bean
  public DefaultHeroesPopulationApi drivingLicenceCreatorService(@Qualifier("defaultHeroDatabaseAdapter") HeroPersistenceSpi spi) {
    return new DefaultHeroesPopulationService(spi);
  }

}
