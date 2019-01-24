package com.utilities;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import java.util.List;
import java.util.Random;

public class BrowserUtils {

        public WebDriver driver;
        public Faker faker;
        public Random random;
        public Select select;

//    a. verifyTextMatches takes 2 parameters: two strings. Verifies whether the
//    first string param is equal to the second one

    public static void verifyTextMatches(String str1, String str2){

        if (str1.equals(str2)){
            System.out.println("Given two strings are equal.");
        }
    }

//    b. verifyTextContains takes 2 parameters: two strings. Verifies whether
//    the first string param contains the second one

    public static void verifyTestContains(String str1, String str2){

        if (str1.contains(str2)){
            System.out.println("First string contains the second string.");
        }
    }

    public void openWebsite(){
        //      Open browser
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        //      Go to website  http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/login.aspx
        driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
    }

    public void login() {
        //      Enter username “Tester”
        driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");

        //      Enter password “test”
        driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test");

        //      Click on Login button
        driver.findElement(By.id("ctl00_MainContent_login_button")).click();
    }

    public void printColumn(){
        List<WebElement> allNames = driver.findElements(
                By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr/td[3]"));

        for (WebElement name : allNames){
            System.out.println(name.getText());
        }
    }

}
