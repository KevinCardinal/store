package be.wavenet.technocite.store.server.configuration;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
public class DefaultConfiguration {

    @Bean
    public ObjectReader objectReader() {
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ISO_LOCAL_DATE));
        CsvSchema schema = CsvSchema.emptySchema().withHeader();
        return new CsvMapper().registerModule(simpleModule).reader().with(schema);
    }
}
