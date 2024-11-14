package tests;

import api.AccountApi;
import api.BookStoreApi;
import helpers.extensions.WithLogin;
import models.GetListOfBooksResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.ProfilePage;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тесты книжного магазина с сайта demoqa.com")
public class DemoQaBookStoreTests extends TestBase {
    @Test
    @WithLogin
    @DisplayName("Проверка успешного удаления товара из списка")
    void successfulDeleteBookTest() {

        step("Очистить корзину с книгами", () ->
                BookStoreApi.deleteAllBooksInCart());

        step("Добавить определенную книгу в корзину", () ->
                BookStoreApi.addBookToList("9781449331818"));

        step("Удалить эту книгу", () -> {
            ProfilePage.openPage();
            ProfilePage.deleteCertainBook();
        });

        step("Проверить, что книга удалена, через UI", () -> {
            ProfilePage.openPage();
            ProfilePage.checkThatTheBookDeletedUI();
        });

        step("Получить список книг в корзине, и проверить, что книга удалена, через API", () -> {
            GetListOfBooksResponseModel response = AccountApi.getListOfBooks();
            assertThat(response.getBooks()).isEmpty();
        });
    }
}

