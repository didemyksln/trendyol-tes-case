package com.trendyolLogin;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class BaseTest {

    public WebDriver driver;


    public WebDriver initializeDriver(String browser) {

        // TODO::

        //Create a map to store  preferences
        Map<String, Object> prefs = new HashMap<String, Object>();

        prefs.put("profile.default_content_setting_values.notifications", 2);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);

        System.setProperty("webdriver.chrome.driver", "/Users/didemyukselen/Downloads/trendyolcase/src/test/resources/drivers/mac/chromedriver");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        driver.get("http://www.trendyol.com");
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        return driver;
    }

}
