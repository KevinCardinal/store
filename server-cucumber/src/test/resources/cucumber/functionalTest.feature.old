Feature: Functional Test - API - Get customers

  Background: API is ready
    Given API is accessible

  Scenario: only 1 customer
    Given the only customer available "Jack" with 0.0 as balance
    When I ask to get all customers on API
    Then I get a valid JSON array
    And the JSON array has only 1 element
    And the element has "Jack" as name and 0.0 as balance
