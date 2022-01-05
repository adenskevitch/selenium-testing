package com.solvd.seleniumtesting.service;

import com.solvd.seleniumtesting.factory.Service;
import com.solvd.seleniumtesting.page.HomePage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class HomeService implements Service<HomeService> {

    private HomePage homePage;
    private WebDriver webDriver;

//    public HomeService(WebDriver webDriver) {
//        this.homePage = new HomePage(webDriver);
//    }

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

    public static Builder builder() {
        return new Builder(new HomeService());
    }

    public static class Builder {

        private final HomeService homeService;

        private Builder(HomeService homeService) {
            this.homeService = homeService;
        }

        public Builder webDriver(WebDriver webDriver) {
            homeService.webDriver = webDriver;
            return this;
        }

        public Builder homePage() {
            homeService.homePage = new HomePage(homeService.webDriver);
            return this;
        }

        public HomeService build() {
            return homeService;
        }
    }
}
