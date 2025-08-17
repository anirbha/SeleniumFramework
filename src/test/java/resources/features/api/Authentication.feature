Feature: Authentication Simulation for POST /posts

  @Authentication   @ApiTestcase2
  Scenario: Simulate authentication and validate POST /posts access control
    Given I set the Authorization header
    When I send a POST request
    Then the status code should be 201
    Then the newly created id should have the same title and body



