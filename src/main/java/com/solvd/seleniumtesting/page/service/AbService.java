package com.solvd.seleniumtesting.page.service;

import com.solvd.seleniumtesting.page.AbPage;

public class AbService {

    private final AbPage abPage;

    public AbService(AbPage abPage) {
        this.abPage = abPage;
    }

    public void selectModel() {
        abPage.getFilterBlock().getModelFilters().getBrand().click();
        String brand = "Audi";
        abPage.getFilterBlock().getModelFilters().getBrandDropdownFilter().getBrandInputField().type(brand);
        abPage.getFilterBlock().getModelFilters().getBrandDropdownFilter().getBrandList().stream()
                .findFirst().get().click();
    }

    public void selectCarBody() {
        abPage.getFilterBlock().getCarBodyFilter().getCarBody().click();
        abPage.getFilterBlock().getCarBodyFilter().getCarBodyDropdownFilter().getCarBodyList().stream()
                .filter(carBody -> "Внедорожник".equals(carBody.getText()))
                .findFirst().get().click();
    }

    public void selectDriverSystem() {
        abPage.getFilterBlock().getDriveSystemFilter().getDriverSystems().stream()
                .filter(driverSystem -> "Полный".equals(driverSystem.getText()))
                .findFirst().get().click();
    }

    public void applyFilters() {
        selectModel();
        selectCarBody();
        selectDriverSystem();
    }

    public AbPage getAbPage() {
        return abPage;
    }
}
