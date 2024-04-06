# Testing OpenCart using Cucumber
This directory contains the cucumber files for testing the OpenCart module of the coupon application.

## Description of the tests

### 1. Apply coupon code by user:
   Firstly, it generate a *@before* function that logs in as admin and adds a coupon for discount with the code *12345*, so when the user logs back in, it will be able to use it
   in order to check if the coupon works.
   Secondly, the User will search for IMac and add it to the cart.
   After doing so, the user will go to the cart, to be able to choose to apply the coupon.
   Note that it has to pick something before using the coupon.
   The current price of the cart will be saved, for the assertion.
   Then the user will apply the coupon code *12345* that was created with the *@before* function.
   To conclude the test, after the coupon was user, it will then check to see the price of the cart after,
   and asserting the price isn't the same, meaning the coupon worked and the test passed.
   
### 2. Deleting coupon by admin:
Firstly, it generate a @before function that logs in as admin and adds a coupon for discount with the code 12345, so when the admin logs back in, he will be able to delete the coupon. in order to delete the coupon firstly we looking for the coupon in the coupons list, and then the adins select the coupon which was created in the @before. after marking the coupon it clicks the delete buttom, after that an alert is raising to ask if we are sure about deleting the marked coupos, when we accept the alert we the coupon will be deleted. after that, as users we add an item to the shopping cart, entring the coupon code, and checking that the price hasn't change.



## Running the tests
Run ```mvn test``` to run all the tests.

## Feature files
The behaviors that we tested are in the feature files that inside the [resources/hellocucumber](resources/hellocucumber) directory. See the files for a detailed description of the tests.

## Step files
The step files in the [src/test/java/hellocucumber](src/test/java/hellocucumber) directory contain the code that defines how each sentence in the feature files is translated to Selenium actions. See the files for a detailed description of the implementation.
