package tests;

import data.LoginData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.LoginRequestModel;
import models.LoginResponseModel;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.support.Resource;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import java.io.PrintStream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.ReqresSpecifications.commonRequest;
import static specs.ReqresSpecifications.response200;

public class DemoQaBookStoreLoginTests extends TestBase {
    @Test
    @DisplayName("Проверка успешного входа в учетную запись")
    void successfulLoginTest() {
        LoginRequestModel request = new LoginRequestModel();
        LoginData loginData = new LoginData();
        request.setUserName(loginData.getUserName());
        request.setPassword(loginData.getPassword());


        LoginResponseModel response = step("Сделать запрос логина и получить cookie ответа", () ->

                given()
                        .contentType(ContentType.JSON)
                        .body(request)

                        .when()
                        .post("/Account/v1/Login")

                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().as(LoginResponseModel.class));

        step("Полученные cookie присвоить текущему браузеру", () ->
                {
                    open("/favicon.ico");
                    getWebDriver().manage().addCookie(new Cookie("userID", response.getUserId()));
                    getWebDriver().manage().addCookie(new Cookie("expires", response.getExpires()));
                    getWebDriver().manage().addCookie(new Cookie("token", response.getToken()));
                });



        step("Проверить в браузере, что логин соответствует таковому из LoginData", () -> {
                    open("/profile");
                    $("#userName-value").shouldHave(text(loginData.getUserName()));
                }
        );
    }

}

