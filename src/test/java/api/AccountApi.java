package api;

import models.GetListOfBooksResponseModel;
import models.LoginRequestModel;
import models.LoginResponseModel;

import static helpers.extensions.LoginExtension.cookies;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.DemoQaBookStoreSpecifications.*;

public class AccountApi {

    public static LoginResponseModel getAuthorizationCookie() {
        LoginResponseModel response;
        LoginRequestModel request = new LoginRequestModel(System.getProperty("bookStoreLogin"),
                System.getProperty("bookStorePassword"));

        response = step("Сделать запрос логина, и записать ответ", () ->
                given(demoQaBookStoreWithJsonRequest)
                        .body(request)

                        .when()
                        .post("/Account/v1/Login")

                        .then()
                        .spec(responseLogging)
                        .statusCode(200)
                        .extract().as(LoginResponseModel.class));

        return response;
    }

    public static GetListOfBooksResponseModel getListOfBooks() {
        GetListOfBooksResponseModel response;
        response = step("Сделать запрос списка книг в корзине, и записать его в переменную", () ->
                given(demoQaBookStoreCommonRequest)
                        .header("Authorization", "Bearer " + cookies.getToken())
                        .queryParam("UserId", cookies.getUserId())

                        .when()
                        .get("/Account/v1/User/" + cookies.getUserId())

                        .then()
                        .spec(responseLogging)
                        .statusCode(200)
                        .extract().as(GetListOfBooksResponseModel.class));

        return response;
    }
}
