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


    @FindBy(xpath = "//input/parent::div[contains(@class,'bar-wrapper')]")
    private ExtendedWebElement iframeSearchField;
    @FindBy(xpath = "//a[contains(text(),'Автомобили')]")
    private ExtendedWebElement categoryLink;

    public HomePage(WebDriver driver) {
        super(driver);
        setPageAbsoluteURL(R.CONFIG.get(Configuration.Parameter.URL.getKey()));
    }

    public AbPage selectAbSection() {
        if (topMenu.isUIObjectPresent()) {
            topMenu.getMenuItems().stream()
                    .filter(menuItem -> "Автобарахолка".equals(menuItem.getText()))
                    .findFirst().get().click();
            return new AbPage(driver);
        }
        throw new RuntimeException("Unable to open section: Автобарахолка");
    }

    public TopMenu getTopMenu() {
        return topMenu;
    }

    public ExtendedWebElement getSearchField() {
        return searchField;
    }

    public ExtendedWebElement getIframeSearchField() {
        return iframeSearchField;
    }

    public ExtendedWebElement getCategoryLink() {
        return categoryLink;
    }
}
