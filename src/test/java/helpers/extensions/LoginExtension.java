package helpers.extensions;

import api.authorization.AuthorizationApi;
import data.AuthData;
import data.LoginData;
import models.LoginResponseModel;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;

public class LoginExtension implements BeforeEachCallback {


    @Override
    public void beforeEach(ExtensionContext context) {
        LoginData loginData = new LoginData();
        LoginResponseModel cookies = AuthorizationApi.getAuthorizationCookie();

        AuthData.USER_ID = cookies.getUserId();
        AuthData.EXPIRES = cookies.getExpires();
        AuthData.USER_TOKEN = cookies.getToken();

        step("Добавить полученные из ответа cookie к текущему браузеру, " +
                "что бы можно было проверить успешный вход в учетную запись", () -> {
            open("/favicon.ico");
            getWebDriver().manage().addCookie(new Cookie("userID", cookies.getUserId()));
            getWebDriver().manage().addCookie(new Cookie("expires", cookies.getExpires()));
            getWebDriver().manage().addCookie(new Cookie("token", cookies.getToken()));
        });

        step("Проверить успешный вход в учетную запись", () -> {
                    open("/profile");
                    $("#userName-value").shouldHave(text(loginData.getUserName()));
                }
        );

    }

}
