package com.neotech.driver;

import com.neotech.driver.utils.ISelectable;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class WebDriverFacade {

    private final static Long DEFAULT_TIMEOUT_SECONDS = 15L;

    private WebDriver driver;

    @Autowired
    public WebDriverFacade(WebDriver driver) {
        this.driver = driver;
        driver.manage().window().maximize();
    }

    public void loadPage(String url) {
        driver.get(url);
    }

    public WebElement waitForElementToBeClickable(ISelectable selectable) {
        return new WebDriverWait(driver, DEFAULT_TIMEOUT_SECONDS).until(ExpectedConditions.elementToBeClickable(selectable.getLocator()));
    }

    public WebElement findElement(ISelectable selectable) throws Exception {
        try {
            return new WebDriverWait(driver, DEFAULT_TIMEOUT_SECONDS)
                    .until(ExpectedConditions.presenceOfElementLocated(selectable.getLocator()));
        } catch (Exception e) {
            takeScreenshot(String.valueOf(System.currentTimeMillis()));
            throw e;
        }
    }

    public List<WebElement> findElements(ISelectable selectable) throws Exception {
        try {
            new WebDriverWait(driver, DEFAULT_TIMEOUT_SECONDS)
                    .until(ExpectedConditions.presenceOfAllElementsLocatedBy(selectable.getLocator()));
            return driver.findElements(selectable.getLocator());
        } catch (Exception e) {
            takeScreenshot(String.valueOf(System.currentTimeMillis()));
            throw e;
        }
    }

    public void click(WebElement element, boolean mouseClick) {
        if (mouseClick) {
            element.click();
        } else {
            element.sendKeys(Keys.RETURN);
        }
    }

    public void takeScreenshot(String fileName) throws IOException {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        // Now you can do whatever you need to do with it, for example copy somewhere
        File screenShot = new File(fileName + ".png");
        //clean up if file exists
        if (screenShot.exists()) {
            screenShot.delete();
        }
        FileUtils.copyFile(scrFile, screenShot);
    }
}