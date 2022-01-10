package com.solvd.seleniumtesting.page.components.filters;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class CarBodyFilter extends AbstractUIObject {

    @FindBy(xpath = ".//*[contains(@class,'container')]")
    private CarBodyDropdownFilter carBodyDropdownFilter;

    @FindBy(xpath = ".//*[contains(@class,'input-style_placeholder')]")
    private ExtendedWebElement carBody;

    public CarBodyFilter(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public CarBodyDropdownFilter getCarBodyDropdownFilter() {
        return carBodyDropdownFilter;
    }

    public ExtendedWebElement getCarBody() {
        return carBody;
    }
}
