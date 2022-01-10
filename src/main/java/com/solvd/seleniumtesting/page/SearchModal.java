package com.solvd.seleniumtesting.page;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class SearchModal extends AbstractPage {

    @FindBy(xpath = "//*[contains(@class,'item_category')]")
    private ExtendedWebElement categoryLink;

    public SearchModal(WebDriver driver) {
        super(driver);
        this.setRootElement(driver.findElement(By.xpath("//iframe[contains(@class,'modal-iframe')]")));;
    }

    public ExtendedWebElement getCategoryLink() {
        return categoryLink;
    }
}
