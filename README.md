# Star Wars API automation with BDD

This repository was created to showcase the author's test automation skills.

This repo consists of a Java framework using RestAssured.

## Requirements:

- Java(JDK 11 or Higher)

## System under test

The SUT is [Star Wars API](https://swapi.dev/documentation).

## Java Automation Framework

### Prerequisites

This automation framework only uses an RestAssured in conjuction with TestNg & Cucumber

### Running

The java framework uses gradle as the main building tool.

On a Windows operating system, navigate to the root of the project in your file system via command line.
Then run :
`gradlew cucumber clean test`

On Mac machine navigate to the repository via terminal. Run `chmod +x gradlew`, this will allow the gradlew file to become executable. To run the solution run command:
`./gradlew cucumber clean test`

### Reporting

A test report is generated after each run in the /test-reports/ folder in the base repository of the repo.
