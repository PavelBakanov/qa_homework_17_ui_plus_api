package tests;

import data.LoginData;
import helpers.extensions.WithLogin;
import io.restassured.http.ContentType;
import models.LoginRequestModel;
import models.LoginResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.DemoQaBookStoreSpecifications.demoQaBookStoreLoginRequest;
import static specs.DemoQaBookStoreSpecifications.response200;

public class DemoQaBookStoreLoginTests extends TestBase {
    @Test
    @WithLogin
    @DisplayName("Проверка успешного входа в учетную запись")
    void successfulLoginTest() {
        LoginData loginData = new LoginData();

        step("Авторизоваться на сайте с помощью аннотации @WithLogin и проверить, что логин соответствует таковому из LoginData", () -> {
                    open("/profile");
                    $("#userName-value").shouldHave(text(loginData.getUserName()));
                }
        );
    }

}

