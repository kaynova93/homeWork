package org.ibs.testUI;


import org.ibs.dataPage.PageElement;
import org.ibs.testData.TestData;
import org.ibs.testUI.BaseTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;


public class TestAddsProduct extends BaseTests {
    PageElement pageElement = new PageElement();

    @ParameterizedTest
    @EnumSource(value = TestData.class,
            names = {"TEST_DATA_1", "TEST_DATA_2"},
            mode = EnumSource.Mode.INCLUDE)
    void testAddNotExoticProduct(TestData testData) {
        pageElement.init(driver);
        pageElement.getBtnAdd().click();
        pageElement.getModalTitle().isDisplayed();
        pageElement.getInputFieldName().sendKeys(testData.getName());
        pageElement.getFieldType().click();
        pageElement.getType(testData).click();
        pageElement.getBtnSave().click();
        List<WebElement> result = pageElement.getResultAddProduct(driver);

        Assertions.assertAll("Сравнение добавленного элемента",
                () -> Assertions.assertEquals(testData.getName(), result.get(0).getText(),
                        "Получили " + result.get(0).getText()),
                () -> Assertions.assertEquals(testData.getType(), result.get(1).getText(),
                        "Получили " + result.get(1).getText()),
                () -> Assertions.assertEquals(testData.getExotic(), result.get(2).getText(),
                        "Получили " + result.get(2).getText())
        );
    }

    @ParameterizedTest
    @EnumSource(value = TestData.class,
            names = {"TEST_DATA_3", "TEST_DATA_4"},
            mode = EnumSource.Mode.INCLUDE)
    void testAddExoticProduct(TestData testData) {
        pageElement.init(driver);
        pageElement.getBtnAdd().click();
        pageElement.getModalTitle().isDisplayed();
        pageElement.getInputFieldName().sendKeys(testData.getName());
        pageElement.getFieldType().click();
        pageElement.getType(testData).click();
        pageElement.getCheckboxExotic().click();
        pageElement.getBtnSave().click();
        List<WebElement> result = pageElement.getResultAddProduct(driver);

        Assertions.assertAll("Сравнение добавленного элемента",
                () -> Assertions.assertEquals(testData.getName(), result.get(0).getText(),
                        "название продукта не совпадает"),
                () -> Assertions.assertEquals(testData.getType(), result.get(1).getText(),
                        "тип продукта не совпадает " + result.get(1).getText() + " "
                                + result.get(0).getText()),
                () -> Assertions.assertEquals(testData.getExotic(), result.get(2).getText(),
                        "признак экзотичный продукта не совпадает")
        );
    }

}
