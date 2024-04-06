Feature: Coupon Management

  Scenario: Admin cancels a coupon
    Given the admin is logged in to the coupon management system
    And there is a valid coupon
    When the admin selects the coupon to cancel
    And confirms the cancellation
    Then I have added a product
    And I entered a deleted coupon
    And checks the price was not change


