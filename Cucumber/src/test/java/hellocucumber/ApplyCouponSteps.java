package hellocucumber;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public class ApplyCouponSteps {
    WebDriver driver;
    float before;
    private String turn = "apply";
    private final static String CHROME_DRIVER_PATH = "/Users/raananpevzner/Documents/GitHub/2023-mbt-25-59/Selenium/chromedriver";
    private WebDriverWait wait;

    String COUPONCODE = null;

    /**
     * This is the build up funtion.
     * It will log in as admin and create a coupon code of 12345
     * That the test can later use so it test the validity of the code.
     */
    @Before
    public void buildUp()
    {
        if (Objects.equals(turn, "apply")) {
            // Implementation to log in as admin
            System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
            this.driver = new ChromeDriver();
            this.driver.manage().window().maximize();
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));

            // Logs in as admin
            driver.get("http://localhost/opencart/upload/admin/index.php?route=marketing/coupon&user_token=d5f8b93fa41721527bb4c3f0b4dc029b/");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@name=\"username\"]"))).sendKeys("admin");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@name=\"password\"]"))).sendKeys("12345");
            driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[contains(@class, 'fa-solid fa-plus')]")));
            element.click();

            // inserting the data for creating on coupon
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='input-name']"))).sendKeys("Coupon Name");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='input-code']"))).sendKeys("54321");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='input-discount']"))).sendKeys("30");
            WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[1]/div[1]/button[1]/i[1]")));
            element1.click();
            element1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ol[1]/li[2]/a[1]")));
            element1.click();
            // Creating the code and closing the driver
            this.COUPONCODE = "54321";
            driver.close();
        }
    }


    /***
     * Opening the main page of Opencart
     */
    @Given("I am on the OpenCart homepage")
    public void IMmOnTheOpenCartHomepage() {
        this.driver = new ChromeDriver();
        driver.manage().window().maximize();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));

        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        driver.get("http://localhost/opencart/upload/");

    }

    /***
     * Making sure the coupon was created.
     */
    @And("there is a valid coupon number")
    public void thereIsAValidCoupon() {
        assertNotNull(this.COUPONCODE);
    }

    /***
     * Adding an IMAC to the cart
     */
    @And("I have added a product to my cart")
    public void iHaveAddedAProductToMyCart() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[2]/div[1]/input[1]"))).sendKeys("iMac");
        driver.findElement(By.xpath("//*[@id='search']/button[1]")).click();
        driver.findElement(By.xpath("//*[@id='product-list']/div/div/div/a/img[1]")).click();
        driver.findElement(By.xpath("//*[@id='button-cart']")).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /***
     * Moving to the shopping cart for later use.
     */
    @And("I moved to shopping cart")
    public void iMovedToShoppingCart() {
        // The time it takes for the pop-up thing to go down.
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Clicks on the cart on top.
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='top']/div[1]/div[2]/ul[1]/li[4]/a[1]/span[1]")));
        element.click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /***
     * Applying the coupon code on the shopping cart
     */
    @When("I apply a valid coupon code")
    public void IApplyAValidCouponCode() {
        // Getting the main price
        WebElement element = driver.findElement(By.xpath("//tfoot[1]/tr[4]/td[2]"));
        String text = element.getText();
        // Remove all non-numeric characters except for '.'
        String numericText = text.replaceAll("[^0-9.]", "");
        this.before = Float.parseFloat(numericText);
        System.out.println("Float value: " + before);
        //Checking to see if the price is good
        assertEquals(122.0,before);


        // Apply the coupon code
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement element3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[2]/h2[1]/button[1]")));
        element3.click();

        // Enter the code
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='input-coupon']"))).sendKeys(this.COUPONCODE);

        // Click apply coupon
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement element2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[2]/div[1]/div[1]/form[1]/div[2]/button[1]")));
        element2.click();
    }

    /***
     * Asserting the discount has happens and the price went down.
     */
    @Then("the discount should be applied to the total cost")
    public void theDiscountShouldBeAppliedToTheTotalCost() {
        // Verify the discount is applied
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Getting the main price
        WebElement element = driver.findElement(By.xpath("//tfoot[1]/tr[4]/td[2]"));
        String text = element.getText();
        // Remove all non-numeric characters except for '.'
        String numericText = text.replaceAll("[^0-9.]", "");
        float after = Float.parseFloat(numericText);

        // Assert that totalPrice is not as it used too, meaning the discount has happend.
        assertNotEquals(before,after);
    }

}

