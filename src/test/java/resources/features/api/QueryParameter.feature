@QueryParamTesting  @ApiTestcase4
Feature: Validate GET requests with various query parameters

  @queryparam1
  Scenario: Validate get request with query parameter as id
    Given I send a GET request with id query parameter
    Then the response status code should be 200
    Then the response should contain the query parameter id

  @queryparam2
  Scenario: Validate get request with query parameter as userid
    Given I send a GET request with userId query parameter
    Then the response status code should be 200
    Then the response should contain the query parameter userId

  @queryparam3
  Scenario: Validate get request with query parameter as both id and userid
    Given I send a GET request with id and userId query parameter
    Then the response status code should be 200
    Then the response should contain the query parameter id and userId





