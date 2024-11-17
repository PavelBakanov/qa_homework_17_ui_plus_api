package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

public class DemoQaBookStoreSpecifications {

    public static ResponseSpecification responseLogging = new ResponseSpecBuilder()
            .log(LogDetail.STATUS)
            .log(LogDetail.ALL)
            .build();

    public static RequestSpecification demoQaBookStoreWithJsonRequest = with()
            .filter(withCustomTemplates())
            .contentType(JSON)
            .log().all();

    public static RequestSpecification demoQaBookStoreCommonRequest = with()
            .filter(withCustomTemplates())
            .log().all();

}
