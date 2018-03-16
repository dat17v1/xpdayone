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

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FuntionalTest {

    @LocalServerPort
    int port;


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


}
