package at.api;

import at.common.Config;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;


/**
 * Класс с методами входа
 */
public class Login {
    private Login() {
    }
    /**
     * Вход с параметрами, указанными в конфиге
     */

    public static void login(){
        login(Config.getSDCDefaultUsername(), Config.getSDCDefaultPassword());

    }

    /**
     * Войти
     * Во время этого действия будет установлен JSessionID
     * @param username имя пользователя
     * @param password пароль
     */

    @Step("Войти")
    public static void login(String username, String password){
        baseURI = Config.getSDCHost();
        port = Config.getSDCPort();
        basePath = Config.getSDCPath();

        String sessionID;
        sessionID = expect().statusCode(200)
                .when().get("/login")
                .sessionId();

        sessionId = expect().
                statusCode(302).
                given().
                param("j_username", username).
                param("j_password", password).
                cookie("JSESSIONID", sessionID).
                post("j_security_check").
                sessionId();
    }

}
