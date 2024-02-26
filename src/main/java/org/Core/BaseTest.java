package org.Core;

import io.cucumber.testng.CucumberOptions;
import io.restassured.RestAssured;
import org.json.simple.JSONArray;


@CucumberOptions(
        features = "src/test/resources/ui-features",
        glue = {"fr.tlasnier.cucumber"},
        plugin = {"pretty", "html:target/cucumber-reports"}
)
public class BaseTest {
    public void switchAdaptors(String adaptor) {
        RestAssured.baseURI = adaptor;
        RestAssured.basePath = "";
    }

    public boolean validatePresenceOfItemInURLList(JSONArray specifiedList, String item) {
        for (int i = 0; i < specifiedList.size(); i++) {

            switchAdaptors((String) specifiedList.get(i));
            String nameUnderTest = RestAssured.
                    get("").
                    getBody().
                    jsonPath().
                    getString("name");

            if (nameUnderTest.equals(item)) {
                return true;
            }
        }
        return false;
    }
}