package com.solvd.seleniumtesting.page;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class TestCarPage extends AbstractPage {

    @FindBy(xpath = "//h1")
    private ExtendedWebElement pageTitle;
    @FindBy(xpath = "//*[contains(@class,'fullscreen-icon')]")
    private ExtendedWebElement fullScreenButton;

    public TestCarPage(WebDriver driver) {
        super(driver);
    }

    public ExtendedWebElement getPageTitle() {
        return pageTitle;
    }

    public ExtendedWebElement getFullScreenButton() {
        return fullScreenButton;
    }
}
