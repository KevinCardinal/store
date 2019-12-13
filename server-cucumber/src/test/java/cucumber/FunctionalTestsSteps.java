package cucumber;

import be.wavenet.technocite.store.server.Application;
import com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
public class FunctionalTestsSteps {

    private static final String CUSTOMERS_URI = "/api/customers";
    private static final String LOCALITIES_URI = "/api/localities";

    private RestTemplate restTemplate;
    private JsonNode json;

    @Given("API is accessible")
    public void apiIsAccessible() {
        restTemplate = new RestTemplateBuilder()
                .rootUri("http://localhost:8081")
                .build();
    }

    @When("I request localities to API")
    public void iRequestLocalitiesToAPI() {
        json = restTemplate
                .exchange(LOCALITIES_URI, HttpMethod.GET, HttpEntity.EMPTY, JsonNode.class)
                .getBody();
    }

    @When("I request customers on API")
    public void iAskToGetAllCustomersOnAPI() {
        json = restTemplate
                .exchange(CUSTOMERS_URI, HttpMethod.GET, HttpEntity.EMPTY, JsonNode.class)
                .getBody();
    }

    @Then("I get a city list of size {int}")
    public void iGetACityListOfSize(int size) {
        assertThat(json.isArray()).isTrue();
        assertThat(json.size()).isEqualTo(size);
        json.iterator().forEachRemaining(node -> assertThat(node.isTextual()).isTrue());
    }

    @DataTableType
    public String city(Map<String, String> entry) {
        return entry.get("city");
    }

    @And("city list contains the following cities:")
    public void cityListContainsTheFollowingCities(List<String> cities) {
        List<String> jsonCities = new ArrayList<>();
        json.forEach(node -> jsonCities.add(node.asText()));
        assertThat(jsonCities).containsAll(cities);
    }
}
