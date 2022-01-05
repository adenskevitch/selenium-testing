package com.solvd.seleniumtesting.service;

import com.solvd.seleniumtesting.factory.Service;
import com.solvd.seleniumtesting.page.SearchModal;
import org.openqa.selenium.WebDriver;

public class SearchModalService implements Service<SearchModalService> {

    private final SearchModal searchModal;

    public SearchModalService(WebDriver webDriver) {
        this.searchModal = new SearchModal(webDriver);
    }

    public void selectCategory(WebDriver webDriver) {
        webDriver.switchTo().frame(getSearchModal().getRootElement());
        searchModal.getCategoryLink().click();
    }

    public SearchModal getSearchModal() {
        return searchModal;
    }

    @Override
    public SearchModalService getService() {
        return this;
    }
}
