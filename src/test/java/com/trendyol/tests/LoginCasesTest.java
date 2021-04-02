package com.trendyol.tests;

import com.trendyol.BaseTest;
import com.trendyol.TestCaseInformation;
import com.trendyol.utils.FileUtils;
import com.trendyol.utils.reports.ExtentTestManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.List;

public class LoginCasesTest extends BaseTest {


    @DataProvider(name = "data-provider")
    public Object[][] getData(Method m) {

        List<TestCaseInformation> testCaseInformationList = FileUtils.readFromCSVFile();

        TestCaseInformation testCaseInformation = getRelatedUserInformation(testCaseInformationList, m.getName());
        return new Object[][]{{testCaseInformation}};

    }


    @Test(dataProvider = "data-provider", description = "Valid scenario for login with right email and password.")
    public void successLoginCase(TestCaseInformation testCaseInformation, Method method) {

        ExtentTestManager.startTest(method.getName(), "Test Scenario: Valid Login Scenario with right email and password.");
        closeFancyPopUp(driver);

        homePage.goToTrendyol()
                .goToLoginPage()
                .loginToTrendyol(testCaseInformation.getEmail(), testCaseInformation.getPassword())
                .verifyLoginSuccessful("https://www.trendyol.com/butik");


    }


    @Test(dataProvider = "data-provider", description = "Invalid scenario for wrong email and password.")
    public void invalidLoginCase(TestCaseInformation testCaseInformation, Method method) {

        ExtentTestManager.startTest(method.getName(), "Test Scenario: Invalid Login Scenario with invalid email and password.");
        closeFancyPopUp(driver);

        homePage.goToTrendyol()
                .goToLoginPage()
                .loginToTrendyol(testCaseInformation.getEmail(), testCaseInformation.getPassword())
                .verifyLoginSuccessful("https://www.trendyol.com/butik");

    }

    @Test(dataProvider = "data-provider", description = "Check error message for wrong email.")
    public void checkErrorMessageForInvalidEmail(TestCaseInformation testCaseInformation, Method method) {

        ExtentTestManager.startTest(method.getName(), "Test Scenario: Check Login Scenario with invalid email address.");
        closeFancyPopUp(driver);

        homePage.goToTrendyol()
                .goToLoginPage()
                .loginToTrendyol(testCaseInformation.getEmail(), testCaseInformation.getPassword())
                .verifyLogin("E-posta adresiniz ve/veya şifreniz hatalı.");

    }

    @Test(dataProvider = "data-provider", description = "Check error message for wrong password.")
    public void checkErrorMessageForInvalidPassoword(TestCaseInformation testCaseInformation, Method method) {

        ExtentTestManager.startTest(method.getName(), "Test Scenario: Check Login Scenario with invalid password.");
        closeFancyPopUp(driver);

        homePage.goToTrendyol()
                .goToLoginPage()
                .loginToTrendyol(testCaseInformation.getEmail(), testCaseInformation.getPassword())
                .verifyLogin("E-posta adresiniz ve/veya şifreniz hatalı.");

    }

    @Test(dataProvider = "data-provider", description = "Check error message for empty email.")
    public void checkErrorMessageForEmtpyEmail(TestCaseInformation testCaseInformation, Method method) {

        ExtentTestManager.startTest(method.getName(), "Test Scenario: Check Login Scenario with empty email.");
        closeFancyPopUp(driver);

        homePage.goToTrendyol()
                .goToLoginPage()
                .loginToTrendyol(testCaseInformation.getEmail(), testCaseInformation.getPassword())
                .verifyLogin("Lütfen geçerli bir e-posta adresi giriniz.");

    }

    @Test(dataProvider = "data-provider", description = "Check error message for empty email.")
    public void checkErrorMessageForEmptyPassword(TestCaseInformation testCaseInformation, Method method) {

        ExtentTestManager.startTest(method.getName(), "Test Scenario: Check Login Scenario with empty password.");
        closeFancyPopUp(driver);

        homePage.goToTrendyol()
                .goToLoginPage()
                .loginToTrendyol(testCaseInformation.getEmail(), testCaseInformation.getPassword())
                .verifyLogin("Lütfen şifrenizi giriniz.");

    }


    private TestCaseInformation getRelatedUserInformation(List<TestCaseInformation> testCaseInformationList, String testCase) {

        for (TestCaseInformation testCaseInformation : testCaseInformationList) {
            if (testCaseInformation.getTestCase().equals(testCase))
                return testCaseInformation;
        }
        return null;
    }


    private static void closeFancyPopUp(WebDriver driver) {
        driver.findElement(By.xpath("//a[@title='Close']")).click();
    }

}
