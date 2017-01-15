package com.wiley.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;


public class WileyStudentsPage {
    private WebDriver driver;

    @FindBy(css = "div[id='page-title'] > h1")
    public WebElement header;

    @FindBy(xpath = "//*[@id='sidebar']/div/ul/li/ul")
    public  WebElement resourcesForItems;

    @FindBy(xpath = "//*[@id='sidebar']/div/ul/li/ul/li[1]/a")
    public  WebElement authorsItem;

    @FindBy(xpath = "//*[@id='sidebar']/div/ul/li/ul/li[5]/span")
    public  WebElement studentsItem;

//    @FindBy(css = "li[class='header-menu-crumb-item'] > a")
//    private List<WebElement> crumbsList;

    public WileyStudentsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

//    public void assertCrumbs(String expectedText) {
//        Assert.assertEquals(crumbsListParent.getText().replaceAll("\n", ""), expectedText, "Wrong crumbs!");
//    }

    public void assertStudentsPageUrl()
    {
        Assert.assertEquals(driver.getCurrentUrl(),
                "http://eu.wiley.com/WileyCDA/Section/id-404702.html",
                "Wrong Students URL!");
    }

    public void checkHeader()
    {
        if (!header.isDisplayed()) System.out.println("No header on Students page!");
    }

    public void assertResourcesForItems(String expectedText)
    {
        Assert.assertEquals(resourcesForItems.getText().replaceAll("\n", ""), expectedText,
                "Wrong items in 'Resources For' menu!");

    }

    public void verifyStudentsItem()
    {
        Assert.assertNotEquals(authorsItem.getCssValue("color"), studentsItem.getCssValue("color"), "Students item has incorrect style!");
        Assert.assertNotEquals(studentsItem.isEnabled(), false, "Students item is clickable! (should not be)");
    }

}
