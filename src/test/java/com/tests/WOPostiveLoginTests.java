package com.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WOPostiveLoginTests {


    WebDriver driver;
    String title;

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

    //    WO-1: Positive Login Test
    @Test
    public void positiveLoginTest(){
        //      4. Enter username “Tester”
        driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");

        //      5. Enter password “test”
        driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test");

        //      6. Click on Login button
        driver.findElement(By.id("ctl00_MainContent_login_button")).click();

        //      7. Verify title equals “Web Orders”
        String expectedTitle = "Web Orders";
        String actualTitle = driver.getTitle();

        System.out.println("Expected title: " + expectedTitle + " | Actual title: " + actualTitle);
        Assert.assertEquals(expectedTitle, actualTitle);

        //      8. Verify url equals http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/login.aspx
        String expectedUrl = "http://secure.smartbearsoftware.com/samples/testcomplete12/weborders/"; // failing because of the weborders
        String actualUrl = driver.getCurrentUrl();

        System.out.println("Expected URL: " + expectedUrl + " | Actual URL: " + actualUrl);
        Assert.assertEquals(expectedUrl, actualUrl);

    }

}
