import java.time.Duration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BookstoreTest {

    @Test
    void purchaseOrderTest() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1500,1000");

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://practice.automationtesting.in/");
        WebElement popup = driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[1]/div[2]/div[2]/button[1]/p"));
        popup.click();
        WebElement shop = driver.findElement(By.xpath("//*[@id=\"menu-item-40\"]/a"));
        shop.click();

        WebElement book = driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li[6]/a[1]"));
        WebElement bookTitleElement = driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li[6]/a[1]/h3"));
        String bookTitle = bookTitleElement.getText();

        new Actions(driver)
            .scrollToElement(book)
            .perform();

        book.click();

        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/div/div/div[2]/form/button")).click();

        WebElement menu = driver.findElement(By.id("menu-icon"));
        boolean menuIsVisible = menu.isDisplayed();
        if (menuIsVisible) {
            menu.click();
            WebElement cart1 = driver.findElement(By.xpath("///*[@id=\"wpmenucartli\"]/a/span[1]"));
            cart1.click();
        } else {
            WebElement cart = driver.findElement(By.xpath("//*[@id=\"wpmenucartli\"]/a/span[1]"));
            cart.click();
        }
        WebElement proceed = driver.findElement(By.xpath("//*[@id=\"page-34\"]/div/div[1]/div/div/div/a"));

        new Actions(driver)
            .scrollByAmount(0, 100)
            .perform();
        proceed.click();

        WebElement firstName = driver.findElement(By.id("billing_first_name"));
        firstName.sendKeys("John");

        WebElement lastName = driver.findElement(By.id("billing_last_name"));
        lastName.sendKeys("Smith");

        WebElement email = driver.findElement(By.id("billing_email"));
        email.sendKeys("john@gmail.com");

        WebElement phone = driver.findElement(By.id("billing_phone"));
        phone.sendKeys("600087322");

        WebElement postcode = driver.findElement(By.id("billing_postcode"));

        new Actions(driver)
            .scrollToElement(postcode)
            .perform();

        WebElement address = driver.findElement(By.id("billing_address_1"));
        address.sendKeys("Belvedere Street");

        WebElement countryOne = driver.findElement(By.id("s2id_billing_country"));
        countryOne.click();

        WebElement country = driver.findElement(By.xpath("//*[@id=\"s2id_autogen1_search\"]"));
        country.sendKeys("United Kingdom (UK)");
        country.sendKeys(Keys.ENTER);

        WebElement city = driver.findElement(By.id("billing_city"));

        city.sendKeys("London");
        postcode.sendKeys("PO16 7GZ");

        WebElement placeOrder = driver.findElement(By.id("place_order"));
        placeOrder.sendKeys(Keys.ENTER);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement orderDetails = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"page-35\"]/div/div[1]/h2[2]")));

        Assertions.assertEquals("Order Details", orderDetails.getText());

        WebElement bookTitleFinal = driver.findElement(By.xpath("//*[@id=\"page-35\"]/div/div[1]/table/tbody/tr/td[1]/a"));

        Assertions.assertEquals (bookTitle, bookTitleFinal.getText());

        WebElement confirmation = driver.findElement(By.xpath("//*[@id=\"page-35\"]/div/div[1]/p[1]"));
        
        Assertions.assertEquals("Thank you. Your order has been received.", confirmation.getText());


        driver.quit();
    }

}
