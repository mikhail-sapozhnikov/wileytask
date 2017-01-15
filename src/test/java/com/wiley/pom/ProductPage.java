package com.wiley.pom;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ProductPage {
    private WebDriver driver;

    public ProductPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(css = "div[class='product-title'] > a")
    public WebElement header;

    public void checkIfHeaderEqualsTitle(String title)
    {
        Assert.assertEquals(header.getText(), title, "Incorrect header!");
    }

}
