package org.StarWarsTestSuite;

import io.restassured.RestAssured;
import org.Core.BaseTest;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.ValidatableResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class Species extends BaseTest {

    static ValidatableResponse speciesResponse;
    static JSONObject movieUnderTest;
    static List<String> names;

    @When("I get a list of all species")
    public void retrieve_all_films() {
        speciesResponse =
                RestAssured.given().
                        log().all().
                        get("species/").
                        then().
                        log().all();
    }

    @Then("I create a list of all species names")
    public void i_create_a_list_of_all_species_names() throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(
                speciesResponse.
                        extract().
                        response().
                        getBody().
                        asString());

        JSONArray results = (JSONArray) jsonObject.get("results");
        names = new ArrayList<>();

        for (Object obj : results) {
            JSONObject tmpObject = (JSONObject) obj;
            String name = (String) tmpObject.get("name");
            names.add(name);
            System.out.println(name);
        }
    }

    @Then("I validate that species list does not contain {string}")
    public void  i_validate_species_list(String string){
        Assert.assertFalse(names.contains(string));
    }
}