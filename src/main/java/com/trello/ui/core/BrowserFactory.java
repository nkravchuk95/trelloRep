package com.trello.ui.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.util.Set;

public class BrowserFactory {
    private Logger logger = LoggerFactory.getLogger(BrowserFactory.class);
    private static WebDriver driver;

    @BeforeTest
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        logger.info("BROWSER STARTED");
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
        logger.info("BROWSER CLOSED");

    }

    public static WebDriver driver(){
        return driver;
    }


    public static void get(String url){
        driver().get(url);
        new WebDriverWait(driver(), 40).until(ExpectedConditions.urlToBe(url));
    }

    public static WebDriverWait getWebDriverWait(long timeout){
        return new WebDriverWait(driver(), timeout);
    }

}
