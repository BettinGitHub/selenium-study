package com.qaguru.seleniumstudy;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class WebElementPractise {
    private final String baseUrl = "http://www.qaguru.ca/webelementapp.php";
    private WebDriver driver;

    @BeforeTest
    public void beforeTest(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setup(){
        driver = new ChromeDriver();
        driver.get(baseUrl);
        //Implicit Wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void selectGender(){
        driver.findElement(By.xpath("//*[@id=\"home-5\"]/form[2]/input[2]")).click();
    }

    @Test
    public void genderIsClicked(){
        WebElement radMale = driver.findElement(By.xpath("//*[@id=\"home-5\"]/form[2]/input[1]"));
        Assert.assertTrue(radMale.isSelected(), "Male Radio button is not selected");
    }

    @Test
    public void setTextBox(){
        WebElement webElement = driver.findElement(By.name("firstname"));
        webElement.clear();
        webElement.sendKeys("Jacky");
    }

    @Test
    public void listBox(){
        WebElement webElement = driver.findElement(By.name("cars"));

        Select select = new Select(webElement);
        select.selectByVisibleText("Audi");

        List<WebElement> webElements = select.getOptions();
        for(WebElement we: webElements){
            System.out.println(we.getText());
        }
    }

    @Test
    public void checkBox() throws InterruptedException{
        sleep(1000);
        WebElement webElement = driver.findElement(By.name("vehicle1"));
        System.out.println(webElement.isSelected());
        webElement.click();
    }

    @Test
    public void calenderControl() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.name("bday")).sendKeys("2020"+ Keys.TAB+"1018");

    }

    @Test
    public void clickButton() throws InterruptedException {
        Thread.sleep(10000);
        driver.findElement(By.xpath("//*[@id=\"home-5\"]/button")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "QA GURU!", "Incorrect alert messsage");
        Thread.sleep(3000);
        alert.accept();
    }

    @Test
    public void implicitSync() throws InterruptedException {
        driver.findElement(By.name("bday")).sendKeys("2020"+ Keys.TAB+"1018");
    }

    //Explicit wait - Synchronization
    @Test
    public void explicitSync() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("bday")));
        webElement.sendKeys("2020"+ Keys.TAB+"1018");
    }


}
