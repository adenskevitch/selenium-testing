package com.solvd.seleniumtesting;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.solvd.seleniumtesting.factory.Page;
import com.solvd.seleniumtesting.factory.Service;
import com.solvd.seleniumtesting.factory.ServiceFactory;
import com.solvd.seleniumtesting.listener.EventHolder;
import com.solvd.seleniumtesting.listener.EventType;
import com.solvd.seleniumtesting.page.AbPage;
import com.solvd.seleniumtesting.page.CatalogPage;
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
    private static Service<CatalogService> catalog;
    private static CatalogPage catalogPage;
    private static Service<SelectCarService> selectCar;
    private static SelectCarPage selectCarPage;
    private static Service<SearchModalService> searchModal;
    private static ServiceFactory factory;
    private static EventHolder eventHolder;

    @BeforeSuite
    public void setWindowParameters() {
        getDriver().manage().window().maximize();
        eventHolder = new EventHolder();
        factory = new ServiceFactory();
        home = factory.getService(getDriver(), Page.HOME);
        homePage = home.getService().getHomePage();
    }

    @Test(dependsOnMethods = "verificationLoadHomePage")
    @Parameters({"searchData"})
    public void verificationFastSearchAuto(String searchData) {
        homePage.open();
        Assert.assertTrue(homePage.isPageOpened(), "Home page is not opened");
        Assert.assertTrue(homePage.getTopMenu().isUIObjectPresent(), "Main menu bar does not present");
        searchModal = factory.getService(getDriver(), Page.SEARCHMODAL);
        eventHolder.addListener(EventType.STARTSEARCH, searchModal.getService());
        home.getService().inputSearchData(searchData);
        eventHolder.notify(EventType.STARTSEARCH);
        catalog = factory.getService(getDriver(), Page.CATALOG);
        catalogPage = catalog.getService().getCatalogPage();
        Assert.assertTrue(catalogPage.getFailMessage().getText().contains("Упс"));
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
        eventHolder.addListener(EventType.PAGELOADED, abService.getService());
        abPage = abService.getService().getAbPage();
        Assert.assertTrue(abPage.getFilterBlock().isUIObjectPresent(), "Filter block does not present");
    }

    @Test(dependsOnMethods = "verificationRedirectToAbPage")
    @Parameters({"filterCarModel", "filterCarBody", "fileCarDriveSystem"})
    public void verificationAppliedFilters(String filterCarModel, String filterCarBody, String fileCarDriveSystem) {
        abService.getService().setCarModel(filterCarModel);
        abService.getService().setCarBody(filterCarBody);
        abService.getService().setDriveSystem(fileCarDriveSystem);
        eventHolder.notify(EventType.PAGELOADED);
        eventHolder.removeListener(EventType.PAGELOADED, abService.getService());
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
        selectCar = factory.getService(getDriver(), Page.SELECTCAR);
        selectCarPage = selectCar.getService().getSelectCarPage();
        selectCar.getService().selectCar(getDriver(), abPage);
        String excTitle = selectCarPage.getPageTitle().getText();
        Assert.assertEquals(actTitle, excTitle);
    }
}
