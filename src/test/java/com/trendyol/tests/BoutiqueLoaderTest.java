package com.trendyol.tests;

import com.google.common.collect.Lists;
import com.trendyol.BaseTest;
import com.trendyol.model.BoutiquePageResponse;
import com.trendyol.model.ServerResponse;
import com.trendyol.utils.reports.ExtentTestManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static com.trendyol.utils.FileUtils.writeToCSVFile;

public class BoutiqueLoaderTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(BoutiqueLoaderTest.class);

    @Test
    public void loadBoutiqueLinks(Method method) throws IOException {

        ExtentTestManager.startTest(method.getName(), "Get boutique links and responses and write to csv file.");
        closeFancyPopUp(driver);
        List<BoutiquePageResponse> boutiquePageResponseList = fetchBoutiquePages();
        List<List<String>> contentList = new ArrayList<>();
        for (BoutiquePageResponse boutiquePageResponse : boutiquePageResponseList) {
            contentList.add(Lists.newArrayList(boutiquePageResponse.getUrl(), String.valueOf(boutiquePageResponse.getResponseCode())));
        }
        writeToCSVFile("boutique-load-response-code.csv", new String[]{"Boutique Link", "Response Code"}, contentList);
    }


    @Test
    public void loadBoutiqueImageLinks(Method method) throws IOException {

        ExtentTestManager.startTest(method.getName(), "Get boutique image links and their loading response time and write to csv file.");
        closeFancyPopUp(driver);
        scrollDown(driver);
        List<ServerResponse> serverResponses = fetchBoutiqueImageLink();
        List<List<String>> contentList = new ArrayList<>();
        for (ServerResponse serverResponse : serverResponses) {
            contentList.add(Lists.newArrayList(serverResponse.getUrl(), String.valueOf(serverResponse.getResponseTime()), String.valueOf(serverResponse.getStatusCode())));
        }
        writeToCSVFile("boutique-image-load-time-responce-code.csv", new String[]{"Boutique Image Link", "Responce Time", "Responce Code"}, contentList);

    }

    private void scrollDown(WebDriver driver) {

        for (int i = 0; i < 2000; i++) {

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,5)");
        }

    }


    private List<BoutiquePageResponse> fetchBoutiquePages() throws IOException {
        List<BoutiquePageResponse> boutiquePageResponseList = new ArrayList<>();
        List<WebElement> elements = driver.findElements(By.xpath("//article[@class='component-item']/a"));
        for (WebElement element : elements) {
            String url = element.getAttribute("href");
            int responseCode = fetchUrl(url);
            boutiquePageResponseList.add(new BoutiquePageResponse(url, responseCode));
        }
        return boutiquePageResponseList;
    }

    private List<ServerResponse> fetchBoutiqueImageLink() throws IOException {

        List<WebElement> images = driver.findElements(By.xpath("//span[@class='image-container']/img"));
        List<ServerResponse> serverResponses = new ArrayList<>();

        for (WebElement element : images) {
            String imageUrl = element.getAttribute("src");
            ServerResponse serverResponse = fetchUrlWithTime(imageUrl);
            serverResponses.add(serverResponse);
        }

        return serverResponses;

    }

    private static void closeFancyPopUp(WebDriver driver) {
        driver.findElement(By.xpath("//a[@title='Close']")).click();
    }

    private static ServerResponse fetchUrlWithTime(String url) throws IOException {
        Instant start = Instant.now();
        int statusCode = fetchUrl(url);
        Instant finish = Instant.now();
        return new ServerResponse(statusCode, Duration.between(start, finish).toMillis(), url);
    }

    private static int fetchUrl(String href) throws IOException {
        URL url = new URL(href);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "");
        con.setRequestMethod("GET");

        int statusCode = con.getResponseCode();
        InputStream inputStream = (200 <= statusCode && statusCode <= 299) ? con.getInputStream() : con.getErrorStream();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            // read all data from response
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
            }
        }
        logger.info("Fetching Url " + url + ", status code: " + statusCode);
        return statusCode;
    }

}
