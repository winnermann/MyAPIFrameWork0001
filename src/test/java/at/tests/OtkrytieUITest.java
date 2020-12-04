package at.tests;

import at.web.selenide.OtkrytieUI;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

public class OtkrytieUITest {
    @Test
    @Description(value = "UI")
    @Epic("Функциональные тесты")
    @Severity(value = SeverityLevel.NORMAL)

    public void testCases(){
        OtkrytieUI.openBrowser();
        OtkrytieUI.searchInGoogle();
        OtkrytieUI.openMainMenu();
        OtkrytieUI.openCurrencyExMenu();
        OtkrytieUI.compareDollarRates();
        OtkrytieUI.compareEuroRates();

    }

}
