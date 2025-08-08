Feature: Validation of wishlist functionality

  Background:
    Given as a user I launch the website
    And I click on the Login Link under Profile button
    And I enter my email address in the email address textbox for login
    And I enter my password in the password textbox for login
    When I click on the Login button

    @Wishlist
  Scenario: Adding items to wishlist and validation
    When I enter the searchitem in searchbox
    Then the searchitem should be appeared in the search results
    And I Apply all the filters
    And I add three items in the wishlist
    When I navigate wishlist page
    Then the wishlisted items should be present


