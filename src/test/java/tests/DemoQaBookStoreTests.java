package tests;

import api.demoqa_book_store.BookStoreApi;
import helpers.extensions.WithLogin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.ProfilePage;

import static io.qameta.allure.Allure.step;

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

        step("Проверить, что книга удалена", () -> {
            ProfilePage.openPage();
            ProfilePage.checkThatTheBookDeletedUI();
        });
    }
}

