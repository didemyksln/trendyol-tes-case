package com.trendyolLogin.utils.pageUtility;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Logger;

public class PageUtility {

    private static final Logger LOGGER = Logger.getLogger(com.trendyol.utility.PageUtility.class.getName());

    public static  void closeFancyPopUp(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.findElement(By.xpath("//a[@title='Close']")).click();

    }

    public static void closeNotificationPopUp(WebDriver driver) {

        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.findElement(By.xpath("//div[@class='modal-close']")).click();

    }

}
