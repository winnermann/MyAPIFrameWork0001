package at.api;

import at.common.ConfigPlaceGoatAPI;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class PlaceGoatAPI {

    @Step("Найти картинку козы")
    public static void searchGoat(){
        baseURI = ConfigPlaceGoatAPI.getSDCHost();
        Response response;
        response = given().
                contentType(ContentType.JSON).
                when().
                get("/200");
        response.then().
                statusCode(200).
                log().all();
    }
}
