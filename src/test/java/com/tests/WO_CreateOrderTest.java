package com.tests;

import com.github.javafaker.Faker;
import com.utilities.BrowserUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WO_CreateOrderTest extends BrowserUtils {

    //        WO-2: Create Order
    @Test
    public void createOrder() {

//        1. Login to Web Orders application using “Tester” and “test””
        openWebsite();
        login();

//        2. Click on Order link
        driver.findElement(By.xpath("//ul[@id='ctl00_menu']//li[3]")).click();

//        3. Select a product (Select different product every time)
//      driver.findElement(By.xpath("//select[@id='ctl00_MainContent_fmwOrder_ddlProduct']/option[2]")).click();
        WebElement select = driver.findElement(By.cssSelector("#ctl00_MainContent_fmwOrder_ddlProduct"));
        Select s = new Select(select);
        Random random = new Random();
        int idx = random.nextInt(3);
        s.selectByIndex(idx);
        String product = s.getFirstSelectedOption().getText();

//        4. Enter data to all the required fields (Enter different data every time)
        //quantity
        int qty = random.nextInt(8) + 1;
        String quantity = String.valueOf(qty);
        driver.findElement(By.xpath("//input[@id='ctl00_MainContent_fmwOrder_txtQuantity']")).sendKeys(quantity);

        //Name,address
        Faker faker = new Faker();
        String name = faker.name().firstName();
        String street = faker.address().streetAddress();
        String city = faker.address().city();
        String state = faker.address().state();
        String zipCode = faker.address().zipCode().substring(0, 5);

        driver.findElement(By.xpath("//input[@id='ctl00_MainContent_fmwOrder_txtName']")).sendKeys(name);
        driver.findElement(By.xpath("//input[@id='ctl00_MainContent_fmwOrder_TextBox2']")).sendKeys(street);
        driver.findElement(By.xpath("//input[@id='ctl00_MainContent_fmwOrder_TextBox3']")).sendKeys(city);
        driver.findElement(By.xpath("//input[@id='ctl00_MainContent_fmwOrder_TextBox4']")).sendKeys(state);
        driver.findElement(By.xpath("//input[@id='ctl00_MainContent_fmwOrder_TextBox5']")).sendKeys(zipCode);

        String cardType = driver.findElement(By.xpath("//input[@id='ctl00_MainContent_fmwOrder_cardList_0']")).getAttribute("value");
        driver.findElement(By.xpath("//input[@id='ctl00_MainContent_fmwOrder_cardList_0']")).click();

//          String cardNumber = faker.number().digit();
        random = new Random();
        int cardNumber = random.nextInt(900000000) + 10000000;
        String cardNumberstr = String.valueOf(cardNumber);
        driver.findElement(By.xpath("//input[@id='ctl00_MainContent_fmwOrder_TextBox6']")).sendKeys(cardNumberstr);


        int month = random.nextInt(3) + 10;
        int year = random.nextInt(5) + 20;
        String date = String.valueOf(month) + "/" + String.valueOf(year);
        driver.findElement(By.xpath("//input[@id='ctl00_MainContent_fmwOrder_TextBox1']")).sendKeys(date);


//        5. Click Proceed
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_InsertButton")).click();

//        6. Click on link View all orders
        driver.findElement(By.linkText("View all orders")).click();

//        7. Verify that all the order details are correct
        List<String> fakeList = new ArrayList<String>();
        fakeList.add(name);
        fakeList.add(product);
        fakeList.add(quantity);
        fakeList.add(street);
        fakeList.add(city);
        fakeList.add(state);
        fakeList.add(zipCode);
        fakeList.add(cardType);
        fakeList.add(cardNumberstr);
        fakeList.add(date);

        System.out.println(fakeList.toString());

        List<String> orderList = new ArrayList<String>();
        List<WebElement> fakeData = driver.findElements(By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr[2]/td"));
        for (WebElement each : fakeData) {
            orderList.add(each.getText());
        }
        orderList.remove(0);
        orderList.remove(11);

        System.out.println(orderList.toString());

        Assert.assertEquals(orderList, fakeList);

    }
}
/*

public class WO2CreateOrder extends TestBase{

    @Test
    public void verifyOrder() throws InterruptedException {
        login();
        driver.findElement(By.linkText("Order")).click();

        //Product
        WebElement select=driver.findElement(By.cssSelector("#ctl00_MainContent_fmwOrder_ddlProduct"));
        Select s=new Select(select);
        Random random=new Random();
        int idx=random.nextInt(3);
        s.selectByIndex(idx);
        String product=s.getFirstSelectedOption().getText();

        //Quantity
        int qty=random.nextInt(8)+1;
        String quantity=String.valueOf(qty);
        driver.findElement(By.cssSelector("#ctl00_MainContent_fmwOrder_txtQuantity")).sendKeys(quantity);
        driver.findElement(By.xpath("(//input[@class='btn_dark'])[1]")).click();

        //Name,address
        Faker f=new Faker();
        String name=f.name().firstName();
        String street=f.address().streetAddress().substring(0,8);
        String address=f.address().fullAddress().substring(0,8);
        String state=f.address().state();
        String zipCode=f.address().zipCode().substring(0,5);

        LocalDate localDate=LocalDate.now();
        String local=localDate.toString().substring(5,7)+"/"+localDate.toString().substring(8,10)+"/"+localDate.toString().substring(0,4);

        driver.findElement(By.cssSelector("#ctl00_MainContent_fmwOrder_txtName")).sendKeys(name);
        driver.findElement(By.cssSelector("#ctl00_MainContent_fmwOrder_TextBox2")).sendKeys(street);
        driver.findElement(By.cssSelector("#ctl00_MainContent_fmwOrder_TextBox3")).sendKeys(address);
        driver.findElement(By.cssSelector("#ctl00_MainContent_fmwOrder_TextBox4")).sendKeys(state);
        driver.findElement(By.cssSelector("#ctl00_MainContent_fmwOrder_TextBox5")).sendKeys(zipCode);


        //CreditCard
        String cardNumber=f.number().digits(12);
        int month=random.nextInt(3)+10;
        int year= random.nextInt(5)+20;
        String date= String.valueOf(month)+"/"+String.valueOf(year);
        String paymentType= driver.findElement(By.cssSelector("#ctl00_MainContent_fmwOrder_cardList_0")).getAttribute("value");

        driver.findElement(By.cssSelector("#ctl00_MainContent_fmwOrder_cardList_0")).click();
        driver.findElement(By.cssSelector("#ctl00_MainContent_fmwOrder_TextBox6")).sendKeys(cardNumber);
        driver.findElement(By.cssSelector("#ctl00_MainContent_fmwOrder_TextBox1")).sendKeys(date);
        driver.findElement(By.linkText("Process")).click();
        driver.findElement(By.linkText("View all orders")).click();

        //Fakedata List
        List<String> fakedata=new ArrayList<String>();
        fakedata.add(name);
        fakedata.add(product);
        fakedata.add(quantity);
        fakedata.add(local);
        fakedata.add(street);
        fakedata.add(address);
        fakedata.add(state);
        fakedata.add(zipCode);
        fakedata.add(paymentType);
        fakedata.add((cardNumber));
        fakedata.add(date);
        System.out.println(fakedata);

        //OderList
        List<String> orderList=new ArrayList<String>();
        List<WebElement> pNames=driver.findElements(By.xpath("//table[@class='SampleTable']//tr[2]/td"));
        for(WebElement d:pNames){
            orderList.add(d.getText());
        }
        //begining and ending cells are removed.
        orderList.remove(0);
        orderList.remove(11);
        System.out.println(orderList);


        Assert.assertEquals(orderList,fakedata);
    }

    public void login(){

        driver.get("http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/\n" +
                "login.aspx");
        driver.findElement(By.cssSelector("input[name='ctl00$MainContent$username']")).sendKeys("Tester");
        driver.findElement(By.name("ctl00$MainContent$password")).sendKeys("test");
        driver.findElement(By.id("ctl00_MainContent_login_button")).click();
    }

}
 */


