package StarWarsTestSuite;

import Core.BaseTest;
import io.cucumber.java.en.Given;
import io.restassured.RestAssured;

public class StartWarsBaseTest extends BaseTest {

    @Given("I am using the star wars api")
    public void setupStarWarsEnvironment() {
        RestAssured.basePath = "api/";
        RestAssured.baseURI = "https://swapi.dev/";
    }
}
