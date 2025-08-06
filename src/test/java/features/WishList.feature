Feature: Validation of wishlist functionality

  Scenario Outline: Adding items to wishlist and validation
    Given as a new user I launch the website"<Testcase>"
    Then I click on the "<Link>" under Profile button
    Then I enter my email address in the email address textbox for login
    Then I enter my password in the password textbox for login
    Then I click on the "<Button>" button
    When I enter the searchitem in searchbox
    Then the searchitem should be appeared in the search results
    Then I Apply all the filters
    And I add three items in the wishlist
    When I navigate wishlist page
    Then the wishlisted items should be present


    Examples:
      | Testcase | Link  | Button |
      | Wishlist | Login | Login  |