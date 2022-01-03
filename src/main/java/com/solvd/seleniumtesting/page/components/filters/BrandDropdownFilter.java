package com.solvd.seleniumtesting.page.components.filters;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class BrandDropdownFilter extends AbstractUIObject {

    @FindBy(xpath = ".//input[@type='text']")
    private ExtendedWebElement brandInputField;

    @FindBy(xpath = ".//*[contains(text(),'Все марки')]/following-sibling::ul/descendant-or-self::li")
    private List<ExtendedWebElement> brandList;

    public BrandDropdownFilter(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public ExtendedWebElement getBrandInputField() {
        return brandInputField;
    }

    public List<ExtendedWebElement> getBrandList() {
        return brandList;
    }
}
