package api.authorization;

import data.LoginData;
import models.LoginRequestModel;
import models.LoginResponseModel;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.DemoQaBookStoreSpecifications.demoQaBookStoreWithJsonRequest;
import static specs.DemoQaBookStoreSpecifications.response200;

public class AuthorizationApi {
    public static LoginResponseModel getAuthorizationCookie() {
        LoginResponseModel response;
        LoginRequestModel request = new LoginRequestModel();
        LoginData loginData = new LoginData();
        request.setUserName(loginData.getUserName());
        request.setPassword(loginData.getPassword());

        response = step("Сделать запрос логина, и записать ответ", () ->
                given(demoQaBookStoreWithJsonRequest)
                        .body(request)

                        .when()
                        .post("/Account/v1/Login")

                        .then()
                        .spec(response200)
                        .extract().as(LoginResponseModel.class));

        return response;
    }
}
