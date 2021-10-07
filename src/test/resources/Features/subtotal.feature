@subtotal @feature
Feature: Calculate subtotal for every product

  @products @scenario
  Scenario: Validate subtotal for each item in the cart

    Given I navigate to shop page
    When I buy below products
      | Quantity | Product         | Css_index |
      | 2        | Stuffed Frog    | 2         |
      | 5        | Fluffy Bunny    | 4         |
      | 3        | Valentine Bear  | 7         |
    And I navigate to the cart page
    Then I verify the subtotal for each product is correct
      | Quantity | Product         | Unit_Price  | Xpath_index |
      | 2        | Stuffed Frog    | 10.99       | 1           |
      | 5        | Fluffy Bunny    | 9.99        | 2           |
      | 3        | Valentine Bear  | 14.99       | 3           |