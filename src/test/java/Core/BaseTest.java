package Core;

import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"StarWarsTestSuite"},
        plugin = {"pretty", "html:target/cucumber-reports"}
)
public class BaseTest {}