package com.solvd.seleniumtesting.page.service;

import com.solvd.seleniumtesting.page.AbPage;

public class AbService {

    private final AbPage abPage;

    public AbService(AbPage abPage) {
        this.abPage = abPage;
    }

    public void selectModel(String carModel) {
        abPage.getFilterBlock().getModelFilters().getBrand().click();
        abPage.getFilterBlock().getModelFilters().getBrandDropdownFilter().getBrandInputField().type(carModel);
        abPage.getFilterBlock().getModelFilters().getBrandDropdownFilter().getBrandList().stream()
                .findFirst().get().click();
    }

    public void selectCarBody(String carBody) {
        abPage.getFilterBlock().getCarBodyFilter().getCarBody().click();
        abPage.getFilterBlock().getCarBodyFilter().getCarBodyDropdownFilter().getCarBodyList().stream()
                .filter(cBody -> carBody.equals(cBody.getText()))
                .findFirst().get().click();
    }

    public void selectDriverSystem(String driveSystem) {
        abPage.getFilterBlock().getDriveSystemFilter().getDriverSystems().stream()
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
