Feature: Data-Driven User Creation from Excel

  @DataDrivenTesting  @ApiTestcase3
  Scenario: Create multiple users from Excel data
    Given I read user data from excel
    When I create users using API endpoint
    Then all users should be created successfully
