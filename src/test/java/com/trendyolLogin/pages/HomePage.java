package com.trendyolLogin.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;


public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {

        super(driver);
    }

    String baseURL = "http://www.trendyol.com/";
    By signIn = By.xpath("//div[@class='account-nav-item user-login-container']");
    By notificationBar = By.id("notification-popup");

    public HomePage goToTrendyol() {
        driver.get(baseURL);
        return this;
    }

    public LoginPage goToLoginPage() {
        click(signIn);
        return new LoginPage(driver);
    }

    public void verifyPageOpened() {

        Assert.assertTrue(driver.getCurrentUrl().contains("https://www.trendyol.com/butik/"));

    }


}
