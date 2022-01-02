package com.solvd.seleniumtesting.page.service;

import com.solvd.seleniumtesting.page.AbPage;
import com.solvd.seleniumtesting.page.HomePage;
import com.solvd.seleniumtesting.page.SearchModal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomeService {

    private final HomePage homePage;

    public HomeService(WebDriver webDriver) {
        this.homePage = new HomePage(webDriver);
    }

    public AbPage selectAbSection() {
        if (homePage.getTopMenu().isUIObjectPresent()) {
            homePage.getTopMenu().getMenuItems().stream()
                    .filter(menuItem -> "Автобарахолка".equals(menuItem.getText()))
                    .findFirst().get().click();
            return new AbPage(homePage.getDriver());
        }
        throw new RuntimeException("Unable to open section: Автобарахолка");
    }

    public CatalogService inputSearchData(WebDriver webDriver, SearchModal searchModal) {
        homePage.getSearchField().click();
        homePage.getSearchField().type("Автомобили");
        searchModal.setRootElement(webDriver.findElement(By.xpath("//iframe[contains(@class,'modal-iframe')]")));
        webDriver.switchTo().frame(searchModal.getRootElement());
        searchModal.getCategoryLink().click();
        return new CatalogService(webDriver);
    }

    public HomePage getHomePage() {
        return homePage;
    }
}
