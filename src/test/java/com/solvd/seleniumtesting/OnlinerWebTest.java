package com.solvd.seleniumtesting;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.solvd.seleniumtesting.factory.Page;
import com.solvd.seleniumtesting.factory.Service;
import com.solvd.seleniumtesting.factory.ServiceFactory;
import com.solvd.seleniumtesting.page.AbPage;
import com.solvd.seleniumtesting.page.HomePage;
import com.solvd.seleniumtesting.page.SelectCarPage;
import com.solvd.seleniumtesting.service.*;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class OnlinerWebTest implements IAbstractTest {

    private static Service<HomeService> home;
    private static HomePage homePage;
    private static Service<AbService> abService;
    private static AbPage abPage;
    private static ServiceFactory factory;

    @BeforeSuite
    public void setWindowParameters() {
        getDriver().manage().window().maximize();
        factory = new ServiceFactory();
        home = factory.getService(getDriver(), Page.HOME);
        homePage = home.getService().getHomePage();
    }

    @Test(dependsOnMethods = "verificationLoadHomePage")
    @Parameters({"searchData"})
    public void verificationFastSearchAuto(String searchData) {
        home.getService().inputSearchData(searchData);
        Service<SearchModalService> searchModal = factory.getService(getDriver(), Page.SEARCHMODAL);
        searchModal.getService().selectCategory(getDriver());
        Service<CatalogService> catalog = factory.getService(getDriver(), Page.CATALOG);
        catalog.getService().getCatalogPage().getFailMessage();
        Assert.assertTrue(catalog.getService().getCatalogPage().getFailMessage().getText().contains("Упс"));
    }

    @Test
    public void verificationLoadHomePage() {
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened");
        Assert.assertTrue(homePage.getTopMenu().isUIObjectPresent(), "Main menu bar does not present");
    }

    @Test(dependsOnMethods = "verificationLoadHomePage")
    @Parameters({"menuSection"})
    public void verificationRedirectToAbPage(String menuSection) {
        Assert.assertTrue(home.getService().selectSection(menuSection));
        abService = factory.getService(getDriver(), Page.AB);
        abPage = abService.getService().getAbPage();
        Assert.assertTrue(abPage.getFilterBlock().isUIObjectPresent(), "Filter block does not present");
    }

    @Test(dependsOnMethods = "verificationRedirectToAbPage")
    @Parameters({"filterCarModel", "filterCarBody", "fileCarDriveSystem"})
    public void verificationAppliedFilters(String filterCarModel, String filterCarBody, String fileCarDriveSystem) {
        abService.getService().applyFilters(filterCarModel, filterCarBody, fileCarDriveSystem);
        SoftAssert softAssert = new SoftAssert();
        abPage.getProductBlock().getProductList()
                .forEach(product -> {
                    softAssert.assertTrue(product.getProductTitle().getText().contains(filterCarModel));
                    softAssert.assertEquals(filterCarBody, product.getCarBodyInfo().getText());
                    softAssert.assertEquals(fileCarDriveSystem, product.getDriverSystemInfo().getText());
                });
        softAssert.assertAll();
    }

    @Test(dependsOnMethods = "verificationAppliedFilters")
    public void verificationRedirectToValidPage() {
        String actTitle = abPage.getTestCar().getText();
        Service<SelectCarService> selectCar = factory.getService(getDriver(), Page.SELECTCAR);
        SelectCarPage selectCarPage = selectCar.getService().getSelectCarPage();
        selectCar.getService().selectCar(getDriver(), abPage);
        String excTitle = selectCarPage.getPageTitle().getText();
        Assert.assertEquals(actTitle, excTitle);
    }
}
