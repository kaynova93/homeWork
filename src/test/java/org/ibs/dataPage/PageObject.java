package org.ibs.dataPage;

import org.ibs.properties.DriverManadger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
import java.util.stream.Collectors;



import static org.ibs.properties.DriverManadger.*;


public class PageObject {

    @FindBy(xpath = "//button[@data-target = '#editModal']")
    @CacheLookup
    private WebElement btnAdd;
    @FindBy(xpath = "//select")
    @CacheLookup
    private WebElement fieldType;
    @FindBy(xpath = "//option[@value='FRUIT']")
    @CacheLookup
    private WebElement selectFruit;
    @FindBy(xpath = "//option[@value='VEGETABLE']")
    @CacheLookup
    private WebElement selectVegetable;
    @FindBy(id = "editModalLabel")
    @CacheLookup
    private WebElement modalTitle;
    @FindBy(id = "save")
    @CacheLookup
    private WebElement btnSave;
    @FindBy(xpath = "//input[@class='form-control']")
    @CacheLookup
    private WebElement inputFieldName;
    @FindBy(id = "exotic")
    @CacheLookup
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

    public void init() {
        DriverManadger.init();
         PageFactory.initElements(driver, this);
    }

    public void closeDriver() {
        DriverManadger.closeDriver();
    }

    public WebElement getType(String type) {
        return type.equals("Фрукт") ? selectFruit : selectVegetable;
    }

    public List<WebElement> getResultAddProduct() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return driver.
                findElement(By.tagName("tbody")).
                findElements(By.cssSelector(" tr:last-child td")).stream().collect(Collectors.toList());
    }




}
