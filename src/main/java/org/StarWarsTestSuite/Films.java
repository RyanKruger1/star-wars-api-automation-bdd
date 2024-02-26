package org.StarWarsTestSuite;

import com.beust.ah.A;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.Core.BaseTest;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.ValidatableResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;

public class Films extends BaseTest {

    static ValidatableResponse filmsResponse;
    static JSONObject movieUnderTest;
    static List<JSONObject> movies = new ArrayList<>();
    static List<String> filteredList = new ArrayList<>();

    @When("I get a list of all films")
    public void retrieve_all_films() {
        filmsResponse =
                RestAssured.given().
                        log().all().
                        get("films/").
                        then().log().all();
    }

    @Then("I create a list of all the movie names")
    public void create_a_list_of_movies() throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(
                filmsResponse.
                        extract().
                        response().
                        getBody().
                        asString());

        JSONArray results = (JSONArray) jsonObject.get("results");
        for (Object object : results) {
            movies.add((JSONObject) object);
        }
    }

    @Then("I sort movies by {string}")
    public void sort_movies_by_title(String criteria) {
        filteredList = new ArrayList<>();
        for (JSONObject currentMovie : movies) {
            filteredList.add(currentMovie.get(criteria).toString());
        }
        Collections.sort(filteredList);
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

    @Then("I inspect film with name {string}")
    public void i_inspect_movie_x(String name) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(
                filmsResponse.
                        extract().
                        response().
                        getBody().
                        asString());

        JSONArray results = (JSONArray) jsonObject.get("results");

        for (Object object : results) {
            JSONObject tmp = (JSONObject) object;
            if (tmp.get("title").equals(name)) {
                movieUnderTest = tmp;
                break;
            }
        }
    }

    @Then("I validate that the movie is directed by {string}")
    public void i_validate_that_movie_is_directed_by_director(String director) {
        Assert.assertEquals((String) movieUnderTest.get("director"), director);
    }

    @Then("I validate that the movie is not produced by {string}")
    public void i_validate_that_the_is_not_produced_by_producer(String producer) {
        Assert.assertNotEquals((String) movieUnderTest.get("producer"), producer);
    }

    @Then("I validate that the last movie is {string}")
    public void i_validate_that_the_last_movie_is(String movieName) {
        Assert.assertEquals(movieName, filteredList.get(filteredList.size() - 1));
    }

    @Then("I validate that {string} belongs to {string}")
    public void i_validate_that_belongs_to(String item, String list) {
        JSONArray specifiedList = (JSONArray) movieUnderTest.get(list);
        Assert.assertTrue(validatePresenceOfItemInURLList(specifiedList, item));
    }

    @Then("I validate that {string} does not belong to {string}")
    public void i_validate_that_does_not_belongs_to(String item, String list) {
        JSONArray specifiedList = (JSONArray) movieUnderTest.get(list);
        Assert.assertFalse(validatePresenceOfItemInURLList(specifiedList, item));
    }
}