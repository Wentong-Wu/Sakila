Feature: Generate random film on button click
  Scenario Outline: Verifying button click works
    Given The page is running
    When the "<page>" is open
    And the button is clicked
    Then Display random film generated
    Examples:
    | page|
    | Home|
    | Fight |