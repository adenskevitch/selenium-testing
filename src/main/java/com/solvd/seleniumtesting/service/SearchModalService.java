package com.solvd.seleniumtesting.service;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.solvd.seleniumtesting.factory.Service;
import com.solvd.seleniumtesting.page.SearchModal;
import org.openqa.selenium.WebDriver;

/*
Decorator pattern realisation example
 */
public class SearchModalService extends ServiceClass implements Service<SearchModalService> {

    private final SearchModal searchModal;
    private final WebDriver webDriver;

    public SearchModalService(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.searchModal = new SearchModal(webDriver);
    }

    public SearchModal getSearchModal() {
        return searchModal;
    }

    @Override
    public SearchModalService getService() {
        return this;
    }

    @Override
    public void onEvent() {
        webDriver.switchTo().frame(getSearchModal().getRootElement());
        clickOnElement(searchModal.getCategoryLink());
    }

    @Override
    public void clickOnElement(ExtendedWebElement webElement) {
        if (webElement.isVisible()) {
            webElement.click();
        }
    }
}
