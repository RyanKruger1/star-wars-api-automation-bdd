Feature: Star Wars API - films

  Scenario: Validate the amount of films in the star wars franchise being returned
    Given I am using the star wars api
    When I get a list of all films
    Then I validate the amount of returned films amounts to 6

  Scenario: Validate data being returned by ensuring the directors that directed is correct
    Given I am using the star wars api
    When I get a list of all films
    Then I inspect film 3
    And I validate that the movie is directed by "Richard Marquand"

  Scenario Outline: Validate data being returned by ensuring the producers listed is correct
    Given I am using the star wars api
    When I get a list of all films
    Then I inspect film 3
    And I validate that the movie is not produced by "<producer>"

    Examples:
      | producer |
      | Gary Kurtz|
      | Rick McCallum |
      | Ryan Kruger|