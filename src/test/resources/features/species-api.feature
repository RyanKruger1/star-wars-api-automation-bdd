Feature: Star Wars API - Features

  Scenario Outline: Validate the accuracy of the list of species
    Given I am using the star wars api
    When I get a list of all species
    Then I create a list of all species names
    And I validate that species list does not contain "<species>"

    Examples:
      | species |
      | Pokemon |
      | Digimon |
      | Pals |
      | Decepticons |
      | AutoBots |