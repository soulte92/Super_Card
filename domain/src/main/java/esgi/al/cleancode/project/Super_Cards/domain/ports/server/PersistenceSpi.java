package esgi.al.cleancode.project.Super_Cards.domain.ports.server;

import java.util.Optional;

public interface PersistenceSpi<T, ID> {
    T save(T o);

    Optional<T> findById(ID id);
}
