Feature: Chained Requests

  @ChainRequests    @ApiTestcase9
  Scenario: Create user and post, validate association
    Given I create a new user in users endpoint
    When I create a post for that user
    Then the post should be associated with the user