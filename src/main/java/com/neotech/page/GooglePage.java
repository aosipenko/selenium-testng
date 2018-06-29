package com.neotech.page;

import com.neotech.driver.WebDriverFacade;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GooglePage {

    private final static String URL = "https://google.com";

    @Autowired
    private WebDriverFacade webDriverFacade;

    @Autowired
    public GooglePage(WebDriverFacade webDriverFacade) {
        this.webDriverFacade = webDriverFacade;
    }

    public void loadPage() {
        webDriverFacade.loadPage(URL);
    }

    public void search(String searchString) throws Exception {
        webDriverFacade.findElement(GooglePageSelectors.SEARCH_INPUT).sendKeys(searchString);
        webDriverFacade.click(webDriverFacade.findElement(GooglePageSelectors.SEARCH_BTN), false);
    }

    public boolean validateSearchContains(String searchResult) throws Exception {
        for (WebElement element : getSearchResults()) {
            if (element.getText().contains(searchResult))
                return true;
        }
        return false;
    }

    public boolean validateFirstSearchResult(String firstResult) throws Exception {
        return getSearchResults().get(0).getText().contains(firstResult);
    }

    private List<WebElement> getSearchResults() throws Exception {
        return webDriverFacade.findElements(GooglePageSelectors.SEARCH_RESULT_LIST);
    }
}
