package cucumber;

import be.wavenet.technocite.store.server.model.Customer;
import be.wavenet.technocite.store.server.repository.impl.CustomerRepositoryImpl;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import io.cucumber.java.DataTableType;
import io.cucumber.java.DocStringType;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UnitTestsSteps {

    private CustomerRepositoryImpl customerRepositoryImpl;
    private List<Customer> values;

    // CustomerRepositoryImpl constructor

    @SuppressWarnings("unchecked")
    private CustomerRepositoryImpl createCustomerRepositoryImpl(List<Customer> customers) {
        try {
            List<Object> objects = customers.stream()
                    .map(customer -> (Object) customer)
                    .collect(toList());

            ObjectReader objectReader = mock(ObjectReader.class);
            ResourceLoader resourceLoader = mock(ResourceLoader.class);

            Resource resource = mock(Resource.class);
            InputStream file = new ByteArrayInputStream(new byte[0]);
            MappingIterator<Object> mappingIterator = mock(MappingIterator.class);

            when(resourceLoader.getResource(any(String.class))).thenReturn(resource);
            when(resource.getInputStream()).thenReturn(file);
            when(objectReader.forType(any(Class.class))).thenReturn(objectReader);
            when(objectReader.readValues(any(InputStream.class))).thenReturn(mappingIterator);
            when(mappingIterator.readAll()).thenReturn(objects);

            return new CustomerRepositoryImpl(objectReader, resourceLoader, "");
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    // Scenario 1

    @Given("the repository containing no customer")
    public void theRepositoryContainingNoCustomer() {
        customerRepositoryImpl = createCustomerRepositoryImpl(emptyList());
    }

    @When("I want to find all customers")
    public void iWantToFindAllCustomers() {
        values = customerRepositoryImpl.findAll();
    }

    @Then("I get an empty list")
    public void iGetAnEmptyList() {
        assertThat(values).isEmpty();
    }

    // Scenario 2

    @Given("the repository only containing customer {string}")
    public void theRepositoryOnlyContainingCustomer(String name) {
        customerRepositoryImpl = createCustomerRepositoryImpl(singletonList(new Customer("0", name, 0.0, LocalDate.MIN)));
    }

    @Then("I get a list of size {int}")
    public void iGetAListOfSize(int size) {
        assertThat(values).hasSize(size);
    }

    @And("the unique element in list is the customer {string}")
    public void theUniqueElementInListIsTheCustomer(String name) {
        assertThat(values.get(0).getName()).isEqualTo(name);
    }

    // Scenario 3

    @ParameterType(name = "date", value = "([0-9]{4})-([0-9]{2})-([0-9]{2})")
    public LocalDate date(String year, String month, String day) {
        return LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
    }

    // Scenario 3 & 4

    @Given("the repository only containing customer {string} registered in {date}")
    public void theRepositoryOnlyContainingCustomerRegisteredIn(String name, LocalDate date) {
        customerRepositoryImpl = createCustomerRepositoryImpl(singletonList(new Customer("0", name, 0.0, date)));
    }

    @Then("the unique element in list is the customer {string} registered in {date}")
    public void theUniqueElementInListIsTheCustomerRegisteredIn(String name, LocalDate date) {
        assertThat(values.get(0).getName()).isEqualTo(name);
        assertThat(values.get(0).getRegistration()).isEqualTo(date);
    }

    // Scenario 5

    @DataTableType
    public Customer customer(Map<String, String> entry) {
        return new Customer(UUID.randomUUID().toString(), entry.get("name"), 0.0, LocalDate.parse(entry.get("date")));
    }

    @Given("the following repository:")
    public void theFollowingRepository(List<Customer> customers) {
        customerRepositoryImpl = createCustomerRepositoryImpl(customers);
    }

    @And("the list contains the customer {string}")
    public void theListContainsTheCustomer(String name) {
        assertThat(values).anyMatch(customer -> customer.getName().equals(name));
    }

    // Scenario 6

    @DocStringType(contentType = "customer_list")
    public List<Customer> customerList(String docString) {
        return Stream.of(docString.split("\\n"))
                .map(name -> new Customer(UUID.randomUUID().toString(), name, 0.0, LocalDate.MIN))
                .collect(toList());
    }

    @Given("the following customer list:")
    public void theFollowingCustomerList(List<Customer> customers) {
        customerRepositoryImpl = createCustomerRepositoryImpl(customers);
    }
}
