package helpers.extensions;

import api.autorization.AutorizationApi;
import models.LoginResponseModel;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class LoginExtension implements BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext context)
    {
        LoginResponseModel cookies = AutorizationApi.getAutorizationCookie();

        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", cookies.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("expires", cookies.getExpires()));
        getWebDriver().manage().addCookie(new Cookie("token", cookies.getToken()));
    }

}
