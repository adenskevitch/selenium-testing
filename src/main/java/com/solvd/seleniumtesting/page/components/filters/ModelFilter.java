package com.solvd.seleniumtesting.page.components.filters;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class ModelFilter extends AbstractUIObject {

    @FindBy(xpath = ".//*[contains(@class,'faux') and text()='Марка']")
    private ExtendedWebElement brand;
    @FindBy(xpath = ".//*[contains(@class,'faux') and text()='Модель']")
    private ExtendedWebElement model;
    @FindBy(xpath = ".//*[contains(@class,'faux') and text()='Поколение']")
    private ExtendedWebElement generation;

    @FindBy(xpath = ".//*[contains(@class,'faux') and text()='Марка']/parent::div/following-sibling::*")
    private BrandDropdownFilter brandDropdownFilter;

    public ModelFilter(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public ExtendedWebElement getBrand() {
        return brand;
    }

    public ExtendedWebElement getModel() {
        return model;
    }

    public ExtendedWebElement getGeneration() {
        return generation;
    }

    public BrandDropdownFilter getBrandDropdownFilter() {
        return brandDropdownFilter;
    }
}
