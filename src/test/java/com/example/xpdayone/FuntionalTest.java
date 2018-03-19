package com.example.xpdayone;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FuntionalTest {

    @LocalServerPort
    int port;
    WebDriver driver;

    @Test
    public void indexTest(){
        WebDriver driver = getWebdriver();
        driver.get("http://localhost:"+port+"/");
        WebDriverWait driverWait = new WebDriverWait(driver,10);
        WebElement mydiv = driverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("mydiv")));
        Assert.assertNotNull(mydiv);
        driver.close();
    }

    private WebDriver getWebdriver(){
        String path = System.getProperty("user.dir")+"/chromedriver";
        System.setProperty("webdriver.chrome.driver", path);
        return new ChromeDriver();
    }

    @Test
    public void loginWithOkCredentialsTest() throws IOException {
        driver = getWebdriver();
        driver.get("http://localhost:"+port+"/");
        WebDriverWait wdw = new WebDriverWait(driver, 10);
        WebElement nameField = wdw.until(ExpectedConditions.presenceOfElementLocated(By.id("loginname")));
        WebElement passwordField=driver.findElement(By.id("loginpassword"));
        WebElement loginButton=driver.findElement(By.id("loginbutton"));
        nameField.clear();
        nameField.sendKeys("anna");
        passwordField.clear();
        passwordField.sendKeys("123456");
        loginButton.submit();
        WebElement mainContent = wdw.until(ExpectedConditions.presenceOfElementLocated(By.id("maincontent")));
        Assert.assertNotNull(mainContent);
        driver.close();
    }

    @Test
    public void loginWithBadCredentialsTest() throws IOException {
        driver = getWebdriver();
        driver.get("http://localhost:"+port+"/");
        WebDriverWait wdw = new WebDriverWait(driver, 10);
        WebElement nameField = wdw.until(ExpectedConditions.presenceOfElementLocated(By.id("loginname")));
        WebElement passwordField=driver.findElement(By.id("loginpassword"));
        WebElement loginButton=driver.findElement(By.id("loginbutton"));
        nameField.clear();
        nameField.sendKeys("anna4");
        passwordField.clear();
        passwordField.sendKeys("123456xx");
        loginButton.submit();
        WebElement mainContent = null;
        try {
            mainContent = (new WebDriverWait(driver, 2))
                    .until(ExpectedConditions.presenceOfElementLocated(By.id("maincontent")));
        }catch (Exception e){

        }
        Assert.assertNull(mainContent);
        driver.close();
    }


}
