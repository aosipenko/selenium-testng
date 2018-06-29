package com.neotech.page;

import com.neotech.driver.utils.ISelectable;
import org.openqa.selenium.By;

public enum GooglePageSelectors implements ISelectable {
    SEARCH_INPUT(By.name("q")),
    SEARCH_BTN(By.name("btnK")),
    SEARCH_RESULT_LIST(By.xpath("//div[@id='search']//div[@class='g']"));

    private By locator;

    GooglePageSelectors(By locator) {
        this.locator = locator;
    }

    public By getLocator() {
        return locator;
    }
}
