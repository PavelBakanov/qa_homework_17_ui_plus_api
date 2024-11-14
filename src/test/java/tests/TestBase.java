package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

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
        Selenide.closeWebDriver();
    }
}
