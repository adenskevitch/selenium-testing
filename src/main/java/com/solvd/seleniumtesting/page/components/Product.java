package com.solvd.seleniumtesting.page.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class Product extends AbstractUIObject {

    @FindBy(xpath = ".//*[contains(@class,'description_car')]")
    private ExtendedWebElement carBodyInfo;

    @FindBy(xpath = ".//*[contains(@class,'link_middle')]")
    private ExtendedWebElement productTitle;

    @FindBy(xpath = ".//*[contains(@class,'description_chassis')]")
    private ExtendedWebElement driverSystemInfo;

    public Product(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public ExtendedWebElement getCarBodyInfo() {
        return carBodyInfo;
    }

    public ExtendedWebElement getProductTitle() {
        return productTitle;
    }

    public ExtendedWebElement getDriverSystemInfo() {
        return driverSystemInfo;
    }
}
