package com.solvd.seleniumtesting.page.components.filters;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DriveSystemFilter extends AbstractUIObject {

    @FindBy(xpath = ".//*[contains(@class,'checkbox-list')]//*[contains(@class,'checkbox-sign')]")
    private List<ExtendedWebElement> driverSystems;

    public DriveSystemFilter(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public List<ExtendedWebElement> getDriverSystems() {
        return driverSystems;
    }
}
