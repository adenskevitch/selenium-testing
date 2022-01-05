package com.solvd.seleniumtesting.service;

import com.solvd.seleniumtesting.page.AbPage;
import com.solvd.seleniumtesting.page.components.filters.*;

public class AbService {

    private final AbPage abPage;

    public AbService(AbPage abPage) {
        this.abPage = abPage;
    }

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

    public void applyFilters(String carModel, String carBody, String driveSystem) {
        selectModel(carModel);
        selectCarBody(carBody);
        selectDriverSystem(driveSystem);
    }

    public AbPage getAbPage() {
        return abPage;
    }
}
