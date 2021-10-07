@subtotal @feature
Feature: Calculate subtotal for every product

  @products @scenario
  Scenario: Validate subtotal for each item in the cart

    Given I navigate to shop page
    When I buy below products
      | Quantity | Product         |
      | 2        | Stuffed Frog    |
      | 5        | Fluffy Bunny    |
      | 3        | Valentine Bear  |
    And I navigate to the cart page
    Then I verify the subtotal for each product is correct
      | Quantity | Product         | price  |
      | 2        | Stuffed Frog    | 10.99  |
      | 5        | Fluffy Bunny    | 9.99   |
      | 3        | Valentine Bear  | 14.99  |