package stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.junit.Assert.assertEquals;

public class APIResponse extends Utils {
    RequestSpecification res;
    ResponseSpecification resspec;
    Response response;
    TestDataBuild data = new TestDataBuild();
    static String place_id;


//Hello
    @Given("Add Place Payload {string}  {string} {string}")
    public void add_place_payload(String name, String language, String address) throws IOException {
        res =given().spec(requestSpecification())
                .body(data.addPlacePayLoad(name, language, address));
    }

    @When("calls AddPlaceAPI with POST http request")
    public Response calls_add_place_api_with_post_http_request() {
        response = res.post("/maps/api/place/add/json");
        place_id = getJsonPath(response, "place_id");
        System.out.println(place_id);
        System.out.println(response.asString());
        return response;
    }

    @Then("the API call got success with status code")
    public void the_api_call_got_success_with_status_code() {
        assertEquals(response.getStatusCode(), 200);
    }
    @Given("Delete API {string}")
    public void delete_api(String resource) throws IOException {
        res =given().spec(requestSpecification())
                .body(data.deletePlacePayload(place_id));
     APIResources resource1=   APIResources.valueOf(resource);
     res.when().post(resource1.getResource());

    }
}
