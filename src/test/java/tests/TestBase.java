package tests;

import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {
    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://demoqa.com";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = System.getProperty("browserSize");
        Configuration.browser = System.getProperty("browserName");
        Configuration.browserVersion = System.getProperty("browserVersion");
        Configuration.remote = "https://" + System.getProperty("login") + "@" + System.getProperty("remote");
    }

    @AfterEach
    void tearDown() {
        closeWebDriver();
    }
}
