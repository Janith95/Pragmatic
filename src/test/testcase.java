package com.pragmatic.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import static org.assertj.core.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import static io.netty.handler.codec.rtsp.RtspHeaderValues.URL;

public class testcase {

    public static final String BASE_URL = "https://www.saucedemo.com";
    WebDriver driver;




    @BeforeClass
    public void beforeClass(){
        //Setup the browser
      WebDriverManager.chromedriver().setup();
        //Launch the browser
        driver = new ChromeDriver();

        //Navigate to the login page
        driver.navigate().to(BASE_URL);

        driver.manage().window().maximize();

    }
            //System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
           // WebDriver driver = new ChromeDriver();

            //WebDriverManager.chromedriver().setup();

           // driver.get("https://www.saucedemo.com");
       // }


        @Test
        public void loginWithCredentials() {
            driver.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys("standard_user");
            driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("secret_sauce");

            driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();

            Assert.assertEquals("https://www.saucedemo.com/inventory.html", "https://www.saucedemo.com/inventory.html");
        }

        @Test
        public void addProduct() {

            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            //*[@id="item_4_title_link"]/div
            WebElement backpack = driver.findElement(By.xpath("//*[@id=\"item_4_title_link\"]/div"));
            //WebElement backpack = driver.findElement(By.xpath("//*[@id=\"item_4_img_link\"]/img"));
            driver.findElement(By.xpath("//*[@id=\"item_4_title_link\"]/div")).click();

            //5.Click on add to cart
            driver.findElement(By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div/button")).click();

            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

            //6.Click on the Shopping Cart icon
            driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();

            //7. Verify the URL is https://www.saucedemo.com/cart.html
            Assert.assertEquals(URL, "https://www.saucedemo.com/cart.html");

        }

        @Test
        public void removeProduct(){
            //9. Click on Remove
            driver.findElement(By.xpath("//*[@id=\"cart_contents_container\"]/div/div[1]/div[3]/div[2]/div[2]/button")).click();

            //if(driver.{ System.out.println("Shopping cart is empty"); }

            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);


        }

        @Test
        public void continueShopping(){
            //11. Click on continue shopping
            driver.findElement(By.xpath("//*[@id=\"cart_contents_container\"]/div/div[2]/a[1]")).click();

            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);


            WebElement element = driver.findElement(By.id("item_1_img_link"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);


            //12. Add any 2 products to the cart
            driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[3]/div[3]/button")).click();
            driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[4]/div[3]/button")).click();

            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            //Thread.sleep(3000);

            driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();

            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            //Thread.sleep(3000);

        }

        @Test
        public void checkOut(){
            //14. Click on checkout
            driver.findElement(By.xpath("//*[@id=\"cart_contents_container\"]/div/div[2]/a[2]")).click();

            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            //Thread.sleep(3000);

            //15. Fill the necessary fields, use suitable element locators
            driver.findElement(By.xpath("//*[@id=\"first-name\"]")).sendKeys("Janith");
            driver.findElement(By.xpath("//*[@id=\"last-name\"]")).sendKeys("Gamage");
            driver.findElement(By.xpath("//*[@id=\"postal-code\"]")).sendKeys("81000");

            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            //Thread.sleep(3000);

            //16. Click on Continue
            driver.findElement(By.xpath("//*[@id=\"checkout_info_container\"]/div/form/div[2]/input")).click();

            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

            //if(driver.{ System.out.println(""); }

            //18. Click on finish
            driver.findElement(By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[2]/div[8]/a[2]")).click();

            //19. Verify the Thank You for your order text is visible.
            if (driver.getPageSource().contains("THANK YOU FOR YOUR ORDER")) {
                System.out.println("Finished");
            }

            //20. Verify the URL https://www.saucedemo.com/checkout-complete.html
            Assert.assertEquals(URL, "https://www.saucedemo.com/checkout-complete.html");
        }

        /*@AfterMethod
        public void aftermethod(){

            driver.quit();
        }*/
}

