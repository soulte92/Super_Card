package esgi.al.cleancode.project.Super_Cards.server.postgres.adapter;

import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Hero;
import esgi.al.cleancode.project.Super_Cards.domain.functional.model.Session;
import esgi.al.cleancode.project.Super_Cards.server.postgres.entity.SessionEntity;
import esgi.al.cleancode.project.Super_Cards.server.postgres.mapper.SessionEntityMapper;
import esgi.al.cleancode.project.Super_Cards.server.postgres.repository.SessionRepository;
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
class SessionDatabaseAdapterTest {

    @InjectMocks
    private SessionDatabaseAdapter adapter;

    @Mock
    private SessionRepository repository;

    @Nested
    class Save {

        @Captor
        private ArgumentCaptor<SessionEntity> entityCaptor;

        @Test
        void should_save() {
            val session = Session.builder().build();
            val entity = SessionEntityMapper.fromDomain(session);

            when(repository.save(any(SessionEntity.class))).thenReturn(entity);

            val actual = adapter.save(session);

            verify(repository).save(entityCaptor.capture());
            verifyNoMoreInteractions(repository);

            assertThat(actual).isInstanceOf(Hero.class);
            assertThat(actual).usingRecursiveComparison().isEqualTo(session);
            assertThat(entityCaptor.getValue()).usingRecursiveComparison().isEqualTo(entity);
        }

        @Test
        void should_not_save_if_repository_throw_exception() {
            val session = Session.builder().build();
            val entity = SessionEntityMapper.fromDomain(session);
            val throwable = new IllegalArgumentException();

            doThrow(throwable).when(repository).save(any(SessionEntity.class));

            val actual = adapter.save(session);

            verify(repository).save(entityCaptor.capture());
            verifyNoMoreInteractions(repository);
        }
    }

    @Nested
    class FindById {
        @Test
        void should_find() {
            val id = UUID.randomUUID();
            val entity = SessionEntity.builder().build();
            val domain = SessionEntityMapper.toDomain(entity);

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
