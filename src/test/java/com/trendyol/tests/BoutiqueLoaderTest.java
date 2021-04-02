package com.trendyol.tests;

import com.google.common.collect.Lists;
import com.trendyol.BaseTest;
import com.trendyol.model.BoutiquePageResponse;
import com.trendyol.utils.reports.ExtentTestManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.trendyol.utils.FileUtils.writeToCSVFile;

public class BoutiqueLoaderTest extends BaseTest {

    @Test
    public void loadBoutiqueLinks(Method method) throws IOException {

        ExtentTestManager.startTest(method.getName(), "Test Scenario: Valid Login Scenario with right email and password.");
        closeFancyPopUp(driver);
        List<BoutiquePageResponse> boutiquePageResponseList = fetchBoutiquePages();
        List<List<String>> contentList = new ArrayList<>();
        for (BoutiquePageResponse boutiquePageResponse : boutiquePageResponseList) {
            contentList.add(Lists.newArrayList(boutiquePageResponse.getUrl(), String.valueOf(boutiquePageResponse.getResponseCode())));
        }
        writeToCSVFile("boutique-load-response-code.csv", new String[]{"Boutique Link", "Response Code"}, contentList);
    }


    private List<BoutiquePageResponse> fetchBoutiquePages() throws IOException {
        List<BoutiquePageResponse> boutiquePageResponseList = new ArrayList<>();
        List<WebElement> elements = driver.findElements(By.xpath("//article[@class='component-item']/a"));
        for (WebElement element : elements) {
            String url = element.getAttribute("href");
            int responseCode = doGet(url);
            boutiquePageResponseList.add(new BoutiquePageResponse(url, responseCode));
        }
        return boutiquePageResponseList;
    }

    private static int doGet(String href) throws IOException {

        URL url = new URL(href);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        return con.getResponseCode();

    }

    private static void closeFancyPopUp(WebDriver driver) {
        driver.findElement(By.xpath("//a[@title='Close']")).click();
    }

}
