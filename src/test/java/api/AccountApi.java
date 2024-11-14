package api;

import models.GetListOfBooksResponseModel;

import static data.AuthData.USER_ID;
import static data.AuthData.USER_TOKEN;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.DemoQaBookStoreSpecifications.demoQaBookStoreCommonRequest;
import static specs.DemoQaBookStoreSpecifications.response200;

public class AccountApi {
    public static GetListOfBooksResponseModel getListOfBooks() {
        GetListOfBooksResponseModel response;
        response = step("Сделать запрос списка книг в корзине, и записать его в переменную", () ->
                given(demoQaBookStoreCommonRequest)
                        .header("Authorization", "Bearer " + USER_TOKEN)
                        .queryParam("UserId", USER_ID)

                        .when()
                        .get("/Account/v1/User/" + USER_ID)

                        .then()
                        .spec(response200)
                        .extract().as(GetListOfBooksResponseModel.class));

        return response;
    }
}
