package org.ibs.properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class DriverManadger {
    private static final String URL_LOCAL = "http://localhost:8080/food";
    private static final String URL = "http://149.154.71.152:8080/food";
    public static WebDriver driver;


    public static WebDriver init() {
        if (!String.valueOf(System.getProperty("app.type.driver")).equals("null")) {
            if (System.getProperty("app.type.driver").equalsIgnoreCase("remote")) {
                initRemoteDriver();
            }
        } else {
            WebDriver local = new ChromeDriver();
            System.setProperty("webdriver.chromedriver.driver", "src/test/resources/chromedriver.exe");
            local.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
            local.manage().window().maximize();
            local.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            local.get(URL_LOCAL);
            driver = local;
        }
        return driver;
    }

    private static WebDriver initRemoteDriver(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("browserVersion", "108.0");
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", false
        ));
        try {
            WebDriver remote = new RemoteWebDriver(
                    URI.create("http://149.154.71.152:4444/wd/hub").toURL(),
                    capabilities
            );
            remote.manage().window().maximize();
            remote.get(URL);
            driver = remote;
            return remote;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void closeDriver() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
//        driver.manage().deleteAllCookies();
        driver.quit();
        driver = null;
    }


}
