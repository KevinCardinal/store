package be.wavenet.technocite.store.server.repository.impl;

import be.wavenet.technocite.store.server.repository.Repository;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.List;
import java.util.Optional;

public abstract class AbstractRepository<T> implements Repository<T> {

    private List<T> values;

    @SuppressWarnings("unchecked")
    public AbstractRepository(ObjectReader objectReader,
                              ResourceLoader resourceLoader,
                              String csvFile,
                              Class<T> type) {
        try {
            InputStream file = resourceLoader.getResource(csvFile).getInputStream();
            values = ((MappingIterator<T>) objectReader.forType(type).readValues(file)).readAll();
        } catch (IOException e) {
            throw new UncheckedIOException("Can't load " + csvFile, e);
        }
    }

    @Override
    public final List<T> findAll() {
        return values;
    }

    @Override
    public abstract Optional<T> findById(String id);
}
