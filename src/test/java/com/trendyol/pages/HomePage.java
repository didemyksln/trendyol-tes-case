package com.trendyol.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {

        super(driver);
    }

    String baseURL = "http://www.trendyol.com/";
    By signIn = By.xpath("//div[@class='account-nav-item user-login-container']");

    public HomePage goToTrendyol() {
        driver.get(baseURL);
        return this;
    }

    public LoginPage goToLoginPage() {
        click(signIn);
        return new LoginPage(driver);
    }


}

