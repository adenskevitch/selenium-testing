package com.solvd.seleniumtesting.factory;

import com.solvd.seleniumtesting.service.*;
import org.openqa.selenium.WebDriver;

public class ServiceFactory {

    public Service getService(WebDriver webDriver, Page page) {
        Service service = null;
        switch (page) {
            case HOME:
                service = HomeService.builder()
                        .webDriver(webDriver)
                        .homePage()
                        .build();
                break;
            case CATALOG:
                service = CatalogService.builder()
                        .webDriver(webDriver)
                        .catalogPage()
                        .build();
                break;
            case SEARCHMODAL:
                service = new SearchModalService(webDriver);
                break;
            case AB:
                service = AbService.builder()
                        .webDriver(webDriver)
                        .abPage()
                        .build();
                break;
            case SELECTCAR:
                service = new SelectCarService(webDriver);
                break;
            default:
                break;
        }
        return service;
    }
}