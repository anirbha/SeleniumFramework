Feature: Login to the application of a registered user

Scenario Outline: Login to the application
  Given as a new user I launch the website"<Testcase>"
  Then I click on the "<Link>" under Profile button
  Then I enter my email address in the email address textbox for login
  Then I enter my password in the password textbox for login
  Then I click on the "<button>" button
  When I click on the Profile button to validate the emailid
  Then the email id should be same with the given email id
  Then the name and mobile number should be same with the given details

  Examples:
    | Testcase | Link  | button |
    | Login    | Login | Login|

  Scenario Outline: Logout from the application
    Given as a new user I launch the website"<Testcase>"
    Then I click on the "<Link>" under Profile button
    Then I enter my email address in the email address textbox for login
    Then I enter my password in the password textbox for login
    Then I click on the "<button>" button
    When I click on the LogOut button
    Then I validate the Logout functionality


    Examples:
      | Testcase | Link  | button |
      | LogOut   | Login | Login  |

    @smoke
  Scenario Outline: Calling help desk functionality
    Given as a new user I launch the website"<Testcase>"
    Then I click on the "<Link>" under Profile button
    Then I enter my email address in the email address textbox for login
    Then I enter my password in the password textbox for login
    Then I click on the "<button>" button
    And I scroll down till phone icon
    When I click on the Phone icon
    Then I retrieve the text from the alert pop up and cancel it

    Examples:
      | Testcase | Link  | button |
      | HelpDesk | Login | Login  |