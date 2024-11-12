package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

public class DemoQaBookStoreSpecifications {
    public static ResponseSpecification response200 = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(LogDetail.STATUS)
            .log(LogDetail.ALL)
            .build();


    public static RequestSpecification demoQaBookStoreLoginRequest = with()
            .filter(withCustomTemplates())
            .contentType(JSON)
            .log().all();

}
