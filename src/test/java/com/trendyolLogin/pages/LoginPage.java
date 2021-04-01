package com.trendyolLogin.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static org.testng.Assert.assertEquals;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    String urlAfterSuccessLogin = "https://www.trendyol.com/butik/liste";
    By loginBtn = By.xpath("//button[@type='submit']");
    By emailText = By.id("login-email");
    By passWordTesxt = By.id("login-password-input");
    By errorMessage = By.xpath("//div//span[@class='message']");


    public LoginPage loginToTrendyol(String email, String password) {
        writeText(emailText, email);
        writeText(passWordTesxt, password);
        click(loginBtn);
        return this;
    }


    public LoginPage verifyLoginEmail(String expectedText) {
        assertEquals(getErrorMessage(), expectedText);
        return this;
    }

    public LoginPage verifyLoginPassword(String expectedText) {
        assertEquals(errorMessage, expectedText);
        return this;
    }


    public void verifyLoginSuccessful(String expectedUrl) {
        waitForPageLoadComplete(driver,10);
        Assert.assertTrue(driver.getCurrentUrl().contains(expectedUrl));
    }


    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }

    public void waitForPageLoadComplete(WebDriver driver, int specifiedTimeout) {
        Wait<WebDriver> wait = new WebDriverWait(driver, specifiedTimeout);
        wait.until(driver1 -> String
                .valueOf(((JavascriptExecutor) driver1).executeScript("return document.readyState"))
                .equals("complete"));
    }
}

