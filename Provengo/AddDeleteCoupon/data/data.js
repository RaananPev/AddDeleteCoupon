/*
 *  This is a good place to put common test data, project-wide constants, etc.
 */

const COUPON_CODE = "54321";
const SEARCH_TITLE = "iMac";
const ADMIN_USERNAME = "admin";
const ADMIN_PASSWORD = "12345";
const ADMIN_DISCOUNT = "30";
const COUPON_NAME = "Coupon Name"
SHORT_SLEEP_TIME = 1000;
LONG_SLEEP_TIME = 5000;


const URL = "http://localhost/opencart/upload/";
const ADMIN_URL = "http://localhost/opencart/upload/admin/index.php?route=marketing/coupon&user_token=d5f8b93fa41721527bb4c3f0b4dc029b";

const xpaths = {
  clientWindow: {
    searchBar: "//div[2]/div[1]/input[1]", // After that: enter "iMac" (sendKeys)
    searchButton: "//div[2]/div[1]/button[1]",
    clickOnImac: "//*[contains(@alt, \"iMac\")]",
    addToCart: "//*[@id='button-cart']",
    cartCategory : "//li[4]/a[1]/span[1]",
    enterShoppingCart: "//div[1]/p[1]/a[1]/strong[1]",
    clickOnUseCoupon: "//div[2]/h2[1]/button[1]", // After that: enter COUPON_CODE (sendKeys)
    applyCoupon: "//div[2]/div[1]/div[1]/form[1]/div[2]/button[1]",
    enterCoupon: "//div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[1]/input[1]",
    priceElement: "//table[1]/tfoot[1]/tr[4]/td[2]"

  },
  adminWindow: {
    enterAdminUsername: '//*[@name="username"]',
    enterAdminPassword: '//*[@name="password"]',
    clickOnSubmit: '//button[@type="submit"]',
    clickOnAddCoupon: "//div[1]/div[1]/a[1]",
    couponName :"//div[1]/div[1]/div[1]/input[1]",
    couponCode: "//div[2]/div[1]/input[1]",
    setCouponDiscount: "//*[@id='input-discount']",
    clickOnSubmitTheCoupon: "//div[1]/div[1]/button[1]",
    returnToMainCouponPage: "//ol[1]/li[2]/a[1]",
    selectCouponToCancel: "//tr[3]/td[1]/input[1]",
    clickTheDeleteButton: "//button[contains(@class, 'btn-danger')]"
  }
}