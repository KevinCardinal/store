Feature: Get localities

  Scenario: API only knows 3 cities
    Given API is accessible
    When I request localities to API
    Then I get a city list of size 3
    And city list contains the following cities:
      | city    |
      | Leuze   |
      | Mons    |
      | Tournai |