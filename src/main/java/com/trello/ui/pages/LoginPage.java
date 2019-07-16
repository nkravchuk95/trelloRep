package com.trello.ui.pages;
import com.trello.ui.core.Constans;
import org.openqa.selenium.By;

import com.trello.ui.core.Elem;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static com.trello.ui.core.BrowserFactory.*;

public class LoginPage {

    private static final String PATH = "/login";

    public Elem emailFld = new Elem(By.cssSelector("#user"),"Email");
    public Elem passwordFld = new Elem(By.cssSelector("#password"),"Pass");
    public Elem loginBth = new Elem(By.cssSelector("#login"),"Butt");

    public void open(){

        get(Constans.URL + PATH);
        Assert.assertTrue(isOpened(),"Page login ["+PATH+"] not open");
    }

    private boolean isOpened() {
        return loginBth.isPresent();
    }

    public void login(String email, String pass){

        emailFld.type(email);
        passwordFld.type(pass);
        loginBth.click();
        new WebDriverWait(driver(), 10).until(ExpectedConditions.urlToBe("https://trello.com/mavenproject3/boards"));

    }
}
