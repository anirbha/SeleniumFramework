Feature: Pagination Testing for Posts API

  @PaginationTesting  @ApiTestcase10
  Scenario: Validate pagination on posts endpoint

    Given I request page number with limit
    Then I should receive posts with that limit
    When I newly request page number with limit
    And the posts on pages should not overlap
