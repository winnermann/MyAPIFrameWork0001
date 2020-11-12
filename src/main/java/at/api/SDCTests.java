package at.api;

import at.api.model.ChangeStatusAndCommentSerialization;
import at.api.model.ChangeStatusWithoutCommentSerialization;
import at.common.Config;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.expect;
import static org.hamcrest.Matchers.containsString;

public class SDCTests {
    public SDCTests() {
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

    @Step("Изменить статус 'Не сверено' на 'Сверено' с внесением 'Комментария'")
    public static void changeStatusAndComment(){
        ChangeStatusAndCommentSerialization csacs = new ChangeStatusAndCommentSerialization();
        csacs.setId("id", 123456);
        csacs.setDeviceId("deviceId", "398102");
        csacs.setTerminalType("terminalType", "CASH_OUT");
        csacs.setComment("comment", "Привет тестировщики1!;%:?*");
        Response response;
        response = given().
                contentType(ContentType.JSON).
                with().
                body(csacs).
                when().
                post("/atm/reconcilation_detail/update.do");
        response.then().
                statusCode(201).
                body(containsString("{\"message\":\"Изменения сохранены\",\"msg\":\"Изменения сохранены\",\"success\":true}")).
                log().all();

    }

    @Step("Изменить статус 'Не сверено' на 'Сверено' без внесения 'Комментария'")
    public static void changeStatusWithoutComment(){
        ChangeStatusWithoutCommentSerialization cswcs = new ChangeStatusWithoutCommentSerialization();
        cswcs.setId("id", 123456);
        cswcs.setDeviceId("deviceId", "398102");
        cswcs.setTerminalType("terminalType", "CASH_OUT");
        cswcs.setComment("comment", null);
        Response response;
        response = given().
                contentType(ContentType.JSON).
                with().
                body(cswcs).
                when().
                post("/atm/reconcilation_detail/update.do");
        response.then().
                statusCode(201).
                body(containsString("{\"message\":\"Изменения сохранены\",\"msg\":\"Изменения сохранены\",\"success\":true}")).
                log().all();
    }

    @Step("Выгрузить отчет в EXCEL")
    public static void downloadReport(){
        HashMap map = new HashMap();
        map.put("reportFormat", "[{\"id\":\"subvision_shortName\",\"type\":\"String\"}]");
        map.put("filter", "[{\"comparison\":\"gte\",\"field\":\"reconcilationDate\"}]");
        map.put("sort", "[]");
        map.put("reportFormat", "EXCEL");
        Response response;
        response = given().
                contentType(ContentType.JSON).
                with().
                body(map).
                when().
                post("/atm/reconcilation/report.do");
        response.then().
                statusCode(201).
                body(containsString("{\"message\":\"Отчет создан\",\"success\":true,\"msg\":\"Отчет создан\"}")).
                log().all();
    }


}
