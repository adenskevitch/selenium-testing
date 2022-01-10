package com.solvd.seleniumtesting.service;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.solvd.seleniumtesting.listener.Event;

public abstract class ServiceClass implements Event {

    /*
    For decorator pattern realisation example
     */
    public void clickOnElement(ExtendedWebElement webElement) {
        webElement.click();
    }
}
