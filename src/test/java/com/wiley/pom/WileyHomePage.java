package com.wiley.pom;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;


public class WileyHomePage {

    private final WebDriver driver;

    @FindBy(css = "div[id='links-site'] > ul")
    public WebElement navigationLinksList;

    @FindBy(css = "div[id='homepage-links'] > ul")
    public WebElement resourcesList;

    @FindBy(css = "a[href='/WileyCDA/Section/id-404702.html']")
    public WebElement studentsItem;

    @FindBy(id = "id31")
    public WebElement submitButton;

    @FindBy(id = "EmailAddress")
    public WebElement emailInputField;

    @FindBy(id = "query")
    public WebElement searchInputField;

    @FindBy(xpath = "//*[@id='links-site']/form/fieldset/input[2]")
    public WebElement searchButton;

    @FindBy(css = "li[class='resource-institutions']")
    public WebElement institutionsItem;



    public WileyHomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }


    public void assertNavigationLinks(String expectedText) {
        Assert.assertEquals(navigationLinksList.getText().replaceAll("\n", ""), expectedText, "Wrong navigation link!");
    }

    public void assertResources(String expectedText) {
        Assert.assertEquals(resourcesList.getText().replaceAll("\n", ""), expectedText, "Wrong resources!");
    }

    public WileyStudentsPage clickStudentsItem() {
        studentsItem.click();
        return new WileyStudentsPage(this.driver);
    }

    public void checkSignUpAlerts()
    {
        submitButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(), "Please enter email address", "Wrong alert text without email entered!");
        alert.accept();

        emailInputField.sendKeys("test.test.com");
        submitButton.click();
        Alert alert2 = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert2.getText(), "Invalid email address.", "Wrong alert text with invalid email entered!");
        alert2.accept();
    }

    public SearchResultsPage searchForDummies() {
        searchInputField.sendKeys("for dummies");
        searchButton.click();
        return new SearchResultsPage(this.driver);
    }

    public InstitutionsPage clickInstitutions()
    {
        institutionsItem.click();
        List<String> browserTabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(browserTabs.get(1));
        return new InstitutionsPage(this.driver);
    }
}
