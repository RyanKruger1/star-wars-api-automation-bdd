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

import static io.restassured.RestAssured.given;

public class Films extends BaseTest {

    static ValidatableResponse filmsResponse;
    static JSONObject movieUnderTest;

    @When("I get a list of all films")
    public void retrieve_all_films() {
        filmsResponse =
                RestAssured.given().
                        log().all().
                        get("films/").
                        then().
                        log().all();
    }

    @Then("I validate the amount of returned films amounts to {int}")
    public void validate_the_amount_of_returned_films(int filmsCount) {
        Assert.assertEquals(filmsCount, filmsResponse.
                extract().
                body().
                jsonPath().getList("results").size());
    }

    @Then("I inspect film {int}")
    public void i_inspect_movie_x(int filmsCount) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(
                filmsResponse.
                        extract().
                        response().
                        getBody().
                        asString());

        JSONArray results = (JSONArray) jsonObject.get("results");
        movieUnderTest = (JSONObject) results.get(filmsCount - 1);
    }

    @Then("I validate that the movie is directed by {string}")
    public void i_validate_that_movie_is_directed_by_director(String director) {
        Assert.assertEquals((String) movieUnderTest.get("director"), director);
    }

    @Then("I validate that the movie is not produced by {string}")
    public void i_validate_that_the_is_not_produced_by_producer(String producer) {
        Assert.assertNotEquals((String) movieUnderTest.get("producer"), producer);
    }
}