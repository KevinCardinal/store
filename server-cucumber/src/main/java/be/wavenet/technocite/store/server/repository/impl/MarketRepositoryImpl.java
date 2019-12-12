package be.wavenet.technocite.store.server.repository.impl;

import be.wavenet.technocite.store.server.configuration.ResourceFiles;
import be.wavenet.technocite.store.server.model.Market;
import be.wavenet.technocite.store.server.repository.MarketRepository;
import com.fasterxml.jackson.databind.ObjectReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MarketRepositoryImpl extends AbstractRepository<Market> implements MarketRepository {


    public MarketRepositoryImpl(ObjectReader objectReader,
                                ResourceLoader resourceLoader,
                                @Value(ResourceFiles.MARKET_CSV_FILE) String marketCsvFile) {
        super(objectReader, resourceLoader, marketCsvFile, Market.class);
    }

    @Override
    public Optional<Market> findById(String id) {
        return findAll().stream()
                .filter(market -> market.getId().equals(id))
                .findAny();
    }
}
