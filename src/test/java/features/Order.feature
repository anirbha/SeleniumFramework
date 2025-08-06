Feature: Order item

  Scenario Outline: Order item functionality till payment page
    Given as a new user I launch the website"<Testcase>"
    Then I click on the "<Link>" under Profile button
    Then I enter my email address in the email address textbox for login
    Then I enter my password in the password textbox for login
    Then I click on the "<button>" button
    Then I navigate to ‘Crockery units’
    When I select all the filters
    Then all the filters should be present
    And I click on any three items from
    And I add them to cart





    Examples:
      | Testcase | Link  | button |
      | Order    | Login | Login  |