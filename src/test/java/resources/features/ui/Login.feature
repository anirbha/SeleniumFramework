Feature: Login and Logout functionality a registered user

 Background:
    Given as a user I launch the website
    And I click on the Login Link under Profile button
    And I enter my email address in the email address textbox for login
    And I enter my password in the password textbox for login
    When I click on the Login button

  @Login
  Scenario: Login to the application
    When I click on the Profile button to validate the emailid
    Then the email id should be same with the given email id
    Then the name and mobile number should be same with the given details

  @LogOut
  Scenario: Logout from the application
    When I click on the LogOut button
    Then I validate the Logout functionality

  @HelpDesk
  Scenario: Calling help desk functionality
    And I scroll down till phone icon
    When I click on the Phone icon
    Then I retrieve the text from the alert pop up and cancel it
