package com.solvd.seleniumtesting.service;

import com.solvd.seleniumtesting.factory.Service;
import com.solvd.seleniumtesting.page.AbPage;
import com.solvd.seleniumtesting.page.SelectCarPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SelectCarService implements Service<SelectCarService> {

    private final SelectCarPage selectCarPage;

    public SelectCarService(WebDriver webDriver) {
        this.selectCarPage = new SelectCarPage(webDriver);
    }

    public void selectCar(WebDriver webDriver, AbPage abPage) {
        abPage.getProductBlock().getProductList().stream()
                .findFirst().get().getProductTitle().click();
        WebDriverWait wait = new WebDriverWait(webDriver, 5);
        wait.until(driver -> getSelectCarPage().getPageTitle().isPresent());
        webDriver.switchTo().parentFrame();
    }

    public SelectCarPage getSelectCarPage() {
        return selectCarPage;
    }

    @Override
    public SelectCarService getService() {
        return this;
    }
}
