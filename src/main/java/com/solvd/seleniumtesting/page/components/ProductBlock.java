package com.solvd.seleniumtesting.page.components;

import com.qaprosoft.carina.core.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductBlock extends AbstractUIObject {

    @FindBy(xpath = "//*[contains(@class,'offers-list')]/a")
    private List<Product> productList;

    public ProductBlock(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public List<Product> getProductList() {
        return productList;
    }
}
