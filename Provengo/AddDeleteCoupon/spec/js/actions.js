function enterDataToSearchBar(session, data) {

    session.writeText(xpaths.clientWindow.searchBar, data.text);
    // session.sleep(1000);
}


function startSearch(session) {
    with (session) {
        click(xpaths.clientWindow.searchButton);
        // session.sleep(SHORT_SLEEP_TIME);
    }
}

function clickOnImac(session) {
    with (session) {
        click(xpaths.clientWindow.clickOnImac);
        // session.sleep(SHORT_SLEEP_TIME);
    }
}

function addToCart(session) {
    with (session) {
        click(xpaths.clientWindow.addToCart);
        sleep(LONG_SLEEP_TIME);
    }
}

function enterShoppingCart(session) {
    with (session) {
        click(xpaths.clientWindow.cartCategory);
        // session.sleep(SHORT_SLEEP_TIME);

    }
}

function clickOnUseCoupon(session) {
    with (session) {
        scrollToElement(xpaths.clientWindow.clickOnUseCoupon)
        click(xpaths.clientWindow.clickOnUseCoupon);
        // session.sleep(SHORT_SLEEP_TIME);
    }
}

function enterCoupon(session, data) {
    with (session) {
        writeText(xpaths.clientWindow.enterCoupon, data.text);
        // session.sleep(SHORT_SLEEP_TIME);

    }
}

function applyCoupon(session) {
    with (session) {
        session.scrollToElement(xpaths.clientWindow.applyCoupon)
        request(Event("The client wants to apply the coupon"));
        click(xpaths.clientWindow.applyCoupon);
        request(Event("The client applied the coupon"));
    }
}

function couponShouldWork(session){
    with (session) {
        let expectedText = "$122.00";
        assertText(xpaths.clientWindow.priceElement, expectedText, TextAssertions.modifiers.Negate)
        request(Event("The price changed"))


    }
}

function couponShouldNotWork(session){
    with (session) {
        let expectedText = "$122.00";
        assertText(xpaths.clientWindow.priceElement, expectedText)
        request(Event("The price didn't change"))

    }
}

function clickOnSubmitTheCoupon(session) {
    with (session) {
        click(xpaths.adminWindow.clickOnSubmitTheCoupon);
        // session.sleep(SHORT_SLEEP_TIME);
        request(Event("The coupon was created"))

    }
}

function enterAdminUsername(session, data) {
    session.writeText(xpaths.adminWindow.enterAdminUsername, data.text);
    // session.sleep(SHORT_SLEEP_TIME);
}

function enterAdminPassword(session, data) {
    session.writeText(xpaths.adminWindow.enterAdminPassword, data.text);
    // session.sleep(SHORT_SLEEP_TIME);
}

function setCouponDiscount(session, data) {
    session.writeText(xpaths.adminWindow.setCouponDiscount, data.text);
    // session.sleep(SHORT_SLEEP_TIME);
}

function setCouponName(session, data) {
    session.writeText(xpaths.adminWindow.couponName, data.text);
    // session.sleep(SHORT_SLEEP_TIME);
}

function setCouponCode(session, data) {
    session.writeText(xpaths.adminWindow.couponCode, data.text);
    // session.sleep(SHORT_SLEEP_TIME);
}

function clickOnSubmit(session) {
    with (session) {
        click(xpaths.adminWindow.clickOnSubmit);
        // session.sleep(SHORT_SLEEP_TIME);
    }
}

function returnToMainCouponPage(session) {
    with (session) {
        click(xpaths.adminWindow.returnToMainCouponPage);
        // session.sleep(SHORT_SLEEP_TIME);
    }
}

function selectCouponToCancel(session) {
    with (session) {
        click(xpaths.adminWindow.selectCouponToCancel);
        // session.sleep(SHORT_SLEEP_TIME);
    }
}

function clickOnAddCoupon(session) {
    with (session) {
        click(xpaths.adminWindow.clickOnAddCoupon);
        // session.sleep(SHORT_SLEEP_TIME);
    }
}


function clickTheDeleteButton(session) {
    with (session) {
        request(Event("Start deleting the coupon"))
        click(xpaths.adminWindow.clickTheDeleteButton);
        acceptAlert();
        request(Event("The coupon was deleted"))
    }
}