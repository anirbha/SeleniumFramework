Feature: User CRUD Operations

  @PostMethod
  Scenario Outline: Create a user
    Given I create a new user
    Then the status code should be "<Postcode>"
    Then the newly created id should have the same name and email


  Examples:
    |Postcode|
    |201|


