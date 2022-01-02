package com.solvd.seleniumtesting.page;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class SearchModal extends AbstractPage {

    @FindBy(xpath = "//input/parent::div[contains(@class,'bar-wrapper')]")
    private ExtendedWebElement iframeSearchField;
    @FindBy(xpath = "//a[contains(text(),'Автомобили')]")
    private ExtendedWebElement categoryLink;

    public SearchModal(WebDriver driver) {
        super(driver);
    }

    public ExtendedWebElement getIframeSearchField() {
        return iframeSearchField;
    }

    public ExtendedWebElement getCategoryLink() {
        return categoryLink;
    }
}
