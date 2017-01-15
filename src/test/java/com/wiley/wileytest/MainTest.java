package com.wiley.wileytest;

import com.wiley.pom.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class MainTest {

    private WebDriver driver;
    private String baseUrl;

    @BeforeSuite
    public void setUp() throws Exception {
//        System.setProperty("webdriver.chrome.driver", "C:\\Autotests\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        baseUrl = "http://www.wiley.com/WileyCDA/";
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

    }

    @DataProvider(name = "Check navigation links")
    public Object[][] createData() {
        return new Object[][]{
                {"HomeSubjectsAbout WileyContact UsHelp"}
        };
    }

    @DataProvider(name = "Check resources")
    public Object[][] createData2() {
        return new Object[][]{
                {"StudentsAuthorsInstructorsLibrariansSocietiesConferencesBooksellersCorporationsInstitutions"}
        };
    }

    @DataProvider(name = "Check Students page")
    public Object[][] createData3() {
        return new Object[][]{
                {"AuthorsLibrariansBooksellersInstructorsStudentsPurchase Your WileyPLUS Registration CodeGetting Started with WileyPLUS and BlackboardSocietiesCorporate Partners"}
        };
    }


    @Test(dataProvider = "Check navigation links")
    public void testNavigationLinks(String expectedLinkText) throws InterruptedException {
        driver.get(baseUrl);
        WileyHomePage page = new WileyHomePage(driver);
        page.assertNavigationLinks(expectedLinkText);

    }

    @Test(dataProvider = "Check resources")
    public void testResources(String expectedResourcesText) throws InterruptedException {
        driver.get(baseUrl);
        WileyHomePage page = new WileyHomePage(driver);
        page.assertResources(expectedResourcesText);

    }

    @Test(dataProvider = "Check Students page")
    public void checkStudentsPage(String expectedMenuText)
    {
        driver.get(baseUrl);
        WileyHomePage page = new WileyHomePage(driver);
        WileyStudentsPage page1 = page.clickStudentsItem();
        page1.assertStudentsPageUrl();
        page1.checkHeader();
        page1.assertResourcesForItems(expectedMenuText);
        page1.verifyStudentsItem();
    }

    @Test
    public void testSignUpLine() {
        driver.get(baseUrl);
        WileyHomePage page = new WileyHomePage(driver);
        page.checkSignUpAlerts();

    }

    @Test
    public void testSearch() {
        driver.get(baseUrl);
        WileyHomePage page = new WileyHomePage(driver);
        SearchResultsPage page1 = page.searchForDummies();
        page1.checkIfMatchesArePresent();
        ProductPage page2 = page1.clickProduct();
        page2.checkIfHeaderEqualsTitle(page1.product.getText());
    }

    @Test
    public void testInstitutions() {
        driver.get(baseUrl);
        WileyHomePage page = new WileyHomePage(driver);
        InstitutionsPage page1 = page.clickInstitutions();
        page1.checkOpenedTabUrl();
    }


    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
