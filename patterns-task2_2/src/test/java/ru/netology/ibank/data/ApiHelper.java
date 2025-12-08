package ru.netology.ibank.data;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ApiHelper {
    private static final RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    private ApiHelper() {
    }

    public static void createUser(ApiUser user) {
        given()
                .spec(requestSpec)
                .body(user)
        .when()
                .post("/api/system/users")
        .then()
                .statusCode(200);
    }

    public static void createActiveUser(String login, String password) {
        createUser(new ApiUser(login, password, "active"));
    }

    public static void createBlockedUser(String login, String password) {
        createUser(new ApiUser(login, password, "blocked"));
    }
}