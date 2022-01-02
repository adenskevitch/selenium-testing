package com.solvd.seleniumtesting;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.solvd.seleniumtesting.page.SearchModal;
import com.solvd.seleniumtesting.page.service.AbService;
import com.solvd.seleniumtesting.page.service.CatalogService;
import com.solvd.seleniumtesting.page.service.HomeService;
import com.solvd.seleniumtesting.page.service.TestCarService;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class OnlinerWebTest implements IAbstractTest {

    private static HomeService homeService;
    private static AbService abService;

    @BeforeSuite
    public void setWindowParameters() {
        getDriver().manage().window().maximize();
        homeService = new HomeService(getDriver());
    }

    @Test
    public void verificationFastSearchAuto() {
        homeService.getHomePage().open();
        Assert.assertTrue(homeService.getHomePage().isPageOpened(), "Home page is not opened");
        Assert.assertTrue(homeService.getHomePage().getSearchField().isPresent(), "Search field does not present");
        SearchModal searchModal = new SearchModal(getDriver());
        CatalogService catalogService = homeService.inputSearchData(getDriver(), searchModal);
        Assert.assertTrue(catalogService.getCatalogPage().getFailMessage().getText().contains("Упс"));
    }

    @Test(dependsOnMethods = "verificationFastSearchAuto")
    public void verificationLoadHomePage() {
        homeService.getHomePage().open();
        Assert.assertTrue(homeService.getHomePage().isPageOpened(), "Home page is not opened");
        Assert.assertTrue(homeService.getHomePage().getTopMenu().isUIObjectPresent(), "Main menu bar does not present");
    }

    @Test(dependsOnMethods = "verificationLoadHomePage")
    public void verificationRedirectToAbPage() {
        abService = new AbService(homeService.selectAbSection());
        Assert.assertTrue(abService.getAbPage().getFilterBlock().isUIObjectPresent(), "Filter block does not present");
    }

    @Test(dependsOnMethods = "verificationRedirectToAbPage")
    public void verificationAppliedFilters() {
        abService.applyFilters();
        SoftAssert softAssert = new SoftAssert();
        abService.getAbPage().getProductBlock().getProductList()
                .forEach(product -> {
                    softAssert.assertTrue(product.getProductTitle().getText().contains("Audi"));
                    softAssert.assertEquals("Внедорожник", product.getCarBodyInfo().getText());
                    softAssert.assertEquals("Полный", product.getDriverSystemInfo().getText());
                });
        softAssert.assertAll();
    }

    @Test(dependsOnMethods = "verificationAppliedFilters")
    public void verificationRedirectToValidPage() {
        String actTitle = abService.getAbPage().getTestCar().getText();
        TestCarService testCarService = new TestCarService(getDriver());
        testCarService.selectCar(getDriver(), abService);
        String excTitle = testCarService.getTestCarPage().getPageTitle().getText();
        Assert.assertEquals(actTitle, excTitle);
    }
}
