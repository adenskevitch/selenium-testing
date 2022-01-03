package com.solvd.seleniumtesting.page;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class CatalogPage extends AbstractPage {

    @FindBy(xpath = "//*[@id='schema-products']/*[contains(text(),'Упс')]")
    private ExtendedWebElement failMessage;

    public CatalogPage(WebDriver driver) {
        super(driver);
    }

    public ExtendedWebElement getFailMessage() {
        return failMessage;
    }
}
