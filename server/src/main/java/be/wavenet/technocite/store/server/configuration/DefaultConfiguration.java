package be.wavenet.technocite.store.server.configuration;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultConfiguration {

    @Bean
    public ObjectReader objectReader() {
        CsvSchema schema = CsvSchema.emptySchema().withHeader();
        return new CsvMapper().reader().with(schema);
    }
}
