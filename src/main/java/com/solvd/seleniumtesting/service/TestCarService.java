package com.solvd.seleniumtesting.service;

import com.solvd.seleniumtesting.page.TestCarPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestCarService {

    private final TestCarPage testCarPage;

    public TestCarService(WebDriver webDriver) {
        this.testCarPage = new TestCarPage(webDriver);
    }

    public void selectCar(WebDriver webDriver, AbService abService) {
        abService.getAbPage().getProductBlock().getProductList().stream()
                .findFirst().get().getProductTitle().click();
        WebDriverWait wait = new WebDriverWait(webDriver, 5);
        wait.until(driver -> getTestCarPage().getPageTitle().isPresent());
        webDriver.switchTo().parentFrame();
    }

    public TestCarPage getTestCarPage() {
        return testCarPage;
    }
}
