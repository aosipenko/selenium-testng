package com.neotech.driver;

import com.neotech.driver.utils.WebDriverName;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.stereotype.Service;

@Service
public class WebDriverFactory {

    public WebDriver getDriver() throws Exception {
        WebDriverName driverName = WebDriverName.valueOf(System.getProperty("driver.name", "CHROME"));
        String driverVersion = System.getProperty("driver.version", null);
        if (WebDriverName.CHROME.equals(driverName)) {
            return chromeDriver(driverVersion);
        } else if (WebDriverName.FIREFOX.equals(driverName)) {
            return fireFoxDriver(driverVersion);
        } else if (WebDriverName.EDGE.equals(driverName)) {
            return edgeDriver(driverVersion);
        }
        //ATF could not get proper driver name
        throw new Exception("This driver is not supported!");
    }

    private WebDriver chromeDriver(String version) {
        //setup version if specified
        if (version != null) {
            WebDriverManager.chromedriver().version(version);
        }
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        //in case if tests need to work with self-singned certificates
        options.setAcceptInsecureCerts(true);
        options.addArguments("--disable-extensions");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        options.addArguments("--disable-dev-shm-usage");
        return new ChromeDriver(options);
    }

    private WebDriver fireFoxDriver(String version) {
        //setup version if specified
        if (version != null) {
            WebDriverManager.firefoxdriver().version(version);
        }
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        //in case if tests need to work with self-singned certificates
        options.setAcceptInsecureCerts(true);
        return new FirefoxDriver(options);
    }

    private WebDriver edgeDriver(String version) {
        //setup version if specified
        if (version != null) {
            WebDriverManager.edgedriver().version(version);
        }
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        //in case if tests need to work with self-singned certificates
        options.setCapability("acceptSslCerts", true);
        return new EdgeDriver(options);
    }
}
