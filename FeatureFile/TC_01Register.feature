Feature: Registration

  @sanity
  Scenario: User registration
    Given user is navigated to registration page
    When user enters registration details
      | firstName | admin |
      | lastName  | admin |
    And user click on register button
    Then registration successful message should be displayed
