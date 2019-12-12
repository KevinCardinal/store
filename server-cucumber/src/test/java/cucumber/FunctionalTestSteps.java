package cucumber;

import be.wavenet.technocite.store.server.Application;
import com.fasterxml.jackson.databind.JsonNode;
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

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
public class FunctionalTestSteps {

    private static final String URI = "/api/customers";

    private RestTemplate restTemplate;
    private JsonNode json;

    @Given("^API is accessible$")
    public void apiIsAccessible() {
        restTemplate = new RestTemplateBuilder()
                .rootUri("http://localhost:8081")
                .build();
    }

    @When("I ask to get all customers on API")
    public void iAskToGetAllCustomersOnAPI() {
        json = restTemplate.exchange(URI, HttpMethod.GET, HttpEntity.EMPTY, JsonNode.class).getBody();
    }

    @Then("I get a valid JSON array")
    public void iGetAValidJSONArray() {
        assertThat(json.isArray()).isTrue();
    }

    @And("the JSON array has only {int} element")
    public void theJSONArrayHasOnlyElement(int size) {
        assertThat(json.size()).isEqualTo(size);
    }

    @And("the element has {string} as name and {double} as balance")
    public void theElementHasAsNameAndAsBalance(String name, double balance) {
        JsonNode element = json.get(0);

        assertThat(element.has("name")).isTrue();
        assertThat(element.get("name").isTextual()).isTrue();
        assertThat(element.get("name").asText()).isEqualTo(name);

        assertThat(element.has("balance")).isTrue();
        assertThat(element.get("balance").isDouble()).isTrue();
        assertThat(element.get("balance").asDouble()).isEqualTo(balance);
    }
}
