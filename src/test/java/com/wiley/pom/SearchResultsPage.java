package com.wiley.pom;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class SearchResultsPage {
    private WebDriver driver;

    public SearchResultsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(css = "h2[class='matches']")
    public WebElement matches;

    @FindBy(css = "div[class='product-title'] > a")
    public WebElement product;

    public void checkIfMatchesArePresent()
    {
        Assert.assertEquals(matches.isDisplayed(), true, "Matches were not found!");
    }

    public ProductPage clickProduct()
    {
        product.click();
        return new ProductPage(this.driver);
    }

}
