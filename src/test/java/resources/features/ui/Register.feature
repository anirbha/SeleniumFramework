Feature: Register a User Account functionality

  @RegisterUser   @Priority
  Scenario: Successful Registration with valid credentials
    Given as a user I launch the website
    And I click on the SignUp Link under Profile button
    And I enter my email address in the email address textbox
    And I enter my password in the password textbox
    And I click on the SignUp button
    When I click on the Profile button to validate the emailid
    Then the email id should be same with the given email id
    When I click on the Edit button
    And I enter my name in the name textbox
    And I enter my mobile number in the mobile number textbox
    When I click on the Confirm button
    Then the name and mobile number should be same with the given details




