package testng.spring;

import com.neotech.page.GooglePage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

@Test
public class GoogleTest extends TestConfig {

    @Autowired
    GooglePage googlePage;

    @Test()
    @Description("This is a fundamental test for google search")
    @Severity(SeverityLevel.CRITICAL)
    void testEmailGenerator() throws Exception {
        googlePage.loadPage();
        googlePage.search("twitter");
        assertTrue(googlePage.validateSearchContains("twitter"), "Search result did not contain 'twitter'!");
        assertTrue(googlePage.validateFirstSearchResult("twitter"), "First search result did not contain 'twitter'!");
        googlePage.validateContent("validationScrn");
    }
}
