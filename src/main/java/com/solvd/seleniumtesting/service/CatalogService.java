package com.solvd.seleniumtesting.service;

import com.solvd.seleniumtesting.factory.Service;
import com.solvd.seleniumtesting.page.CatalogPage;
import org.openqa.selenium.WebDriver;

public class CatalogService implements Service<CatalogService> {

    private final CatalogPage catalogPage;

    public CatalogService(WebDriver webDriver) {
        this.catalogPage = new CatalogPage(webDriver);
    }

    public CatalogPage getCatalogPage() {
        return catalogPage;
    }

    @Override
    public CatalogService getService() {
        return this;
    }
}
