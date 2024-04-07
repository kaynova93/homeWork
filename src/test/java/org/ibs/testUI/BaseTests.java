package org.ibs.testUI;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import java.util.concurrent.TimeUnit;

public class BaseTests {
    private static final String URL = "http://localhost:8080/food";
    static WebDriver driver;
    @BeforeEach
     void init() {
         driver = new ChromeDriver();
        System.setProperty("webdriver.chromedriver.driver","src/test/resources/chromedriver.exe");
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get(URL);
    }


    @AfterEach
     void preOver() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.close();
    }

    @AfterEach
    void over() {

        driver.quit();
    }
}
