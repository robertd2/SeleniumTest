Feature: SearchBox search

  Background:
    Given User is on Main Page

  Scenario Outline: Displaying search suggestion under SearchBox
    When User enter product "<product>" into SearchBox
    Then Appears suggestion under Searchbox with "<product>"
    Examples:
    | product |
    | dress   |
    | blouse  |

  Scenario: Open first element from suggestion under SearchBox
    When User enter product "dress" into SearchBox
    And Appears suggestion under Searchbox with "dress"
    And Open first element from suggestion box
    Then Opens page with "dress"