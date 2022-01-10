package com.solvd.seleniumtesting.page.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class TopMenu extends AbstractUIObject {

    @FindBy(xpath = ".//*[contains(@class,'main-navigation') and contains(@class,'text')]")
    private List<ExtendedWebElement> menuItems;

    public TopMenu(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public List<ExtendedWebElement> getMenuItems() {
        return menuItems;
    }
}
