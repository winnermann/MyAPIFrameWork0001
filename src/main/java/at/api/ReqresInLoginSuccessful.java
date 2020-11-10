package at.api;

import at.common.ConfigReqresInLoginSuccessful;
import io.qameta.allure.Step;
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


        //Объект credentials из файла Credentials.java
        //устанавливает username,password из файла configReqresInLoginSuccessful.properties
        Credentials credentials = new Credentials();
        credentials.setUsername(ConfigReqresInLoginSuccessful.getSDCDefaultUsername());
        credentials.setPassword(ConfigReqresInLoginSuccessful.getSDCDefaultPassword());

        //Объект token из файла AuthenticationToken.java
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
        //получаем token
        token = authenticationToken.getToken();
        System.out.println(token);
    }

}
