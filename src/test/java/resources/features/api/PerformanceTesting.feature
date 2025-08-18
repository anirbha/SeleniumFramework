Feature: Performance Testing for Posts API

  @PerformanceTesting @ApiTestcase7
  Scenario: Measure response times under load
    Given I have performance test parameters
    When I send a high volume of GET requests
    Then the average response time should be below the maximum acceptable threshold