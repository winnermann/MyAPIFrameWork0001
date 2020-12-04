package at.tests;

import at.web.selenide.AutomationpracticeUI;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

public class AutomationpracticeUITest {
    @Test
    @Description(value = "UI")
    @Epic("Функциональные тесты")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCases(){
        AutomationpracticeUI.login();
    }
}
