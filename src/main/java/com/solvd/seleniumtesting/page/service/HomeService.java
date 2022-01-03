package com.solvd.seleniumtesting.page.service;

import com.solvd.seleniumtesting.page.AbPage;
import com.solvd.seleniumtesting.page.HomePage;
import com.solvd.seleniumtesting.page.SearchModal;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class HomeService {

    private final HomePage homePage;

    public HomeService(WebDriver webDriver) {
        this.homePage = new HomePage(webDriver);
    }

    public AbPage selectAbSection(String menuSection) {
        if (homePage.getTopMenu().isUIObjectPresent()) {
            homePage.getTopMenu().getMenuItems().stream()
                    .filter(menuItem -> menuSection.equals(menuItem.getText()))
                    .findFirst().orElseThrow(() -> new NoSuchElementException("Section not found")).click();
            return new AbPage(homePage.getDriver());
        }
        throw new RuntimeException(String.format("Unable to open section: %s", menuSection));
    }

    public CatalogService inputSearchData(WebDriver webDriver, SearchModal searchModal, String searchData) {
        homePage.getSearchField().click();
        homePage.getSearchField().type(searchData);
        webDriver.switchTo().frame(searchModal.getRootElement());
        searchModal.getCategoryLink().click();
        return new CatalogService(webDriver);
    }

    public HomePage getHomePage() {
        return homePage;
    }
}
