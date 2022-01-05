package com.solvd.seleniumtesting.service;

import com.solvd.seleniumtesting.factory.Service;
import com.solvd.seleniumtesting.page.HomePage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class HomeService implements Service<HomeService> {

    private final HomePage homePage;

    public HomeService(WebDriver webDriver) {
        this.homePage = new HomePage(webDriver);
    }

    public boolean selectSection(String menuSection) {
        if (homePage.getTopMenu().isUIObjectPresent()) {
            homePage.getTopMenu().getMenuItems().stream()
                    .filter(menuItem -> menuSection.equals(menuItem.getText()))
                    .findFirst().orElseThrow(() -> new NoSuchElementException("Section not found")).click();
            return true;
        }
        throw new RuntimeException(String.format("Unable to open section: %s", menuSection));
    }

    public void inputSearchData(String searchData) {
        homePage.getSearchField().click();
        homePage.getSearchField().type(searchData);
    }

    public HomePage getHomePage() {
        return homePage;
    }

    @Override
    public HomeService getService() {
        return this;
    }
}
