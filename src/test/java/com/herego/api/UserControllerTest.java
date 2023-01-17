package com.herego.api;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;

@QuarkusTest
public class UserControllerTest {

    @Test
    public void getByID() {
        given().when().get("/users/15322456")
                .then()
                .statusCode(200);
    }

}
