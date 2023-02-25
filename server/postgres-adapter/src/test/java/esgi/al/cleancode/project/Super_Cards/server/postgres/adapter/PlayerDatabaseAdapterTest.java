package esgi.al.cleancode.project.Super_Cards.server.postgres.adapter;

import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Player;
import esgi.al.cleancode.project.Super_Cards.server.postgres.entity.PlayerEntity;
import esgi.al.cleancode.project.Super_Cards.server.postgres.mapper.PlayerEntityMapper;
import esgi.al.cleancode.project.Super_Cards.server.postgres.repository.PlayerRepository;
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
class PlayerDatabaseAdapterTest {

    @InjectMocks
    private PlayerDatabaseAdapter adapter;

    @Mock
    private PlayerRepository repository;

    @Nested
    class Save {

        @Captor
        private ArgumentCaptor<PlayerEntity> entityCaptor;

        @Test
        void should_save() {
            val player = Player.builder().build();
            val entity = PlayerEntityMapper.fromDomain(player);

            when(repository.save(any(PlayerEntity.class))).thenReturn(entity);

            val actual = adapter.save(player);

            verify(repository).save(entityCaptor.capture());
            verifyNoMoreInteractions(repository);

            assertThat(actual).isInstanceOf(Player.class);
            assertThat(actual).usingRecursiveComparison().isEqualTo(player);
            assertThat(entityCaptor.getValue()).usingRecursiveComparison().isEqualTo(entity);
        }

        @Test
        void should_not_save_if_repository_throw_exception() {
            val player = Player.builder().build();
            PlayerEntityMapper.fromDomain(player);
            val throwable = new IllegalArgumentException();

            doThrow(throwable).when(repository).save(any(PlayerEntity.class));

            adapter.save(player);

            verify(repository).save(entityCaptor.capture());
            verifyNoMoreInteractions(repository);
        }
    }

    @Nested
    class FindById {
        @Test
        void should_find() {
            val id = UUID.randomUUID();
            val entity = PlayerEntity.builder().build();
            val domain = PlayerEntityMapper.toDomain(entity);

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
