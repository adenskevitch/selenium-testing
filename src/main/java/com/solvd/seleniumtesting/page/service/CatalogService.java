package com.solvd.seleniumtesting.page.service;

import com.solvd.seleniumtesting.page.CatalogPage;
import org.openqa.selenium.WebDriver;

public class CatalogService {

    private final CatalogPage catalogPage;

    public CatalogService(WebDriver webDriver) {
        this.catalogPage = new CatalogPage(webDriver);
    }

    public CatalogPage getCatalogPage() {
        return catalogPage;
    }
}
