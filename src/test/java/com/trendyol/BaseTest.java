package com.trendyol;

import com.trendyol.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class BaseTest {

    public WebDriver driver;
    public HomePage homePage;

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeMethod
    @Parameters("browser")
    public void classLevelSetup(@Optional("chrome") String browser) throws MalformedURLException {

        if (browser.equalsIgnoreCase("chrome")) {
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), DesiredCapabilities.chrome());
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), DesiredCapabilities.firefox());
        }

        driver.manage().window().maximize();
        driver.get("http://www.trendyol.com");
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        homePage = new HomePage(driver);
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
