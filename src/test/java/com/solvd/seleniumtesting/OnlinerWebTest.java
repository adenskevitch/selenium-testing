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
        searchModal = factory.getService(getDriver(), Page.SEARCH_MODAL);
        eventHolder.addListener(EventType.START_SEARCH, searchModal.getService());
        home.getService().inputSearchData(searchData);
        eventHolder.notify(EventType.START_SEARCH);
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
        eventHolder.addListener(EventType.FILTER_BLOCK_READY, abService.getService());
        abPage = abService.getService().getAbPage();
        Assert.assertTrue(abPage.getFilterBlock().isUIObjectPresent(), "Filter block does not present");
    }

    @Test(dependsOnMethods = "verificationRedirectToAbPage")
    @Parameters({"firstCarModel", "firstCarBody", "firstCarDriveSystem"})
    public void verificationFirstFilters(String firstCarModel, String firstCarBody, String firstCarDriveSystem) {
        abService.getService().setCarModel(firstCarModel);
        abService.getService().setCarBody(firstCarBody);
        abService.getService().setDriveSystem(firstCarDriveSystem);
        /*
        Facade pattern realisation example
         */
        eventHolder.notify(EventType.FILTER_BLOCK_READY);
        eventHolder.removeListener(EventType.FILTER_BLOCK_READY, abService.getService());
        SoftAssert softAssert = new SoftAssert();
        abPage.getProductBlock().getProductList()
                .forEach(product -> {
                    softAssert.assertTrue(product.getProductTitle().getText().contains(firstCarModel));
                    softAssert.assertEquals(firstCarBody, product.getCarBodyInfo().getText());
                    softAssert.assertEquals(firstCarDriveSystem, product.getDriverSystemInfo().getText());
                });
        softAssert.assertAll();
    }

    @Test(dependsOnMethods = "verificationFirstFilters")
    @Parameters({"secondCarModel, secondCarBody, secondCarDriveSystem"})
    public void verificationSecondFilters(String secondCarModel, String secondCarBody, String secondCarDriveSystem) {
        /*
        Strategy pattern realisation example
         */
        abService.getService().setCarModel(secondCarModel);
        abService.getService().setCarBody(secondCarBody);
        abService.getService().setDriveSystem(secondCarDriveSystem);
        /*
        Facade pattern realisation example
         */
        eventHolder.notify(EventType.FILTER_BLOCK_READY);
        eventHolder.removeListener(EventType.FILTER_BLOCK_READY, abService.getService());
        SoftAssert softAssert = new SoftAssert();
        abPage.getProductBlock().getProductList()
                .forEach(product -> {
                    softAssert.assertTrue(product.getProductTitle().getText().contains(secondCarModel));
                    softAssert.assertEquals(secondCarBody, product.getCarBodyInfo().getText());
                    softAssert.assertEquals(secondCarDriveSystem, product.getDriverSystemInfo().getText());
                });
        softAssert.assertAll();
    }

    @Test(dependsOnMethods = "verificationSecondFilters")
    public void verificationRedirectToValidPage() {
        String actTitle = abPage.getTestCar().getText();
        selectCar = factory.getService(getDriver(), Page.SELECT_CAR);
        selectCarPage = selectCar.getService().getSelectCarPage();
        selectCar.getService().selectCar(getDriver(), abPage);
        String excTitle = selectCarPage.getPageTitle().getText();
        Assert.assertEquals(actTitle, excTitle);
    }
}
