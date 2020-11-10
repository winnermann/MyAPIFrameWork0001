package at.api;
import at.api.model.AuthenticationToken;
import at.api.model.Credentials;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Login2 {
    public static void login2(){

        Credentials credentials = new Credentials();
        credentials.setUsername("eve.holt@reqres.in");
        credentials.setPassword("cityslicka");

        AuthenticationToken authenticationToken =

                given()
                        .accept("application/json")
                        .contentType("application/json")
                        .body(credentials)
                        .expect()
                        .statusCode(200)
                        .body("token", equalTo("QpwL5tke4Pnpja7X4"))
                .when()
                        .post("https://reqres.in/api/login")
                .then()
                        .log().all()
                        .extract()
                        .body().as(AuthenticationToken.class);
    }
}
