Feature: Order item functionality till payment page

  Background:
    Given as a user I launch the website
    And I click on the Login Link under Profile button
    And I enter my email address in the email address textbox for login
    And I enter my password in the password textbox for login
    When I click on the Login button

  @Order    @Testcase4
  Scenario: Order item functionality till payment page
    Then I searched for the ‘Crockery units’
    When I select all the filters
    Then all the filters should be present
    And I click on any three items
    When I checkout from the cart
    Then I add address into the shipping address
    Then I click on Save and Continue button to navigate to payment page
    When I verify the items and the prices it should match with the earlier


