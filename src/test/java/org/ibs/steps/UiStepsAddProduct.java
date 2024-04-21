package org.ibs.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.*;
import io.qameta.allure.Issue;
import org.ibs.dataPage.PageObject;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import java.util.List;

@Issue("Kaynova I.S.")
public class UiStepsAddProduct {

    PageObject pageObject = new PageObject();

    @И("Подключить настройки для UI")
    public void init() {
            pageObject.init();
    }

    @И("Закрыть подключение UI")
    public void close() {
        pageObject.closeDriver();
    }

    @Когда("Кнопка добавить видна")
    public void btnAddVisible() {
        pageObject.getBtnAdd().isDisplayed();
    }

    @Когда("Кнопка сохранить видна")
    public void btnSaveVisible() {
        pageObject.getBtnSave().isDisplayed();
    }

    @То("Нажать кнопку добавить")
    public void clickBtnAdd() {
        pageObject.getBtnAdd().click();
    }

    @И("Открылось модальное окно")
    public void titleModalVisible() {
        pageObject.getModalTitle().isDisplayed();
    }


    @Тогда("Заполнить поле Наименование")
    public void fillFielNameProduct(String name) {
        pageObject.getInputFieldName().sendKeys(name);
    }

    @Тогда("Открыть список тип продукта")
    public void openListTypeProduct() {
        pageObject.getFieldType().click();
    }

    @Тогда("Выбрать тип продукта")
    public void selectTypeProduct(String type) {
        pageObject.getType(type).click();
    }

    @То("Нажать на кнопку сохранить")
    public void clickBtnSave() {
        pageObject.getBtnSave().click();
    }

    @Тогда("Поля видны")
    public void fieldVisible() {
        pageObject.getInputFieldName().isDisplayed();
        pageObject.getFieldType().isDisplayed();
        pageObject.getCheckboxExotic().isDisplayed();
    }

    @Тогда("Проверить значения в списке {string} , {string}")
    public void checkValuesInListType(String fruit, String vegetable) {
        Assertions.assertTrue(pageObject.getSelectFruit().isDisplayed());
        Assertions.assertTrue(pageObject.getSelectVegetable().isDisplayed());
        Assertions.assertEquals(fruit, pageObject.getSelectFruit().getText(),
                "получили текст : " + pageObject.getSelectFruit().getText());
        Assertions.assertEquals(vegetable, pageObject.getSelectVegetable().getText(),
                "получили текст : " + pageObject.getSelectVegetable().getText());
    }

    @Тогда("Заголовки полей видны")
    public void titleFieldVisible() {
        pageObject.getTitleFieldName().isDisplayed();
        pageObject.getTitleFieldName().isDisplayed();
        pageObject.getTitleFieldExotic().isDisplayed();

        Assertions.assertAll(
                () -> Assertions.assertEquals("Добавление товара",
                        pageObject.getModalTitle().getAttribute("innerText"),
                        "не совпал текст у заголовка"),
                () -> Assertions.assertEquals("Наименование",
                        pageObject.getTitleFieldName().getAttribute("innerText"),
                        "не совпал текст у поля "),
                () -> Assertions.assertEquals("Тип",
                        pageObject.getTitleFieldType().getAttribute("innerText"),

                        "не совпал текст у поля "),
                () -> Assertions.assertEquals("Экзотический",
                        pageObject.getTitleFieldExotic().getAttribute("innerText"),
                        "не совпал текст у поля "),
                () -> Assertions.assertEquals("Сохранить",
                        pageObject.getBtnSave().getAttribute("innerText"),
                        "не совпал текст у поля ")
        );
    }

    @Тогда("Проставить чекбокс")
    public void clickCheckbox(String exotic) {
        if (exotic.equals("true")) {
            pageObject.getCheckboxExotic().click();
        }
    }

    @Тогда("Проверить добавленный продукт в таблице")
    public void checkAddProductInTable(DataTable dataTable) {
        List<List<String>> table = dataTable.asLists(String.class);
        List<WebElement> result = pageObject.getResultAddProduct();

        Assertions.assertAll("Сравнение добавленного элемента",
                () -> Assertions.assertEquals(table.get(0).get(0), result.get(0).getText(),
                        "Получили " + result.get(0).getText()),
                () -> Assertions.assertEquals(table.get(0).get(1), result.get(1).getText(),
                        "Получили " + result.get(1).getText()),
                () -> Assertions.assertEquals(table.get(0).get(2), result.get(2).getText(),
                        "Получили " + result.get(2).getText())
        );
    }

    @Тогда("Проверить наименование добавленного продукта в таблице")
    public void checkAddNameInTable(String name) {
        List<WebElement> result = pageObject.getResultAddProduct();

        Assertions.assertEquals(name, result.get(0).getText(),
                "название продукта не совпадает");
    }
}
