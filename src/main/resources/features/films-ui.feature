Feature: Start Wars - UI test cases

  Scenario: Validate sorting functionality of API
    Given I am using the star wars api
    When I get a list of all films
    And I create a list of all the movie names
    And  I sort movies by "title"
    Then I validate that the last movie is "The Phantom Menace"

  Scenario: Validate species list of specific movies
    Given I am using the star wars api
    When I get a list of all films
    And I inspect film with name "The Empire Strikes Back"
    Then I validate that "Wookie" belongs to "species"

  Scenario: Validate planet list of specific movie
    Given I am using the star wars api
    When I get a list of all films
    And I inspect film with name "The Phantom Menace"
    Then I validate that "Camino" does not belong to "planets"