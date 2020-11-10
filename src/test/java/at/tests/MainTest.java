package at.tests;

import at.api.*;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

public class MainTest {
    @Test
    @Description(value = "API")
    @Epic("Функциональные тесты")
    @Severity(value = SeverityLevel.NORMAL)

    public void testCases(){
        Login2.login2();
        StarWarsAPI.searchLukeSkywalker();
        StarWarsAPI.searchYavinIV();
        StarWarsAPI.searchDeathStar();
        ReqresInLoginSuccessful.reqresInLoginSuccessful();
        //ReqresInLoginUnsuccessful.reqresInLoginUnsuccessful();
        ReqresInRegisterSuccessful.reqresInRegisterSuccessful();
        ReqresInDeleteUser.ReqresInDeleteUser();
    }
}
