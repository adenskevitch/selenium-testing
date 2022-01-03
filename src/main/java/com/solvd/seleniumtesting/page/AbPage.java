package com.solvd.seleniumtesting.page;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import com.solvd.seleniumtesting.page.components.FilterBlock;
import com.solvd.seleniumtesting.page.components.ProductBlock;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class AbPage extends AbstractPage {

    @FindBy(xpath = "//*[contains(@class,'filter-overflow')]")
    private FilterBlock filterBlock;

    @FindBy(xpath = "//*[contains(@class,'offers-list')]")
    private ProductBlock productBlock;
    @FindBy(xpath = "//*[contains(@class,'link_middle')]")
    private ExtendedWebElement testCar;

    public AbPage(WebDriver driver) {
        super(driver);
    }

    public FilterBlock getFilterBlock() {
        return filterBlock;
    }

    public ProductBlock getProductBlock() {
        return productBlock;
    }

    public ExtendedWebElement getTestCar() {
        return testCar;
    }
}
