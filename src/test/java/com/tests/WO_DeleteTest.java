package com.tests;

import com.utilities.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

public class WO_DeleteTest extends BrowserUtils {

    //    WO-3: Delete

    @Test
    public void delete(){
//         2. Login to Web Orders application using “Tester” and “test”
        openWebsite();
        login();

//         3. Delete a random entry from the table

        List<WebElement> allRows = driver.findElements(
                                    By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr"));
        System.out.println("Number of rows: " + allRows.size());

        random = new Random();
        int numberToDelete = random.nextInt(8)+2;
        System.out.println(numberToDelete);
        WebElement checkBoxToDelete = driver.findElement(
                By.xpath("//table[@id='ctl00_MainContent_orderGrid']//tbody//tr["+numberToDelete+"]/td"));
        String nameToDelete = driver.findElement(
                By.xpath("//table[@id='ctl00_MainContent_orderGrid']//tbody//tr["+numberToDelete+"]/td[2]")).getText();
        System.out.println(nameToDelete);
        checkBoxToDelete.click();
        driver.findElement(By.id("ctl00_MainContent_btnDelete")).click();

//         4. Verify that table row count decreased by 1
        List<WebElement> numberOfRowsAfter = driver.findElements(By.xpath("//table[@id='ctl00_MainContent_orderGrid']//tbody//tr"));
        System.out.println(numberOfRowsAfter.size());

        int diff = allRows.size() - numberOfRowsAfter.size();
        System.out.println("Row count of table is decreased by 1? Difference is: " + diff);

//         5. Verify Name column does not contain deleted person’s name

        List<WebElement> allNames = driver.findElements(
                By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr/td[2]"));

        for (WebElement name : allNames){
            System.out.println(name.getText());
        }
        Assert.assertTrue(!allNames.contains(nameToDelete));

    }

}
