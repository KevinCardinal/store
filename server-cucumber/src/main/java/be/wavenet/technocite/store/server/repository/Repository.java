package be.wavenet.technocite.store.server.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {

    List<T> findAll();

    Optional<T> findById(String id);
}
