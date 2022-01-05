package com.solvd.seleniumtesting.service;

import com.solvd.seleniumtesting.factory.Service;
import com.solvd.seleniumtesting.page.AbPage;
import com.solvd.seleniumtesting.page.components.filters.CarBodyFilter;
import com.solvd.seleniumtesting.page.components.filters.DriveSystemFilter;
import com.solvd.seleniumtesting.page.components.filters.ModelFilter;
import org.openqa.selenium.WebDriver;

public class AbService extends ServiceClass implements Service<AbService> {

    private AbPage abPage;
    private WebDriver webDriver;

    private String carModel;
    private String carBody;
    private String driveSystem;

    public void selectModel(String carModel) {
        ModelFilter carModelFilter = abPage.getFilterBlock().getModelFilters();
        carModelFilter.getBrand().click();
        carModelFilter.getBrandDropdownFilter().getBrandInputField().type(carModel);
        carModelFilter.getBrandDropdownFilter().getBrandList().stream()
                .findFirst().get().click();
    }

    public void selectCarBody(String carBody) {
        CarBodyFilter carBodyFilter = abPage.getFilterBlock().getCarBodyFilter();
        carBodyFilter.getCarBody().click();
        carBodyFilter.getCarBodyDropdownFilter().getCarBodyList().stream()
                .filter(cBody -> carBody.equals(cBody.getText()))
                .findFirst().get().click();
    }

    public void selectDriverSystem(String driveSystem) {
        DriveSystemFilter carDriverSystemFilter = abPage.getFilterBlock().getDriveSystemFilter();
        carDriverSystemFilter.getDriverSystems().stream()
                .filter(dSystem -> driveSystem.equals(dSystem.getText()))
                .findFirst().get().click();
    }

    public void applyFilters() {
        selectModel(carModel);
        selectCarBody(carBody);
        selectDriverSystem(driveSystem);
    }

    public AbPage getAbPage() {
        return abPage;
    }

    @Override
    public AbService getService() {
        return this;
    }

    @Override
    public void onEvent() {
        applyFilters();
    }

    public static Builder builder() {
        return new Builder(new AbService());
    }

    public static class Builder {
        private final AbService abService;

        private Builder(AbService abService) {
            this.abService = abService;
        }

        public Builder webDriver(WebDriver webDriver) {
            abService.webDriver = webDriver;
            return this;
        }

        public Builder abPage() {
            abService.abPage = new AbPage(abService.webDriver);
            return this;
        }

        public AbService build() {
            return abService;
        }
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public void setCarBody(String carBody) {
        this.carBody = carBody;
    }

    public void setDriveSystem(String driveSystem) {
        this.driveSystem = driveSystem;
    }
}
