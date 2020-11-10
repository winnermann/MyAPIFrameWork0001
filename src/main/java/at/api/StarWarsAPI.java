package at.api;
import at.common.ConfigStarWarsAPI;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.basePath;
import static org.hamcrest.Matchers.equalTo;

public class StarWarsAPI {

    @Step("Найти героя Luke Skywalker")
    public static void searchLukeSkywalker(){
        baseURI = ConfigStarWarsAPI.getSDCHost();
        basePath = ConfigStarWarsAPI.getSDCPath();
        Response response;
        response = given().
                contentType(ContentType.JSON).
                when().
                get("/people/1/");
        response.then().
                statusCode(200).
                body("name", equalTo("Luke Skywalker")).
                body("homeworld", equalTo("http://swapi.dev/api/planets/1/")).
                body("films[0]", equalTo("http://swapi.dev/api/films/1/")).
                body("films[3]", equalTo("http://swapi.dev/api/films/6/")).
                assertThat().body("species", Matchers.hasSize((0))).
                body("created", equalTo("2014-12-09T13:50:51.644000Z")).
                body("url", equalTo("http://swapi.dev/api/people/1/")).
                log().all();
    }

    @Step("Найти планету Yavin IV")
    public static void searchYavinIV(){
        baseURI = ConfigStarWarsAPI.getSDCHost();
        basePath = ConfigStarWarsAPI.getSDCPath();
        Response response;
        response = given().
                contentType(ContentType.JSON).
                when().
                get("/planets/3/");
        response.then().
                statusCode(200).
                log().all();
    }

    @Step("Найти звездолет Death Star")
    public static void searchDeathStar(){
        baseURI = ConfigStarWarsAPI.getSDCHost();
        basePath = ConfigStarWarsAPI.getSDCPath();
        Response response;
        response = given().
                contentType(ContentType.JSON).
                when().
                get("/starships/9/");
        response.then().
                statusCode(200).
                log().all();
    }
}
