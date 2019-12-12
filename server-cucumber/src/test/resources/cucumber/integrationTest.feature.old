Feature: Integration Test - API Controller

  Background: correct content in customer CSV file
    Given the only customer available "Jack" with 0.0 as balance

  Scenario: only 1 customer
    Given controller linked to customer csv repository
    When I ask to get all customers on controller
    Then I get a customer dto list with size 1
    And the customer's name is "Jack" and his sold is 0.0
