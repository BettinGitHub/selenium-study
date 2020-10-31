package com.qaguru.seleniumstudy;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DemoTest {
    private final String baseUrl = "https://www.homeandstuff.com/";

    @Test
    public void testHomePageTitle(){
        System.out.println("Testing the Home Page Title");
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get(baseUrl);
        //driver.quit(); //process is terminated and completely released from memory
        // driver.close(); //will also work, but the instance still remains there
        String expTitle = "Furniture, Kitchen, Dining Room, Entertainment, Bedroom Sets, Outdoor, Fireplaces";
        String actTitle = driver.getTitle();
        Assert.assertEquals(actTitle, expTitle, "Title is wrong");
        //Thread.sleep(1000);
        driver.quit();

    }
}
