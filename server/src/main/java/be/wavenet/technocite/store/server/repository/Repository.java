package be.wavenet.technocite.store.server.repository;

import java.util.Collection;
import java.util.Optional;

public interface Repository<T> {

    Collection<T> findAll();

    Optional<T> findById(String id);
}
