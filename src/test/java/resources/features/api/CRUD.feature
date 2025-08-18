@CRUDtesting  @ApiTestcase1
Feature: User CRUD Operations

  @PostMethod
  Scenario Outline: Create a user
    Given I create a new user
    Then the status code should be "<Postcode>"
    Then the newly created id should have the same name and email


  Examples:
    |Postcode|
    |201|

    @GetMethod
    Scenario Outline: Retrieve user details
    Given I retrieve the user by ID
    Then the status code should be "<GetCode>"
    Then the user name and email should be same with the expected one

  Examples:
    | GetCode |
    | 200     |

    @PutMethod
    Scenario Outline: Update the user details
      Given I update the user name and email
      Then the status code should be "<PutCode>"
      Then the user name and email should be same with the expected one

      Examples:
      |PutCode|
      |200    |

    @DeleteMethod
    Scenario Outline: Delete the user details
      Given I delete the user
      Then the status code should be "<DeleteCode>"
      Then the user data should not be found

      Examples:
      |DeleteCode|
      |200       |



