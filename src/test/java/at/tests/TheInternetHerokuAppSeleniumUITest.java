package at.tests;

import at.web.selenium.TheInternetHerokuAppSeleniumUI;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

import java.io.IOException;

public class TheInternetHerokuAppSeleniumUITest {
    @Test
    @Description(value = "UI")
    @Epic("Функциональные тесты Selenium")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCases() throws IOException {
//        TheInternetHerokuAppSeleniumUI.basicAuth();
//        TheInternetHerokuAppSeleniumUI.checkBox();
//        TheInternetHerokuAppSeleniumUI.digestAuthentication();
//        TheInternetHerokuAppSeleniumUI.drugAndDrop();
        TheInternetHerokuAppSeleniumUI.drugAndDropJavaScript();

    }
}
