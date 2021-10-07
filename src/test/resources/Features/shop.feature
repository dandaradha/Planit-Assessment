@shop @feature
Feature: Add items to cart

  @cart @scenario
  Scenario: Validate items in the cart

    Given I navigate from home page to shop page
    When I click buy button
      | Quantity | Product      | css_index   |
      | 2        | Funny Cow    | 6           |
      | 1        | Fluffy Bunny | 4           |
    And I click the cart menu
    Then I verify the items are in the cart
      | Quantity | Product      | Xpath_item_index |
      | 2        | Funny Cow    | 1                |
      | 1        | Fluffy Bunny | 2                |
