package api;

import models.AddListOfBooksRequestModel;
import models.IsbnModel;

import java.util.List;

import static helpers.extensions.LoginExtension.cookies;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.DemoQaBookStoreSpecifications.demoQaBookStoreWithJsonRequest;
import static specs.DemoQaBookStoreSpecifications.responseLogging;

public class BookStoreApi {

    public static void deleteAllBooksInCart() {


        step("Сделать запрос удаления определенной книги из корзины", () -> {
            given(demoQaBookStoreWithJsonRequest)
                    .header("Authorization", "Bearer " + cookies.getToken())
                    .queryParam("UserId", cookies.getUserId())

                    .when()
                    .delete("/BookStore/v1/Books")

                    .then()
                    .spec(responseLogging)
                    .statusCode(204);
        });
    }

    public static void addBookToList(String isbn) {

        IsbnModel isbnModel = new IsbnModel(isbn);
        AddListOfBooksRequestModel request = new AddListOfBooksRequestModel(cookies.getUserId(), List.of(isbnModel));

        step("Сделать запрос добавления книги в корзину", () -> {
            given(demoQaBookStoreWithJsonRequest)
                    .header("Authorization", "Bearer " + cookies.getToken())
                    .body(request)

                    .when()
                    .post("/BookStore/v1/Books")

                    .then()
                    .spec(responseLogging)
                    .statusCode(201);
        });
    }
}
