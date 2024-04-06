package org.ibs;

import org.ibs.dataPage.PageObject;
import org.ibs.testData.TestData;
import org.ibs.testData.ValidationTestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.ibs.dataPage.PageObject.*;
import static org.ibs.testData.ValidationTestData.*;

public class TestModalWindow extends BaseSettings{

    @Test
    void testElementsInModal(){
        driver.findElement(By.xpath(BTN_ADD.getSelector())).click();

        Assertions.assertAll(
                ()->Assertions.assertEquals("Добавление товара",
                        driver.findElement(By.id( MODAL_TITLE.getSelector())).getAttribute("innerText"),
                        "не совпал текст у заголовка"),
                ()-> Assertions.assertEquals("Наименование",
                        driver.findElement(By.xpath(TITLE_FIELD_NAME.getSelector())).getAttribute("innerText"),
                        "не совпал текст у поля "),
                ()-> Assertions.assertEquals("Тип",
                        driver.findElement(By.xpath(TITLE_FIELD_TYPE.getSelector())).getAttribute("innerText"),

                        "не совпал текст у поля "),
                ()-> Assertions.assertEquals("Экзотический",
                        driver.findElement(By.xpath(TITLE_FIELD_EXOTIC.getSelector())).getAttribute("innerText"),
                        "не совпал текст у поля "),
                ()-> Assertions.assertEquals("Сохранить",
                        driver.findElement(By.id(BTN_SAVE.getSelector())).getAttribute("innerText"),
                        "не совпал текст у поля ")
        );
    }

    @Test
    void testTypeList(){
       driver.findElement(By.xpath(BTN_ADD.getSelector())).click();
       driver.findElement(By.xpath(FIELD_TYPE.getSelector())).click();
       WebElement fruit= driver.findElement(By.xpath(SELECT_FRUIT.getSelector()));
       WebElement vegetable = driver.findElement(By.xpath(SELECT_VEGETABLE.getSelector()));
       Assertions.assertTrue(fruit.isDisplayed());
       Assertions.assertTrue(vegetable.isDisplayed());
       Assertions.assertEquals(TEST_DATA_8.getTest(),fruit.getText(), "получили текст : "+ fruit.getText());
       Assertions.assertEquals(TEST_DATA_7.getTest(),vegetable.getText(), "получили текст : "+ vegetable.getText());
    }

    @ParameterizedTest
    @EnumSource(value = ValidationTestData.class,
            names = {"TEST_DATA_7","TEST_DATA_8"},
            mode = EnumSource.Mode.EXCLUDE)
    void testValidationTestPositive(ValidationTestData validationTestData){
        driver.findElement(By.xpath(PageObject.BTN_ADD.getSelector())).click();
        driver.findElement(By.xpath(INPUT_FIELD_NAME.getSelector())).sendKeys(validationTestData.getTest());
        driver.findElement(By.id(BTN_SAVE.getSelector())).click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        List<WebElement> els = driver.
                findElement(By.tagName("tbody")).
                findElements(By.cssSelector(" tr:last-child td")).stream().toList();

        Assertions.assertEquals(validationTestData.getTest(),els.get(0).getText(),
                        "название продукта не совпадает");
    }
}
