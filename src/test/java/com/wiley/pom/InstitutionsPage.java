package com.wiley.pom;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class InstitutionsPage {
    private WebDriver driver;

    public InstitutionsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void checkOpenedTabUrl()
    {
        Assert.assertEquals(driver.getCurrentUrl(), "https://edservices.wiley.com/", "Incorrect URL was opened for Institutions!");
    }
}
