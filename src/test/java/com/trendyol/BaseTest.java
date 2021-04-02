package com.trendyol;

import com.trendyol.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class BaseTest {

    public WebDriver driver;
    public HomePage homePage;

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeClass
    @Parameters("browser")
    public void classLevelSetup(@Optional("chrome") String browser) {

        if (browser.equalsIgnoreCase("chrome")) {

            Map<String, Object> prefs = new HashMap<String, Object>();

            prefs.put("profile.default_content_setting_values.notifications", 2);

            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", prefs);

            System.setProperty("webdriver.chrome.driver", "/Users/didemyukselen/Downloads/trendyolcase/src/test/resources/drivers/mac/chromedriver");
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();

            driver.get("http://www.trendyol.com");
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        } else {
            System.setProperty("webdriver.chrome.driver", "/Users/didemyukselen/Downloads/trendyolcase/src/test/resources/drivers/mac/geckodriver");
            driver = new FirefoxDriver();
            driver.manage().window().maximize();

            driver.get("http://www.trendyol.com");
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        }

    }

    @BeforeMethod
    public void methodLevelSetup() {
        homePage = new HomePage(driver);
    }


    @AfterClass
    public void teardown() {
        driver.quit();
    }
}