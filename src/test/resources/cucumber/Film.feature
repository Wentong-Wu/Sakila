Feature: Display all films on load
  Scenario Outline: Verifying the films generated
    Given The application is running
    When the "<page>" page is open
    And the api connects
    Then display list of all films
    Examples:
      | page |
      | Film |
      | Fight |
      | Home |

