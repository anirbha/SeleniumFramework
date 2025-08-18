Feature: Header and Cookie Testing for Comments API

  @HeaderAndCookieTesting @ApiTestcase5
  Scenario: Send request with custom header and cookie, validate response
    Given I set a custom header with value
    And I set a cookie with value
    When I send a GET request to
    Then the response status code should be 200 for header cookie
    Then the response should contain at least one comment
    Then the response header "Content-Type" should be "application/json; charset=utf-8"
