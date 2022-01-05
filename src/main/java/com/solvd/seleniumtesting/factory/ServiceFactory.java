package com.solvd.seleniumtesting.factory;

import com.solvd.seleniumtesting.service.*;
import org.openqa.selenium.WebDriver;

public class ServiceFactory {

    public Service getService(WebDriver webDriver, Page page) {
        Service service = null;
        switch (page) {
            case HOME:
                service = new HomeService(webDriver);
                break;
            case CATALOG:
                service = new CatalogService(webDriver);
                break;
            case SEARCHMODAL:
                service = new SearchModalService(webDriver);
                break;
            case AB:
                service = new AbService(webDriver);
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