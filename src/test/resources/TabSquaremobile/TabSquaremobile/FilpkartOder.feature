Feature: Flipkart Order

  Scenario: Verify checkout process
    Given I am on the login page
    And I click on "Log in"
    And I enter below detail
    | MobileNumber |
    | 9408070188   |
    And I am on the Home page
    And I enter text "Iphone 12" in searchBox