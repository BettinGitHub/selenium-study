package com.qaguru.seleniumstudy;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DemoTest {
    private final String baseUrl = "https://www.homeandstuff.com/";

    @Test
    public void testHomePageTitle() throws InterruptedException {
        System.out.println("Testing the Home Page Title");
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get(baseUrl);
        //driver.quit(); //process is terminated and completely released from memory
        // driver.close(); //will also work, but the instance still remains there
        String expTitle = "Furniture, Kitchen, Dining Room, Entertainment, Bedroom Sets, Outdoor, Fireplaces";
        String actTitle = driver.getTitle();
        Assert.assertEquals(actTitle, expTitle, "Title is wrong");
        Thread.sleep(1000);
        driver.quit();

    }

    @Test
    public void testSearchingForAProduct() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get(baseUrl);
        WebElement txtSearch = driver.findElement(By.name("search_field"));
        txtSearch.clear();
        txtSearch.sendKeys("table");
        WebElement btnSearch = driver.findElement(By.xpath("//*[@id=\"search\"]/form/div/span/input"));
        btnSearch.click();
        WebElement lnkResult = driver.findElement(By.linkText("Atlantic Height Adjustable Standing Desk Converter Large"));
        driver.quit();

    }
}
