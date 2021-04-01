package com.trendyolLogin.tests;

import com.trendyolLogin.UserInformation;
import com.trendyolLogin.utils.extentReports.ExtentTestManager;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LoginCasesTest extends BaseDeneme {


    @DataProvider(name = "data-provider")
    public Object[][] getData(Method m) {

        List<UserInformation> userInformationList = readFromCSVFile();

        if (m.getName().equals("loginTestWithValidEmailPassword")) {

            UserInformation userInformation = getRelatedUserInformation(userInformationList, "ValidEmailPassword");
            return new Object[][]{{userInformation}};
        } else if (m.getName().equals("loginTestWithInValidEmail")) {

            UserInformation userInformation = getRelatedUserInformation(userInformationList, "InvalidEmail");
            return new Object[][]{{userInformation}};
        }

        return new Object[][]{};

    }


    @Test(dataProvider = "data-provider")
    public void loginTestWithValidEmailPassword(UserInformation userInformation, Method method) {

        ExtentTestManager.startTest(method.getName(), "Invalid Login Scenario with invalid username and password.");
        closeFancyPopUp(driver);

        homePage.goToTrendyol()
                .goToLoginPage()
                .loginToTrendyol(userInformation.getEmail(), userInformation.getPassword())
                .verifyLoginSuccessful("https://www.trendyol.com/butik/kadin");


    }

    @Test(dataProvider = "data-provider", description = "Valid login scenario with right email and password.")
    public void loginTestWithInValidEmail(UserInformation userInformation, Method method) {

        ExtentTestManager.startTest(method.getName(), "Invalid Login Scenario with invalid username and password.");
        String message = "E-posta adresiniz ve/veya şifreniz hatalı.";

        com.trendyolLogin.pages.HomePage homePage = new com.trendyolLogin.pages.HomePage(driver);
        closeFancyPopUp(driver);
        homePage.goToTrendyol()
                .goToLoginPage()
                .loginToTrendyol(userInformation.getEmail(), userInformation.getPassword())
                .verifyLoginEmail(message);

    }


    public List<UserInformation> readFromCSVFile() {

        Reader reader;
        List<UserInformation> listUserInformation = new ArrayList<>();

        {
            try {
                reader = Files.newBufferedReader(Paths.get("userLoginInfo.csv"));

                Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim().parse(reader);

                for (CSVRecord record : records) {

                    String testCase = record.get("TestCaseName");
                    String email = record.get("Email");
                    String password = record.get("Password");

                    UserInformation userInformation = new UserInformation(testCase, email, password);
                    listUserInformation.add(userInformation);

                }


                reader.close();


            } catch (IOException e) {
                e.printStackTrace();
            }

            return listUserInformation;

        }
    }

    private UserInformation getRelatedUserInformation(List<UserInformation> userInformationList, String testCase) {

        for (UserInformation userInformation : userInformationList) {
            if (userInformation.getTestCase().equals(testCase))
                return userInformation;
        }
        return null;
    }


    private static void closeFancyPopUp(WebDriver driver) {
        driver.findElement(By.xpath("//a[@title='Close']")).click();
    }

}