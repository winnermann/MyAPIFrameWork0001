package at.api;

//import at.common.Config;
import at.common.ConfigReqresInLoginSuccessful;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;


/**
 * Класс с методами входа
 */
public class ReqresInLoginSuccessful {
    private ReqresInLoginSuccessful() {
    }
    /**
     * Вход с параметрами, указанными в конфиге
     */

    public static void reqresInLoginSuccessful(){
        reqresInLoginSuccessful(ConfigReqresInLoginSuccessful.getSDCDefaultUsername(), ConfigReqresInLoginSuccessful.getSDCDefaultPassword());

    }

    /**
     * Войти
     * Во время этого действия будет установлен JSessionID
     * @param username имя пользователя
     * @param password пароль
     */

    @Step("Войти")
    public static void reqresInLoginSuccessful(String username, String password){
        baseURI = ConfigReqresInLoginSuccessful.getSDCHost();
        basePath = ConfigReqresInLoginSuccessful.getSDCPath();
        String token;


        Credentials credentials = new Credentials();
        credentials.setUsername(ConfigReqresInLoginSuccessful.getSDCDefaultUsername());
        credentials.setPassword(ConfigReqresInLoginSuccessful.getSDCDefaultPassword());

        AuthenticationToken authenticationToken =

                given()
                        .accept(ContentType.JSON)
                        .contentType(ContentType.JSON)
                        .body(credentials)
                        .expect()
                        .statusCode(200)
                        .body("token", equalTo("QpwL5tke4Pnpja7X4"))
                .when()
                        .post("/login")
                .then()
                        .log().all()
                        .extract()
                        .body().as(AuthenticationToken.class);
        token = authenticationToken.getToken();
        System.out.println(token);

//        String sessionID;
//        sessionID = expect().statusCode(200)
//                .when().get("/login")
//                .sessionId();
//
//        sessionId = expect().
//                statusCode(302).
//                given().
//                param("j_username", username).
//                param("j_password", password).
//                cookie("JSESSIONID", sessionID).
//                post("j_security_check").
//                sessionId();
    }

}
