Feature: Login with excel data

  Scenario Outline: User login with excel data
    Given user should navigated to login page
    When user enter username and password from excel as "<data>"
    And user click on login button
    Then user should navigated to My Account page if data is valid "<data>"

    Examples: 
      | data |
      |    1 |
      |    2 |
      |    3 |
