package org.Core;

import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"fr.tlasnier.cucumber"},
        plugin = {"pretty", "html:target/cucumber-reports"}
)
public class BaseTest {}