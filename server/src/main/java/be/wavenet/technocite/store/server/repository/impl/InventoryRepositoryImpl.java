package be.wavenet.technocite.store.server.repository.impl;

import be.wavenet.technocite.store.server.configuration.ResourceFiles;
import be.wavenet.technocite.store.server.model.Inventory;
import be.wavenet.technocite.store.server.repository.InventoryRepository;
import com.fasterxml.jackson.databind.ObjectReader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class InventoryRepositoryImpl extends AbstractRepository<Inventory> implements InventoryRepository {

    public InventoryRepositoryImpl(ObjectReader objectReader, ResourceLoader resourceLoader) {
        super(objectReader, resourceLoader, ResourceFiles.INVENTORY_CSV_FILE, Inventory.class);
    }

    @Override
    public Optional<Inventory> findById(String id) {
        return findAll().stream()
                .filter(inventory -> inventory.getId().equals(id))
                .findAny();
    }
}
