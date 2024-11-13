package tests;

import helpers.extensions.WithLogin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
@DisplayName("Тесты книжного магазина с сайта demoqa.com")
public class DemoQaBookStoreTests extends TestBase {
    @Test
    @WithLogin
    @DisplayName("Проверка успешного удаления товара из списка")
    void successfulDeleteBookTest() {

    }

}

