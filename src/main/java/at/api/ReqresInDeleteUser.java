package at.api;
import at.common.ConfigReqresInDeleteUser;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.basePath;

public class ReqresInDeleteUser {

    @Step("Delete User 2")
    public static void ReqresInDeleteUser(){
        baseURI = ConfigReqresInDeleteUser.getSDCHost();
        basePath = ConfigReqresInDeleteUser.getSDCPath();
        Response response;
        response = given().
                contentType(ContentType.JSON).
                when().
                delete("/users/2");
        response.then().
                statusCode(204).
                log().all();
    }
}
