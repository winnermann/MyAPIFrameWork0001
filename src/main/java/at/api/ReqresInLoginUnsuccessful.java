package at.api;

//import at.common.Config;
import at.common.ConfigReqresInLoginUnsuccessful;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;


/**
 * Класс с методами входа
 */
public class ReqresInLoginUnsuccessful {
    private ReqresInLoginUnsuccessful() {
    }
    /**
     * Вход с параметрами, указанными в конфиге
     */

    public static void reqresInLoginUnsuccessful(){
        reqresInLoginSuccessful(ConfigReqresInLoginUnsuccessful.getSDCDefaultUsername(), ConfigReqresInLoginUnsuccessful.getSDCDefaultPassword());

    }

    /**
     * Войти
     * Во время этого действия будет установлен JSessionID
     * @param username имя пользователя
     * @param password пароль
     */

    @Step("Войти")
    public static void reqresInLoginSuccessful(String username, String password){
        baseURI = ConfigReqresInLoginUnsuccessful.getSDCHost();
        basePath = ConfigReqresInLoginUnsuccessful.getSDCPath();
        //String token;


        Credentials credentials = new Credentials();
        credentials.setUsername(ConfigReqresInLoginUnsuccessful.getSDCDefaultUsername());
        credentials.setPassword(ConfigReqresInLoginUnsuccessful.getSDCDefaultPassword());

        AuthenticationToken authenticationToken =

                given()
                        .accept(ContentType.JSON)
                        .contentType(ContentType.JSON)
                        .body(credentials)
                        .expect()
                        .statusCode(400)
                        .body("error", equalTo("Missing password"))
                .when()
                        .post("/login")
                .then()
                        .log().all()
                        .extract()
                        .body().as(AuthenticationToken.class);
    }

}
