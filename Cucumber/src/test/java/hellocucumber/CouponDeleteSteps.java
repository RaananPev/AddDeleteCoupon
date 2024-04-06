package hellocucumber;

import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class CouponDeleteSteps {

    float before;  // a variable for the price before the coupon
    private final static String CHROME_DRIVER_PATH = "/Users/raananpevzner/Documents/GitHub/2023-mbt-25-59/Selenium/chromedriver";
    private ChromeDriver driver;
    private WebDriverWait wait;

    /**
     * this function is used to log in as the admin to the coupon page
     */
    @Given("the admin is logged in to the coupon management system")
    public void adminIsLoggedIn() {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        this.driver = new ChromeDriver();               // starts a new chrome driver
        this.driver.manage().window().maximize();           // maximize the window
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));      // set a default wait value
        // opens the admin page
        driver.get("http://localhost/opencart/upload/admin/index.php?route=marketing/coupon&user_token=d5f8b93fa41721527bb4c3f0b4dc029b/");
        // enters admins username
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@name=\"username\"]"))).sendKeys("admin");
        // enters admins password
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@name=\"password\"]"))).sendKeys("12345");
        // press to submit button
        driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();


    }

    /**
     * this function is used to add a new valid coupon with the number 54321
     */
    @And("there is a valid coupon")
    public void adminAddsACoupon(){
        // click on add new coupon
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[contains(@class, 'fa-solid fa-plus')]")));
        element.click();
        // set the coupon name
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='input-name']"))).sendKeys("Coupon Name");
        // set the coupon code
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='input-code']"))).sendKeys("54321");
        // set the coupon value in %
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='input-discount']"))).sendKeys("30");
        // click on submit the coupon
        WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[1]/div[1]/button[1]/i[1]")));
        element1.click();
        // return to the main coupon page
        element1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ol[1]/li[2]/a[1]")));
        element1.click();
    }

    /**
     * this function is used to select a coupon to delete
     */
    @When("the admin selects the coupon to cancel")
    public void adminSelectsCouponToCancel() {
        // ticks the V on the coupon we will delete
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tr[3]/td[1]/input[1]")));
        element.click();
    }

    /**
     * this function confirms the delete process
     */
    @And("confirms the cancellation")
    public void adminConfirmsCancellation() {
        // click the delete button
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("  //button[contains(@class, 'btn-danger')]")));
        element.click();
        // Switch to the alert
        Alert alert = driver.switchTo().alert();
        // Accept the "are you sure" alert by pressing "OK"
        alert.accept();
        //close the admins window
        this.driver.close();




    }

    /***
     * this function start a new driver as a user and then Adding an IMAC to the cart
     */
    @And("I have added a product")
    public void HaveAddedAProductToMyCart() {
        // start the new chrome driver
        this.driver = new ChromeDriver();
        //maximize the window
        driver.manage().window().maximize();
        // set a default wait value
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        // opens the user page
        driver.get("http://localhost/opencart/upload/");
        // wait for the site to load
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // add the product (iMac) to the shopping cart
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[2]/div[1]/input[1]"))).sendKeys("iMac");
        driver.findElement(By.xpath("//*[@id='search']/button[1]")).click();
        driver.findElement(By.xpath("//*[@id='product-list']/div/div/div/a/img[1]")).click();
        driver.findElement(By.xpath("//*[@id='button-cart']")).click();

        // The time it takes for the new thing to go down.
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // go to the shopping cart
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='top']/div[1]/div[2]/ul[1]/li[4]/a[1]/span[1]")));
        element.click();

        // wait for the shopping card to load
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /***
     * this function saves the initial price for the product,then user enters a deleted coupon code to the shopping cart
     */
    @And("I entered a deleted coupon")
    public void iEnteredADeletedCoupon(){
        // Getting the main price
        WebElement element = driver.findElement(By.xpath("//tfoot[1]/tr[4]/td[2]"));
        String text = element.getText();
        // Remove all non-numeric characters except for '.'
        String numericText = text.replaceAll("[^0-9.]", "");
        this.before = Float.parseFloat(numericText);
        //Checking to see if the price is good
        assertEquals(122.0,before);


        // Apply the coupon code
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

        // wait for the coupon to load
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement element3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[2]/h2[1]/button[1]")));
        element3.click();

        // scrolls up to the top of the page
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='input-coupon']"))).sendKeys("54321");

        // Click apply coupon
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
        WebElement element2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[2]/div[1]/div[1]/form[1]/div[2]/button[1]")));
        element2.click();
    }

    /**
     * this function checks that the price before the coupon and the price after the coupon is the same
     */
    @And("checks the price was not change")
    public void checksThePriceWasNotChange() {
        // scroll to the top of the page
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
        //wait for the scrolling
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
        // Assert that totalPrice hasn't changed
        assertEquals(before,after);
    }

    }
