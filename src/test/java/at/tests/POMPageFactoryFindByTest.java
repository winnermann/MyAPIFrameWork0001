package at.tests;

import at.web.selenium.PageFactory.BrowserFactory;
import at.web.selenium.PageFactory.POMPageFactoryFindBy;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class POMPageFactoryFindByTest {
    @Test
    @Description(value = "UI")
    @Epic("Функциональные тесты Selenium POM PageFactory with FindBy")
    @Severity(value = SeverityLevel.NORMAL)
    public static void testCases(){
        //This will launch browser with specific URL
        //Запускает нужный браузер с нежным URL
        WebDriver driver = BrowserFactory.StartBrowser("chrome");
        //Created PageObject using PageFactory
        //Создана страница POMPageFactoryFindBy в виде объекта checkBox
        POMPageFactoryFindBy checkBox = PageFactory.initElements(driver, POMPageFactoryFindBy.class);
        //Call the method checkBox()
        //Вызывает метод checkBox()
        checkBox.checkBox();
    }
}
