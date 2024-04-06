Feature: A customer uses a coupon that lowers the cost in the cart

  Scenario: Customer applies a valid coupon code to the cart
    Given I am on the OpenCart homepage
    And there is a valid coupon number
    And I have added a product to my cart
    And I moved to shopping cart
    When I apply a valid coupon code
    Then the discount should be applied to the total cost



