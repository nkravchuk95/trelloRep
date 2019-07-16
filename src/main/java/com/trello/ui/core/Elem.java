package com.trello.ui.core;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.trello.ui.core.BrowserFactory.driver;
import static com.trello.ui.core.BrowserFactory.getWebDriverWait;

public class Elem {
    private By by;
    private String name;

    public Elem(By by,String name){
        this.by = by;
        this.name = name;
    }

    public Elem(By by){
        this(by, "");
    }


    public WebElement find(){
        return getWebDriverWait(10).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void click(){
        find().click();
    }

    public void type(String text){
        find().clear();
        find().sendKeys(text);
    }

    public boolean isPresent(){
        try {
            getWebDriverWait(10).until(ExpectedConditions.presenceOfElementLocated(by));
            return true;
        } catch (TimeoutException e){
            return false;
        }
    }
}