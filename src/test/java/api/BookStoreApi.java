package api;

import models.AddListOfBooksRequestModel;
import models.IsbnModel;

import java.util.List;

import static data.AuthData.USER_ID;
import static data.AuthData.USER_TOKEN;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.DemoQaBookStoreSpecifications.*;

public class BookStoreApi {

    public static void deleteAllBooksInCart() {


        step("Сделать запрос удаления определенной книги из корзины", () -> {
            given(demoQaBookStoreWithJsonRequest)
                    .header("Authorization", "Bearer " + USER_TOKEN)
                    .queryParam("UserId", USER_ID)

                    .when()
                    .delete("/BookStore/v1/Books")

                    .then()
                    .spec(response204);
        });
    }

    public static void addBookToList(String isbn) {

        AddListOfBooksRequestModel request = new AddListOfBooksRequestModel();
        IsbnModel isbnModel = new IsbnModel();
        isbnModel.setIsbn(isbn);
        request.setUserId(USER_ID);
        request.setCollectionOfIsbns(List.of(isbnModel));


        step("Сделать запрос добавления книги в корзину", () -> {
            given(demoQaBookStoreWithJsonRequest)
                    .header("Authorization", "Bearer " + USER_TOKEN)
                    .body(request)

                    .when()
                    .post("/BookStore/v1/Books")

                    .then()
                    .spec(response201);
        });
    }
}
