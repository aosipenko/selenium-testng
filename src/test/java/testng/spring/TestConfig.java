package testng.spring;

import org.openqa.selenium.WebDriver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterSuite;

@ContextConfiguration(locations = {"classpath:spring-test-config.xml"})
public class TestConfig extends AbstractTestNGSpringContextTests {

    @AfterSuite
    public void shutDownDriver() {
        applicationContext.getBean("webDriver", WebDriver.class).quit();
    }
}
