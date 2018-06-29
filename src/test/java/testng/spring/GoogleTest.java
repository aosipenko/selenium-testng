package testng.spring;

import com.neotech.page.GooglePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

@Test
public class GoogleTest extends TestConfig {

    @Autowired
    GooglePage googlePage;

    @Test()
    void testEmailGenerator() throws Exception {
        googlePage.loadPage();
        googlePage.search("twitter");
        assertTrue(googlePage.validateSearchContains("twitter"), "Search result did not contain 'twitter'!");
        assertTrue(googlePage.validateFirstSearchResult("twitter"), "First search result did not contain 'twitter'!");
    }
}
