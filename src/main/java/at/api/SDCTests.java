package at.api;

import at.api.model.ChangeStatusAndCommentSerialization;
import at.api.model.ChangeStatusWithoutCommentSerialization;
import at.common.Config;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.expect;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

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

    /**
     * В методе changeStatusAndComment() содержится post-запрос
     * с применением Сериализации объектов Rest-Assured
     */
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

    /**
     * В методе changeStatusWithoutComment() содержится post-запрос
     * с применением Сериализации объектов Rest-Assured
     */
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

    /**
     * В методе downloadReport() содержится post-запрос
     * с применением HashMap
     */
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

    /**
     * В методе sverka() содержится GET-запрос
     * с применением queryParam
     */
    @Step("Сверка оборотов за последние 6 дней: делаем запрос с 'Шесть дней назад от текущей даты' по 'Текущая дата' филиал не выбран")
    public static void sverka(){
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy"); //устанавливает в каком формате будет Дата
        Date todayDate = new Date(); //создает объект сегодняшняя дата
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy"); //устанавливает в каком формате будет Дата
        LocalDate defaultDate = LocalDate.now().minusDays(6); //Устанавливет дату defaultDate минус 6 дней от текущей даты
        String formattedStrind = defaultDate.format(formatter); //применяет формат "MM/dd/yyyy" к defaultDate и сохраняет в formattedStrind
        Response response;
        response = given().
                contentType(ContentType.JSON).
                with().
                queryParam("_dc", "01234567891234").
                //устанавливаем дату С formattedStrind (6 дней до текущей даты) ПО dateFormat.format(todayDate) (текущую дату)
                queryParam("filter", "[{\"fieldFrom\": " + " \"" + formattedStrind + "\""+ ",\"fieldTo\":" + " \"" +dateFormat.format(todayDate) + "\""+ "}]").
                //отображается страница первая
                queryParam("page", "1").
                //ркзультаты на странице начиная с нуля
                queryParam("start", "0").
                //на первой странице отображаются не более 25 результатов от 0-го до 24-го (всего результатов 45 начиная с 0 до 44)
                queryParam("limit", "25").
                when().
                get("/atm/reconcilation/list.json");
        response.then().
                statusCode(200).
                body("results.subvision.recincilationProgress[0]", equalTo("BARNAUL")).
                body("results.subvision.recincilationProgress[24]", equalTo("MOSCOW")).
                log().all();

    }


}
