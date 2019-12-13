Feature: Unit Test - Customer Repository - Find all customers

  Scenario: 1. no customer in repository
    Given the repository containing no customer
    When I want to find all customers
    Then I get an empty list

  Scenario: 2. only customer Jack in repository
    Given the repository only containing customer "Jack"
    When I want to find all customers
    Then I get a list of size 1
    And the unique element in list is the customer "Jack"

  Scenario: 3. only customer Jack registered in 2012-12-21 in repository
    Given the repository only containing customer "Jack" registered in 2012-12-21
    When I want to find all customers
    Then I get a list of size 1
    And the unique element in list is the customer "Jack" registered in 2012-12-21

  Scenario Outline: 4. only 1 customer in repository
    Given the repository only containing customer "<name>" registered in <date>
    When I want to find all customers
    Then I get a list of size 1
    And the unique element in list is the customer "<name>" registered in <date>

    Examples: Men
      | name | date       |
      | Marc | 2000-01-01 |
      | Jean | 2012-12-21 |

    Examples: Women
      | name  | date       |
      | Julie | 2000-01-01 |
      | Laura | 2012-12-21 |

  Scenario: 5. multiple customers in repository, especially Jacques
    Given the following repository:
      | name    | date       |
      | Georges | 2017-01-07 |
      | Jacques | 2018-01-08 |
    When I want to find all customers
    Then I get a list of size 2
    And the list contains the customer "Jacques"

  Scenario: 6. multiples customers in repository, especially Georges
    Given the following customer list:
    """customer_list
    Georges
    Jacques
    """
    When I want to find all customers
    Then I get a list of size 2
    And the list contains the customer "Georges"
