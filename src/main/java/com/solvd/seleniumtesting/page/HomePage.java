package com.solvd.seleniumtesting.page;

import com.qaprosoft.carina.core.foundation.utils.Configuration;
import com.qaprosoft.carina.core.foundation.utils.R;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import com.solvd.seleniumtesting.page.components.TopMenu;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class HomePage extends AbstractPage {

    @FindBy(xpath = "//*[contains(@class,'b-top-menu')]")
    private TopMenu topMenu;

    @FindBy(xpath = "//input[contains(@class,'fast-search')]")
    private ExtendedWebElement searchField;

    public HomePage(WebDriver driver) {
        super(driver);
        setPageAbsoluteURL(R.CONFIG.get(Configuration.Parameter.URL.getKey()));
    }

    public TopMenu getTopMenu() {
        return topMenu;
    }

    public ExtendedWebElement getSearchField() {
        return searchField;
    }
}
