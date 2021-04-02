package com.trendyol.tests;

import com.trendyol.BaseTest;
import com.trendyol.model.BoutiquePageResponse;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BoutiqueLoaderTest extends BaseTest {

    @Test
    public void loadBoutiqueLinks() throws IOException {
        closeFancyPopUp(driver);
        List<BoutiquePageResponse> boutiquePageResponseList = fetchBoutiquePages();
        writeResponseCodeToCSV(boutiquePageResponseList);
    }

    private void writeResponseCodeToCSV(List<BoutiquePageResponse> boutiquePageResponseList) throws IOException {
        try (FileWriter csvWriter = new FileWriter("new.csv")) {
            csvWriter.append("Boutique Link")
                    .append(";")
                    .append("Response Code")
                    .append("\n");

            for (BoutiquePageResponse boutiquePageResponse : boutiquePageResponseList) {
                csvWriter.append(boutiquePageResponse.getUrl())
                        .append(";")
                        .append(String.valueOf(boutiquePageResponse.getResponseCode()))
                        .append("\n");
            }
            csvWriter.flush();
        }
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
