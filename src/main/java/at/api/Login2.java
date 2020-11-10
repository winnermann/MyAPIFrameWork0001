package at.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;

public class Login2 {
    public static void login2(){
//        int code = RestAssured.given()
//                .auth().preemptive()
//                .basic("eve.holt@reqres.in", "cityslicka")
//                .when()
//                .post("https://reqres.in/api/login/")
//                .getStatusCode();
//        System.out.println(code);

//        Response response =
//                RestAssured.given().
//                        header("Content-Type", "application/json").
//                        auth().preemptive().
//                        basic("eve.holt@reqres.in", "cityslicka").
//                        //body(loginPayload).
//                        when().
//                        post("https://reqres.in/api/login/").
//                        then().
//                        log().ifError().
//                        statusCode(200).
//                        contentType("application/vnd.api+json").
//                        body("$", hasKey("authorization_token")).                                   //authorization_token is present in the response
//                        body("any { it.key == 'authorization_token' }", is(notNullValue())).        //authorization_token value is not null - has a value
//                        extract().response();
//        given().auth()
//                .preemptive()
//                .basic("eve.holt@reqres.in", "cityslicka")
//                .when()
//                .post("https://reqres.in/api/login/")
//                .then().log().all();
        //Response response;
        //response = given().auth().form("eve.holt@reqres.in", "cityslicka").
                //contentType(ContentType.JSON).
                //body("{email:\"eve.holt@reqres.in\", password:\"cityslicka\"}").
                //when().
                //post("https://reqres.in/api/login");
        //response.then().
                //statusCode(200).
//                body("name", equalTo("Luke Skywalker")).
//                body("homeworld", equalTo("http://swapi.dev/api/planets/1/")).
//                body("films[0]", equalTo("http://swapi.dev/api/films/1/")).
//                body("films[3]", equalTo("http://swapi.dev/api/films/6/")).
//                assertThat().body("species", Matchers.hasSize((0))).
//                body("created", equalTo("2014-12-09T13:50:51.644000Z")).
//                body("url", equalTo("http://swapi.dev/api/people/1/")).
                //log().all();

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
