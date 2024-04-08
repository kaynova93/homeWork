package org.ibs.dataPage;

import org.ibs.testData.TestData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PageElement {

    @FindBy(xpath = "//button[@data-target = '#editModal']")
    private WebElement btnAdd;

    @FindBy(xpath = "//select")
    private WebElement fieldType;
    @FindBy(xpath = "//option[@value='FRUIT']")
    private WebElement selectFruit;
    @FindBy(xpath = "//option[@value='VEGETABLE']")
    private WebElement selectVegetable;
    @FindBy(id = "editModalLabel")
    private WebElement modalTitle;
    @FindBy(id = "save")
    private WebElement btnSave;

    @FindBy(xpath = "//input[@class='form-control']")
    private WebElement inputFieldName;

    @FindBy(id = "exotic")
    private WebElement checkboxExotic;

    @FindBy(xpath = "//label[.='Наименование']")
    private WebElement titleFieldName;

    @FindBy(xpath = "//label[.='Тип']")
    private WebElement titleFieldType;

    @FindBy(xpath = "//label[.='Экзотический']")
    private WebElement titleFieldExotic;

    public WebElement getTitleFieldName() {
        return titleFieldName;
    }

    public WebElement getTitleFieldType() {
        return titleFieldType;
    }

    public WebElement getTitleFieldExotic() {
        return titleFieldExotic;
    }

    public WebElement getCheckboxExotic() {
        return checkboxExotic;
    }

    public WebElement getBtnAdd() {
        return btnAdd;
    }

    public WebElement getFieldType() {
        return fieldType;
    }

    public WebElement getSelectFruit() {
        return selectFruit;
    }

    public WebElement getSelectVegetable() {
        return selectVegetable;
    }

    public WebElement getModalTitle() {
        return modalTitle;
    }

    public WebElement getBtnSave() {
        return btnSave;
    }

    public WebElement getInputFieldName() {
        return inputFieldName;
    }

    public void init(final WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public WebElement getType(TestData testData){
        return testData.getType().equals("Фрукт")? selectFruit : selectVegetable;
    }

    public List<WebElement> getResultAddProduct(final WebDriver driver){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return driver.
                findElement(By.tagName("tbody")).
                findElements(By.cssSelector(" tr:last-child td")).stream().toList();
    }

}
