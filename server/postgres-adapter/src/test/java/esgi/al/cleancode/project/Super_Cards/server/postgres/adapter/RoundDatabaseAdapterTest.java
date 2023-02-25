package esgi.al.cleancode.project.Super_Cards.server.postgres.adapter;

import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Hero;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Round;
import esgi.al.cleancode.project.Super_Cards.server.postgres.entity.RoundEntity;
import esgi.al.cleancode.project.Super_Cards.server.postgres.mapper.RoundEntityMapper;
import esgi.al.cleancode.project.Super_Cards.server.postgres.repository.RoundRepository;
import lombok.val;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoundDatabaseAdapterTest {

    @InjectMocks
    private RoundDatabaseAdapter adapter;

    @Mock
    private RoundRepository repository;

    @Nested
    class Save {

        @Captor
        private ArgumentCaptor<RoundEntity> entityCaptor;

        @Test
        void should_save() {
            val round = Round.builder().build();
            val entity = RoundEntityMapper.fromDomain(round);

            when(repository.save(any(RoundEntity.class))).thenReturn(entity);

            val actual = adapter.save(round);

            verify(repository).save(entityCaptor.capture());
            verifyNoMoreInteractions(repository);

            assertThat(actual).isInstanceOf(Hero.class);
            assertThat(actual).usingRecursiveComparison().isEqualTo(round);
            assertThat(entityCaptor.getValue()).usingRecursiveComparison().isEqualTo(entity);
        }

        @Test
        void should_not_save_if_repository_throw_exception() {
            val round = Round.builder().build();
            val entity = RoundEntityMapper.fromDomain(round);
            val throwable = new IllegalArgumentException();

            doThrow(throwable).when(repository).save(any(RoundEntity.class));

            val actual = adapter.save(round);

            verify(repository).save(entityCaptor.capture());
            verifyNoMoreInteractions(repository);
        }
    }

    @Nested
    class FindById {
        @Test
        void should_find() {
            val id = UUID.randomUUID();
            val entity = RoundEntity.builder().build();
            val domain = RoundEntityMapper.toDomain(entity);

            when(repository.findById(id)).thenReturn(Optional.of(entity));

            val actual = adapter.findById(id);

            assertThat(actual).isPresent();
            assertThat(actual.get()).usingRecursiveComparison().isEqualTo(domain);

            verifyNoMoreInteractions(repository);
        }

        @Test
        void should_not_find() {
            val id = UUID.randomUUID();

            when(repository.findById(id)).thenReturn(Optional.empty());

            val actual = adapter.findById(id);

            assertThat(actual).isEmpty();

            verifyNoMoreInteractions(repository);
        }
    }
}
