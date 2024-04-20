package org.ibs.dataPage;

import org.ibs.properties.PropManedger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class PageObject {

    private static final String URL_LOCAL = "http://localhost:8080/food";
    private static final String URL = "http://149.154.71.152:8080/food";
    public static WebDriver driver;


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
        System.out.println(System.getProperty("app.type.driver"));
        if (!String.valueOf(System.getProperty("app.type.driver")).equals("null")) {
            if (System.getProperty("app.type.driver").equalsIgnoreCase("remote")) {
                initRemoteDriver();}
            } else {
                driver = new ChromeDriver();
                System.setProperty("webdriver.chromedriver.driver", "src/test/resources/chromedriver.exe");
                driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
                driver.get(URL_LOCAL);
            }
            PageFactory.initElements(driver, this);

    }


    private void initRemoteDriver(){

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
//        desiredCapabilities.setCapability ("browserName", System.getProperty ("type.browser"));
//        desiredCapabilities.setCapability ("browserVersion", System.getProperty ("type.version"));
        desiredCapabilities.setBrowserName(System.getProperty("type.browser"));
        desiredCapabilities.setVersion(System.getProperty("type.version"));
        desiredCapabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", false
        ));
        try {
            driver = new RemoteWebDriver(URI.create(PropManedger.propertyMap.get("selenoid.url")).toURL(),desiredCapabilities);
            driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            driver.get(URL);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.close();
    }

    public void quit() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.quit();
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
