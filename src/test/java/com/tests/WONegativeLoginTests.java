package com.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WONegativeLoginTests {

    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        //      1. Open browser
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        //      2. Go to website  http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/login.aspx
        driver.get("http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/login.aspx");

        //      3. Verify title equals “Web Orders Login”
        String expectedTitleBeforeLogin = "Web Orders Login";
        String actualTitleBeforeLogin = driver.getTitle();

        System.out.println("Expected title before login: " + expectedTitleBeforeLogin + " | Actual title before login: " + actualTitleBeforeLogin);
        Assert.assertEquals(expectedTitleBeforeLogin, actualTitleBeforeLogin);

    }


    //    WO-2: Negative Login Test Wrong Username
    @Test
    public void negativeLoginTestWrongUsername() {

//      4. Save the current url
        String currentUrl = driver.getCurrentUrl();

//      5. Enter username “Test”
        driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Test");

//      6. Enter password “Test”
        driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test");

//      7. Click on Login button
        driver.findElement(By.id("ctl00_MainContent_login_button")).click();

//      8. Verify title still equals “Web Orders Login”
        String expectedTitle = "Web Orders Login";
        String actualTitle = driver.getTitle();

        System.out.println("Expected title: " + expectedTitle + " | Actual title: " + actualTitle);
        Assert.assertEquals(expectedTitle, actualTitle);

//      9. Verify the current url equals the string saved in step 4
        String actualUrl = driver.getCurrentUrl();

        System.out.println("Current URL from step 4: " + currentUrl + " | Actual URL at step 9: " + actualUrl);
        Assert.assertEquals(currentUrl, actualUrl);
    }

//    WO-3: Negative Login Test Wrong Password

    @Test
    public void negativeLoginTestWrongPassword() {

//          4. Save the current url
        String currentUrl = driver.getCurrentUrl();
//          5. Enter username “Tester”
        driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
//          6. Enter password “Tester”
        driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("Tester");
//          7. Click on Login button
        driver.findElement(By.id("ctl00_MainContent_login_button")).click();
//          8. Verify title still equals “Web Orders Login”
        String expectedTitle = "Web Orders Login";
        String actualTitle = driver.getTitle();

        System.out.println("Expected title: " + expectedTitle + " | Actual title: " + actualTitle);
        Assert.assertEquals(expectedTitle, actualTitle);

//          9. Verify the current url equals the string saved in step 4
        String actualUrl = driver.getCurrentUrl();

        System.out.println("Current URL from step 4: " + currentUrl + " | Actual URL at step 9: " + actualUrl);
        Assert.assertEquals(currentUrl, actualUrl);

    }
    //    WO-4: Negative Login Test Blank Username
    @Test
    public void negativeLoginTestBlankUsername() {

//          4. Save the current url
        String currentUrl = driver.getCurrentUrl();

//          5. Enter password “test”
        driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test");

//          6. Click on Login button
        driver.findElement(By.id("ctl00_MainContent_login_button")).click();

//          7. Verify title still equals “Web Orders Login”
        String expectedTitle = "Web Orders Login";
        String actualTitle = driver.getTitle();

        System.out.println("Expected title: " + expectedTitle + " | Actual title: " + actualTitle);
        Assert.assertEquals(expectedTitle, actualTitle);

//          8. Verify the current url equals the string saved in step 4
        String actualUrl = driver.getCurrentUrl();

        System.out.println("Current URL from step 4: " + currentUrl + " | Actual URL at step 9: " + actualUrl);
        Assert.assertEquals(currentUrl, actualUrl);

    }

//    WO-5: Negative Login Test Blank Password

//          4. Save the current url
//          5. Enter username “Tester”
//          6. Click on Login button
//          7. Verify title still equals “Web Orders Login”
//          8. Verify the current url equals the string saved in step 4


}
