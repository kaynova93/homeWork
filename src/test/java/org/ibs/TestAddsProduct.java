package org.ibs;


import org.ibs.testData.TestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

import static org.ibs.dataPage.PageObject.*;

public class TestAddsProduct extends BaseSettings{

    @ParameterizedTest
    @EnumSource(value = TestData.class,
            names = {"TEST_DATA_1","TEST_DATA_2"},
            mode = EnumSource.Mode.INCLUDE)
    void testAddNotExoticProduct(TestData testData){
        driver.findElement(By.xpath(BTN_ADD.getSelector())).click();
        driver.findElement(By.xpath(INPUT_FIELD_NAME.getSelector()))
                .sendKeys(testData.getName());
        driver.findElement(By.xpath(FIELD_TYPE.getSelector())).click();
        driver.findElement(By.id(MODAL_TITLE.getSelector())).isDisplayed();
        WebElement select = driver.findElement(By.xpath(testData.getPageObject().getSelector()));
        select.click();
        select.findElement(By.xpath(testData.getPageObject().getSelector())).click();
        driver.findElement(By.id(BTN_SAVE.getSelector())).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        List<WebElement> els = driver.
                findElement(By.tagName("tbody")).
                findElements(By.cssSelector(" tr:last-child td")).stream().toList();

        Assertions.assertAll("Сравнение добавленного элемента",
                ()->Assertions.assertEquals(testData.getName(),els.get(0).getText(),"Получили "+ els.get(0).getText()),
                ()->Assertions.assertEquals(testData.getType(),els.get(1).getText(),"Получили "+els.get(1).getText()),
                ()->Assertions.assertEquals(testData.getExotic(),els.get(2).getText(),"Получили "+els.get(2).getText())
        );
    }

    @ParameterizedTest
    @EnumSource(value = TestData.class,
            names = {"TEST_DATA_3","TEST_DATA_4"},
            mode = EnumSource.Mode.INCLUDE
    )
    void testAddExoticProduct(TestData testData){
        driver.findElement(By.xpath(BTN_ADD.getSelector())).click();
        driver.findElement(By.xpath(INPUT_FIELD_NAME.getSelector()))
                .sendKeys(testData.getName());
        driver.findElement(By.xpath(FIELD_TYPE.getSelector())).click();
        driver.findElement(By.id(MODAL_TITLE.getSelector())).isDisplayed();
        WebElement select = driver.findElement(By.xpath(FIELD_TYPE.getSelector()));
        select.click();
        select.findElement(By.xpath(testData.getPageObject().getSelector())).click();
        driver.findElement(By.id(CHECKBOX_EXOTIC.getSelector())).click();
        driver.findElement(By.id(BTN_SAVE.getSelector())).click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        List<WebElement> els = driver.
                findElement(By.tagName("tbody")).
                findElements(By.cssSelector(" tr:last-child td")).stream().toList();

        Assertions.assertAll("Сравнение добавленного элемента",
                ()->Assertions.assertEquals(testData.getName(),els.get(0).getText(),
                        "название продукта не совпадает"),
                ()->Assertions.assertEquals(testData.getType(),els.get(1).getText(),
                        "тип продукта не совпадает "+ els.get(1).getText()+" " +els.get(0).getText()),
                ()->Assertions.assertEquals(testData.getExotic(),els.get(2).getText(),
                        "признак экзотичный продукта не совпадает")
        );
    }

}
