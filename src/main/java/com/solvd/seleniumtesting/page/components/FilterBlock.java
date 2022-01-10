package com.solvd.seleniumtesting.page.components;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import com.solvd.seleniumtesting.page.components.filters.CarBodyFilter;
import com.solvd.seleniumtesting.page.components.filters.DriveSystemFilter;
import com.solvd.seleniumtesting.page.components.filters.ModelFilter;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class FilterBlock extends AbstractUIObject {

    @FindBy(xpath = ".//*[text()='Марка']/ancestor::*[contains(@class,'group')]")
    private ModelFilter modelFilters;

    @FindBy(xpath = ".//*[text()='Тип кузова']/ancestor::*[contains(@class,'group')]")
    private CarBodyFilter carBodyFilter;

    @FindBy(xpath = ".//*[text()='Привод']/ancestor::*[contains(@class,'group')]")
    private DriveSystemFilter driveSystemFilter;

    public FilterBlock(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public ModelFilter getModelFilters() {
        return modelFilters;
    }

    public CarBodyFilter getCarBodyFilter() {
        return carBodyFilter;
    }

    public DriveSystemFilter getDriveSystemFilter() {
        return driveSystemFilter;
    }
}

