package org.ibs.testUI;

import org.ibs.dataPage.PageElement;
import org.ibs.testData.ValidationTestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.openqa.selenium.WebElement;
import java.util.List;
import static org.ibs.testData.ValidationTestData.*;

public class TestModalWindow extends BaseTests {
    PageElement pageElement = new PageElement();

    @Test
    void testElementsInModal() {
        pageElement.init(driver);
        pageElement.getBtnAdd().click();

        Assertions.assertAll(
                () -> Assertions.assertEquals("Добавление товара",
                        pageElement.getModalTitle().getAttribute("innerText"),
                        "не совпал текст у заголовка"),
                () -> Assertions.assertEquals("Наименование",
                        pageElement.getTitleFieldName().getAttribute("innerText"),
                        "не совпал текст у поля "),
                () -> Assertions.assertEquals("Тип",
                        pageElement.getTitleFieldType().getAttribute("innerText"),

                        "не совпал текст у поля "),
                () -> Assertions.assertEquals("Экзотический",
                        pageElement.getTitleFieldExotic().getAttribute("innerText"),
                        "не совпал текст у поля "),
                () -> Assertions.assertEquals("Сохранить",
                        pageElement.getBtnSave().getAttribute("innerText"),
                        "не совпал текст у поля ")
        );
    }

    @Test
    void testTypeList() {
        pageElement.init(driver);
        pageElement.getBtnAdd().click();
        pageElement.getFieldType().click();
        Assertions.assertTrue(pageElement.getSelectFruit().isDisplayed());
        Assertions.assertTrue(pageElement.getSelectVegetable().isDisplayed());
        Assertions.assertEquals(TEST_DATA_8.getTest(), pageElement.getSelectFruit().getText(),
                "получили текст : " + pageElement.getSelectFruit().getText());
        Assertions.assertEquals(TEST_DATA_7.getTest(), pageElement.getSelectVegetable().getText(),
                "получили текст : " + pageElement.getSelectVegetable().getText());
    }

    @ParameterizedTest
    @EnumSource(value = ValidationTestData.class,
            names = {"TEST_DATA_7", "TEST_DATA_8"},
            mode = EnumSource.Mode.EXCLUDE)
    void testValidationTestPositive(ValidationTestData validationTestData) {
        pageElement.init(driver);

        pageElement.getBtnAdd().click();
        pageElement.getInputFieldName().sendKeys(validationTestData.getTest());
        pageElement.getBtnSave().click();
        List<WebElement> result = pageElement.getResultAddProduct(driver);

        Assertions.assertEquals(validationTestData.getTest(), result.get(0).getText(),
                "название продукта не совпадает");
    }
}
