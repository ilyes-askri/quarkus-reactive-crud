package org.acme.ressources;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
class PersonResourceTest {



    @Test
    public void testGetUserById() {
        given()
                .pathParam("id", "your_user_id_here")
                .when().get("/users/{id}")
                .then()
                .statusCode(200)
                .body("id", equalTo("your_user_id_here"));
    }
}