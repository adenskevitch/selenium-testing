package com.solvd.seleniumtesting.service;

import com.solvd.seleniumtesting.factory.Service;
import com.solvd.seleniumtesting.page.CatalogPage;
import org.openqa.selenium.WebDriver;

public class CatalogService implements Service<CatalogService> {

    private CatalogPage catalogPage;
    private WebDriver webDriver;

    public CatalogPage getCatalogPage() {
        return catalogPage;
    }

    @Override
    public CatalogService getService() {
        return this;
    }

    public static Builder builder() {
        return new Builder(new CatalogService());
    }

    public static class Builder {
        private final CatalogService catalogService;

        private Builder(CatalogService catalogService) {
            this.catalogService = catalogService;
        }

        public Builder webDriver(WebDriver webDriver) {
            catalogService.webDriver = webDriver;
            return this;
        }

        public Builder catalogPage() {
            catalogService.catalogPage = new CatalogPage(catalogService.webDriver);
            return this;
        }

        public CatalogService build() {
            return catalogService;
        }
    }
}
