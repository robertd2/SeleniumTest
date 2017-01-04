Feature: SearchBox search

  Scenario: Displaying search suggestion under SearchBox
    Given User is on Main Page
    When User enter product "dress" into SearchBox
    Then Appears suggestion under Searchbox with "dress"

  Scenario: Open first element from suggestion under SearchBox
    Given User is on Main Page
    When User enter product "dress" into SearchBox
    And Appears suggestion under Searchbox with "dress"
    And Open first element from suggestion box
    Then Opens page with "dress"