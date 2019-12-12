package cucumber;

import io.cucumber.java.en.Given;
import org.assertj.core.util.Strings;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class IntegrationTestSteps {

    private static final String CUSTOMER_CSV_FILE = "classpath:csv/customer.csv";

    @Given("the only customer available {string} with {double} as balance")
    public void theOnlyCustomerAvailableWithAsBalance(String name, double balance) throws IOException {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Stream<String> lines = Files.lines(Paths.get(resourceLoader.getResource(CUSTOMER_CSV_FILE).getURI()));

        List<List<String>> rows = lines.filter(line -> !Strings.isNullOrEmpty(line))
                .map(line -> Arrays.stream(line.split(",")).map(String::trim).collect(toList()))
                .collect(toList());
        lines.close();

        assertThat(rows.size()).isEqualTo(2);
        assertThat(rows.get(0).size()).isEqualTo(4);
        assertThat(rows.get(1).size()).isEqualTo(4);

        assertThat(rows.get(0).get(1)).isEqualTo("name");
        assertThat(rows.get(1).get(1)).isEqualTo(name);

        assertThat(rows.get(0).get(2)).isEqualTo("balance");
        assertThat(Double.parseDouble(rows.get(1).get(2))).isEqualTo(balance);
    }
}
