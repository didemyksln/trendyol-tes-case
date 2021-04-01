package com.trendyol;

import com.trendyol.model.BoutiquePageResponse;
import com.trendyolLogin.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BoutiqueLoaderTest extends Base {

    public WebDriver driver;

    @BeforeTest
    public void initialize() {
        driver = initializeDriver("chrome");
    }

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

    private List<BoutiquePageResponse> fetchBoutiquePages() {
        List<BoutiquePageResponse> boutiquePageResponseList = new ArrayList<>();
        List<WebElement> elements = driver.findElements(By.xpath("//article[@class='component-item']/a"));
        for (WebElement element : elements) {
            String url = element.getAttribute("href");
            int responseCode = doGet(url);
            boutiquePageResponseList.add(new BoutiquePageResponse(url, responseCode));
        }
        return boutiquePageResponseList;
    }

    private static int doGet(String href) {
        try {
            URL url = new URL(href);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            return con.getResponseCode();
        } catch (IOException e) {
            // TODO :: create a new type exception
            throw new RuntimeException(e);
        }
    }

    private static void closeFancyPopUp(WebDriver driver) {
        driver.findElement(By.xpath("//a[@title='Close']")).click();
    }

}
