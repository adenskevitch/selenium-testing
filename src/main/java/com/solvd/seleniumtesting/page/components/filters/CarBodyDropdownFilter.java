package com.solvd.seleniumtesting.page.components.filters;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CarBodyDropdownFilter extends AbstractUIObject {

    @FindBy(xpath = ".//input[contains(@type,'checkbox')]/following-sibling::div[contains(@class,'dropdown-style')]/div[contains(@class,'checkbox-sign')]")
    private List<ExtendedWebElement> carBodyList;

    public CarBodyDropdownFilter(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public List<ExtendedWebElement> getCarBodyList() {
        return carBodyList;
    }
}
