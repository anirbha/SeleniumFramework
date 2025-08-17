Feature: Error Handling and Validation

  @ErrorValidation  @ApiTestcase6
  Scenario: Validate error response status code
    Given I have an invalid endpoint
    When I send GET request with that endpoint
    Then the we should receive bad request status code
    And response should be null
