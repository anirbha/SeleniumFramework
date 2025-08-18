Feature: Functionality of Comparing items

  Background:
    Given as a user I launch the website
    And I click on the Login Link under Profile button
    And I enter my email address in the email address textbox for login
    And I enter my password in the password textbox for login
    When I click on the Login button

    @CompareSameItems   @Testcase5
    Scenario: Compare 2 items and store it in excel
      And I enter beds in searchbox
      Then beds should be appeared in the search results
      And I add two products to compare
      When I navigated to the compare page
      Then the same two items should be present
      Then I retrieve all the specifications values into excel

      @CompareDiffItems   @Testcase6
    Scenario: Compare items of different collections
      And I enter Recliners in the searchbox
      Then Recliners should be appeared in the searchbox
      And I add one product to compare
      And I add Mattress in the searchbox
      When I try to add one product to compare
      Then I should receive message ‘Can’t compare recliners with mattresses’

