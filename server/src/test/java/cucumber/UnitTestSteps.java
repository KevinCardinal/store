package cucumber;

import be.wavenet.technocite.store.server.model.Customer;
import be.wavenet.technocite.store.server.repository.impl.CustomerRepositoryImpl;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UnitTestSteps {

    private CustomerRepositoryImpl customerRepositoryImpl;
    private Collection<Customer> values;

    @SuppressWarnings("unchecked")
    @Given("the only customer available {string}")
    public void theOnlyCustomerAvailable(String name) throws IOException {
        Customer customer = new Customer("0", name, 0);

        ObjectReader objectReader = mock(ObjectReader.class);
        ResourceLoader resourceLoader = mock(ResourceLoader.class);

        Resource resource = mock(Resource.class);
        File file = new File("");
        MappingIterator<Object> mappingIterator = mock(MappingIterator.class);

        when(resourceLoader.getResource(any(String.class))).thenReturn(resource);
        when(resource.getFile()).thenReturn(file);
        when(objectReader.forType(any(Class.class))).thenReturn(objectReader);
        when(objectReader.readValues(any(File.class))).thenReturn(mappingIterator);
        when(mappingIterator.readAll()).thenReturn(singletonList(customer));

        customerRepositoryImpl = new CustomerRepositoryImpl(objectReader, resourceLoader);
    }

    @When("I ask to get all customers")
    public void iAskToGetAllCustomers() {
        values = customerRepositoryImpl.findAll();
    }

    @Then("I only get {string}")
    public void iOnlyGet(String name) {
        assertThat(values).hasSize(1);
        assertThat(values).allMatch(customer -> customer.getName().equals(name));
    }

    @SuppressWarnings("unchecked")
    @Given("no customer available")
    public void noCustomerAvailable() throws IOException {
        ObjectReader objectReader = mock(ObjectReader.class);
        ResourceLoader resourceLoader = mock(ResourceLoader.class);

        Resource resource = mock(Resource.class);
        File file = new File("");
        MappingIterator<Object> mappingIterator = mock(MappingIterator.class);

        when(resourceLoader.getResource(any(String.class))).thenReturn(resource);
        when(resource.getFile()).thenReturn(file);
        when(objectReader.forType(any(Class.class))).thenReturn(objectReader);
        when(objectReader.readValues(any(File.class))).thenReturn(mappingIterator);
        when(mappingIterator.readAll()).thenReturn(emptyList());

        customerRepositoryImpl = new CustomerRepositoryImpl(objectReader, resourceLoader);
    }


    @Then("I don't get any customers")
    public void iDonTGetAnyCustomers() {
        assertThat(values).isEmpty();
    }
}
