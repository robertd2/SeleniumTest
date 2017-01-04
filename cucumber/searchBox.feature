Feature: SearchBox search

  Scenario: Displaying search suggestion under SearchBox
    Given User is on Main Page
    When User enter product "dress" into SearchBox
    Then Appears suggestion under Searchbox with "dress"