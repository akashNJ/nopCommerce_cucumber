Feature: Login

  @sanity
  Scenario: user login
    Given user should navigated to login page
    When user enter valid username and password
    And user click on login button
    Then user should navigated to My Account page
