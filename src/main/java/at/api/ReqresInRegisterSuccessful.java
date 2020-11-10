package at.api;

import at.common.ConfigReqresInRegisterSuccessful;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;


/**
 * Класс с методами входа
 */
public class ReqresInRegisterSuccessful {
    private ReqresInRegisterSuccessful() {
    }
    /**
     * Вход с параметрами, указанными в конфиге
     */

    public static void reqresInRegisterSuccessful(){
        reqresInRegisterSuccessful(ConfigReqresInRegisterSuccessful.getSDCDefaultUsername(), ConfigReqresInRegisterSuccessful.getSDCDefaultPassword());

    }

    /**
     * Зарегистрироваться
     * @param username имя пользователя
     * @param password пароль
     */

    @Step("Зарегистрироваться успешно")
    public static void reqresInRegisterSuccessful(String username, String password){
        baseURI = ConfigReqresInRegisterSuccessful.getSDCHost();
        basePath = ConfigReqresInRegisterSuccessful.getSDCPath();

        Credentials credentials = new Credentials();
        credentials.setUsername(ConfigReqresInRegisterSuccessful.getSDCDefaultUsername());
        credentials.setPassword(ConfigReqresInRegisterSuccessful.getSDCDefaultPassword());

        Response response;
        response = given().
                accept(ContentType.JSON).
                contentType(ContentType.JSON).
                with().
                body(credentials).
                when().
                post("/register");
        response.then().
                statusCode(200).
                body("id", equalTo(4)).
                body("token", equalTo("QpwL5tke4Pnpja7X4")).
                log().all();
    }
}
