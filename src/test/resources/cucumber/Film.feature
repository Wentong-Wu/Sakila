Feature: Display films on load
  Scenario Outline: Verifying the films generated
    Given The application is running
    When the "<page>" page is open
    And the api connects
    Then display list of films
    Examples:
      | page |
      | Home |
      | Fight |
      | Film |

