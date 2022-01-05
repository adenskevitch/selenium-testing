package com.solvd.seleniumtesting;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.solvd.seleniumtesting.page.SearchModal;
import com.solvd.seleniumtesting.service.AbService;
import com.solvd.seleniumtesting.service.CatalogService;
import com.solvd.seleniumtesting.service.HomeService;
import com.solvd.seleniumtesting.service.TestCarService;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
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
    @Parameters({"searchData"})
    public void verificationFastSearchAuto(String searchData) {
        homeService.getHomePage().open();
        Assert.assertTrue(homeService.getHomePage().isPageOpened(), "Home page is not opened");
        Assert.assertTrue(homeService.getHomePage().getSearchField().isPresent(), "Search field does not present");
        SearchModal searchModal = new SearchModal(getDriver());
        CatalogService catalogService = homeService.inputSearchData(getDriver(), searchModal, searchData);
        Assert.assertTrue(catalogService.getCatalogPage().getFailMessage().getText().contains("Упс"));
    }

    @Test(dependsOnMethods = "verificationFastSearchAuto")
    public void verificationLoadHomePage() {
        homeService.getHomePage().open();
        Assert.assertTrue(homeService.getHomePage().isPageOpened(), "Home page is not opened");
        Assert.assertTrue(homeService.getHomePage().getTopMenu().isUIObjectPresent(), "Main menu bar does not present");
    }

    @Test(dependsOnMethods = "verificationLoadHomePage")
    @Parameters({"menuSection"})
    public void verificationRedirectToAbPage(String menuSection) {
        abService = new AbService(homeService.selectAbSection(menuSection));
        Assert.assertTrue(abService.getAbPage().getFilterBlock().isUIObjectPresent(), "Filter block does not present");
    }

    @Test(dependsOnMethods = "verificationRedirectToAbPage")
    @Parameters({"filterCarModel", "filterCarBody", "fileCarDriveSystem"})
    public void verificationAppliedFilters(String filterCarModel, String filterCarBody, String fileCarDriveSystem) {
        abService.applyFilters(filterCarModel, filterCarBody, fileCarDriveSystem);
        SoftAssert softAssert = new SoftAssert();
        abService.getAbPage().getProductBlock().getProductList()
                .forEach(product -> {
                    softAssert.assertTrue(product.getProductTitle().getText().contains(filterCarModel));
                    softAssert.assertEquals(filterCarBody, product.getCarBodyInfo().getText());
                    softAssert.assertEquals(fileCarDriveSystem, product.getDriverSystemInfo().getText());
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
