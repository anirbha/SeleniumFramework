Feature: Register a User Account functionality

  Scenario Outline: Successful Registration with valid credentials
    Given as a new user I launch the website"<Testcase>"
    Then I click on the "<Link>" under Profile button
    Then I enter my email address in the email address textbox
    Then I enter my password in the password textbox
    Then I click on the "<Button>" button
    When I click on the Profile button to validate the emailid
    Then the email id should be same with the given email id
    Then I click on the Edit button
    And I enter my name in the name textbox
    And I enter my mobile number in the mobile number textbox
    When I click on the Confirm button
    Then the name and mobile number should be same with the given details

    Examples:
      | Testcase     | Link   |  | Button |
      | RegisterUser | SignUp |  | SignUp |


