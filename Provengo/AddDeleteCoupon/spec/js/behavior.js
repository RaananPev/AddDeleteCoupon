/* @provengo summon selenium */

let session_use_coupon;
/**
 * This story opens a new browser window, goes to google.com, and searches for "Pizza". TODO: FIX IT
 */
bthread('Client side', function () {

    session_use_coupon = new SeleniumSession('Client Session').start(URL)
    enterDataToSearchBar(session_use_coupon, { text: SEARCH_TITLE })
    startSearch(session_use_coupon)
    clickOnImac(session_use_coupon)
    addToCart(session_use_coupon)
    enterShoppingCart(session_use_coupon)
    clickOnUseCoupon(session_use_coupon)
    waitFor(Event("The coupon was created"))
    enterCoupon(session_use_coupon,{text : COUPON_CODE})
    applyCoupon(session_use_coupon)
})

// /**
//  * This story opens a new browser window, goes to google.com, and searches for "Pasta" using the "I Feel Lucky" feature.
//  TODO: FIX IT
//  */
bthread('Admin creates a coupon', function () {
    let session_add_coupon = new SeleniumSession('Admin Session').start(ADMIN_URL)
    enterAdminUsername(session_add_coupon, { text: ADMIN_USERNAME })
    enterAdminPassword(session_add_coupon, { text: ADMIN_PASSWORD })
    clickOnSubmit(session_add_coupon)
    clickOnAddCoupon(session_add_coupon)
    setCouponName(session_add_coupon,{text:COUPON_NAME})
    setCouponCode(session_add_coupon,{text:COUPON_CODE})
    setCouponDiscount(session_add_coupon, { text: ADMIN_DISCOUNT })
    clickOnSubmitTheCoupon(session_add_coupon)
    returnToMainCouponPage(session_add_coupon)
    // let session_cancel_coupon = new SeleniumSession('cancel coupon').start(ADMIN_URL)
    selectCouponToCancel(session_add_coupon)
    clickTheDeleteButton(session_add_coupon)
})

bthread('Admin deletes the coupon before the client applied it', function () {
    sync({waitFor: Event("Start deleting the coupon"), block: Event("The client wants to apply the coupon")});
    sync({waitFor: Event("The coupon was deleted"), block: Event("The client wants to apply the coupon")});
    couponShouldNotWork(session_use_coupon)
    //block(Event("The price changed"))
})

bthread('Admin deletes the coupon after the client applied it', function () {
    sync({waitFor: Event("The client wants to apply the coupon"), block: Event("Start deleting the coupon")});
    sync({waitFor: Event("The client applied the coupon"), block: Event("Start deleting the coupon")});
    couponShouldWork(session_use_coupon)
//    block(Event("The price didn't change"))
})