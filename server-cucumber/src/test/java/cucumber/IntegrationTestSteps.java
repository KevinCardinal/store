package cucumber;

import be.wavenet.technocite.store.server.configuration.DefaultConfiguration;
import be.wavenet.technocite.store.server.controller.ApiController;
import be.wavenet.technocite.store.server.controller.ApiControllerImpl;
import be.wavenet.technocite.store.server.converter.CustomerConverter;
import be.wavenet.technocite.store.server.dto.CustomerDto;
import be.wavenet.technocite.store.server.repository.CustomerRepository;
import be.wavenet.technocite.store.server.repository.impl.CustomerRepositoryImpl;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
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

    private ApiController apiController;
    private List<CustomerDto> customers;

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

    @Given("controller linked to customer csv repository")
    public void controllerLinkedToCustomerCsvRepository() {
        DefaultConfiguration configuration = new DefaultConfiguration();
        CustomerRepository customerRepository = new CustomerRepositoryImpl(configuration.objectReader(),
                new DefaultResourceLoader(), CUSTOMER_CSV_FILE);
        CustomerConverter customerConverter = new CustomerConverter();

        apiController = new ApiControllerImpl(customerRepository, customerConverter);
    }

    @When("I ask to get all customers on controller")
    public void iAskToGetAllCustomersOnController() {
        customers = apiController.getCustomers();
    }

    @Then("I get a customer dto list with size {int}")
    public void iGetACustomerDtoListWithSize(int size) {
        assertThat(customers).hasSize(size);
    }

    @And("the customer's name is {string} and his sold is {double}")
    public void theCustomerSNameIsAndHisSoldIs(String name, double sold) {
        CustomerDto dto = customers.get(0);
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getBalance()).isEqualTo(sold);
    }
}
