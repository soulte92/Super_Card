package esgi.al.cleancode.project.Super_Cards.bootstrap.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import esgi.al.cleancode.project.Super_Cards.bootstrap.config.domain.DomainConfiguration;
import esgi.al.cleancode.project.Super_Cards.server.postgres.entity.DefaultHeroEntity;
import esgi.al.cleancode.project.Super_Cards.server.postgres.entity.PlayerHeroEntity;
import esgi.al.cleancode.project.Super_Cards.server.postgres.repository.DefaultHeroRepository;
import esgi.al.cleancode.project.Super_Cards.server.postgres.repository.PlayerHeroRepository;
import io.vavr.jackson.datatype.VavrModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Configuration
@Import({DomainConfiguration.class})
public class ApplicationConfiguration {

  @Bean
  public ObjectMapper objectMapper() {
    return new ObjectMapper().registerModule(new VavrModule());
  }

  @Bean
  public DefaultHeroRepository defaultHeroRepository() {
    return new DefaultHeroRepository() {
      @Override
      public List<DefaultHeroEntity> findAll() {
        return null;
      }

      @Override
      public List<DefaultHeroEntity> findAll(Sort sort) {
        return null;
      }

      @Override
      public Page<DefaultHeroEntity> findAll(Pageable pageable) {
        return null;
      }

      @Override
      public List<DefaultHeroEntity> findAllById(Iterable<UUID> uuids) {
        return null;
      }

      @Override
      public long count() {
        return 0;
      }

      @Override
      public void deleteById(UUID uuid) {

      }

      @Override
      public void delete(DefaultHeroEntity entity) {

      }

      @Override
      public void deleteAll(Iterable<? extends DefaultHeroEntity> entities) {

      }

      @Override
      public void deleteAll() {

      }

      @Override
      public <S extends DefaultHeroEntity> S save(S entity) {
        return null;
      }

      @Override
      public <S extends DefaultHeroEntity> List<S> saveAll(Iterable<S> entities) {
        return null;
      }

      @Override
      public Optional<DefaultHeroEntity> findById(UUID uuid) {
        return Optional.empty();
      }

      @Override
      public boolean existsById(UUID uuid) {
        return false;
      }

      @Override
      public void flush() {

      }

      @Override
      public <S extends DefaultHeroEntity> S saveAndFlush(S entity) {
        return null;
      }

      @Override
      public void deleteInBatch(Iterable<DefaultHeroEntity> entities) {

      }

      @Override
      public void deleteAllInBatch() {

      }

      @Override
      public DefaultHeroEntity getOne(UUID uuid) {
        return null;
      }

      @Override
      public <S extends DefaultHeroEntity> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
      }

      @Override
      public <S extends DefaultHeroEntity> List<S> findAll(Example<S> example) {
        return null;
      }

      @Override
      public <S extends DefaultHeroEntity> List<S> findAll(Example<S> example, Sort sort) {
        return null;
      }

      @Override
      public <S extends DefaultHeroEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
      }

      @Override
      public <S extends DefaultHeroEntity> long count(Example<S> example) {
        return 0;
      }

      @Override
      public <S extends DefaultHeroEntity> boolean exists(Example<S> example) {
        return false;
      }

      @Override
      public Optional<DefaultHeroEntity> findDefaultHeroEntityById(UUID id) {
        return Optional.empty();
      }
    };
  }

  @Bean
  public PlayerHeroRepository playerHeroRepository() {
    return new PlayerHeroRepository() {
      @Override
      public List<PlayerHeroEntity> findAll() {
        return null;
      }

      @Override
      public List<PlayerHeroEntity> findAll(Sort sort) {
        return null;
      }

      @Override
      public Page<PlayerHeroEntity> findAll(Pageable pageable) {
        return null;
      }

      @Override
      public List<PlayerHeroEntity> findAllById(Iterable<UUID> uuids) {
        return null;
      }

      @Override
      public long count() {
        return 0;
      }

      @Override
      public void deleteById(UUID uuid) {

      }

      @Override
      public void delete(PlayerHeroEntity entity) {

      }

      @Override
      public void deleteAll(Iterable<? extends PlayerHeroEntity> entities) {

      }

      @Override
      public void deleteAll() {

      }

      @Override
      public <S extends PlayerHeroEntity> S save(S entity) {
        return null;
      }

      @Override
      public <S extends PlayerHeroEntity> List<S> saveAll(Iterable<S> entities) {
        return null;
      }

      @Override
      public Optional<PlayerHeroEntity> findById(UUID uuid) {
        return Optional.empty();
      }

      @Override
      public boolean existsById(UUID uuid) {
        return false;
      }

      @Override
      public void flush() {

      }

      @Override
      public <S extends PlayerHeroEntity> S saveAndFlush(S entity) {
        return null;
      }

      @Override
      public void deleteInBatch(Iterable<PlayerHeroEntity> entities) {

      }

      @Override
      public void deleteAllInBatch() {

      }

      @Override
      public PlayerHeroEntity getOne(UUID uuid) {
        return null;
      }

      @Override
      public <S extends PlayerHeroEntity> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
      }

      @Override
      public <S extends PlayerHeroEntity> List<S> findAll(Example<S> example) {
        return null;
      }

      @Override
      public <S extends PlayerHeroEntity> List<S> findAll(Example<S> example, Sort sort) {
        return null;
      }

      @Override
      public <S extends PlayerHeroEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
      }

      @Override
      public <S extends PlayerHeroEntity> long count(Example<S> example) {
        return 0;
      }

      @Override
      public <S extends PlayerHeroEntity> boolean exists(Example<S> example) {
        return false;
      }

      @Override
      public Optional<PlayerHeroEntity> findPlayerHeroEntityById(UUID id) {
        return Optional.empty();
      }
    };
  }
}
