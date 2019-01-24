package com.tests;

import com.utilities.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WO_EditTest extends BrowserUtils {

    //    WO-4: Edit
    @Test
    public void edit() {

//         1. Login to Web Orders application using “Tester” and “test”
        openWebsite();
        login();
//         2. Click edit button for any entry
        random = new Random();
        int randomRowNumber = random.nextInt(8)+2; // find a way to ignore cell#1
        List<String> initialList = new ArrayList<String>();
        List<WebElement> initialItemInfo = driver.findElements(
                By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr["+randomRowNumber+"]"));
        for (WebElement row : initialItemInfo) {
            initialList.add(row.getText());

        }
        String nameOfCustomer = driver.findElement(
                By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr["+randomRowNumber+"]/td[2]")).getText();
        System.out.println("Name of customer whose order is updated: " + nameOfCustomer);
        driver.findElement(By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr["+randomRowNumber+"]/td[13]")).click();

//         3. Change the quantity to a different amount
        String initialQuantity = driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity")).getAttribute("value");
        System.out.println(initialQuantity);
        int rndQuantity = random.nextInt(8)+1;
        String updatedQuantity = String.valueOf(rndQuantity);
        driver.findElement(By.xpath("//input[@id='ctl00_MainContent_fmwOrder_txtQuantity']")).clear();
        driver.findElement(By.xpath("//input[@id='ctl00_MainContent_fmwOrder_txtQuantity']")).sendKeys(updatedQuantity);
        System.out.println(updatedQuantity);

//         4. Click Calculate
        driver.findElement(By.xpath("//input[@class='btn_dark']")).click();

//         5. Verify that new quantity is displayed
        System.out.println("Initial quantity: " + initialQuantity + " | Updated quantity: " + updatedQuantity);
        if (!initialQuantity.equals(updatedQuantity)) {
            System.out.println("Updated quantity is displayed.");
        }else{
            System.out.println("Same quantity is selected randomly.");
        }
//         6. Click Update
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_UpdateButton")).click();

//         7. Verify new quantity is displayed
        WebElement quantityDisplayed = driver.findElement(
                By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr["+randomRowNumber+"]/td[4]"));
        System.out.println("Updated quantity is displayed on order page: " + quantityDisplayed.getText());
        Assert.assertTrue(quantityDisplayed.isDisplayed());

//         8. Verify that other information in that row did not change
        List<String> updatedList = new ArrayList<String>();
        List<WebElement> updatedItemInfo = driver.findElements(
                By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr["+randomRowNumber+"]"));
        for (WebElement row : updatedItemInfo) {
            updatedList.add(row.getText());

        }
        System.out.println(initialList.toString());
        System.out.println(updatedList.toString());

        // use splitmethod to seperate the list by space

        Assert.assertEquals(initialList, updatedList);


    }
}

