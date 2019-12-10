Feature: Unit Test - Customer Repository

  Scenario: get 1 customer when only 1 customer is available
    Given the only customer available "Jack"
    When I ask to get all customers
    Then I only get "Jack"

  Scenario: don't get any customers when no customer is available
    Given no customer available
    When I ask to get all customers
    Then I don't get any customers
