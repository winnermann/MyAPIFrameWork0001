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

import java.io.IOException;

public class POMPageFactoryFindByTest {
    @Test
    @Description(value = "UI")
    @Epic("Функциональные тесты Selenium POM PageFactory with FindBy")
    @Severity(value = SeverityLevel.NORMAL)
    public static void testCases() throws IOException, InterruptedException {
        //Step1
        /**
         * В данном случае чтобы Войти нужно
         * перед URL подставить логин и пароль
         * admin:admin@
         * полная версия "http://admin:admin@the-internet.herokuapp.com/basic_auth"
         */
        WebDriver driver = BrowserFactory.StartBrowser("chrome", "http://admin:admin@the-internet.herokuapp.com/basic_auth");
        POMPageFactoryFindBy basicAuth = PageFactory.initElements(driver, POMPageFactoryFindBy.class);
        basicAuth.basicAuth();

        //Step2
        //This will launch browser with specific URL
        //Запускает нужный браузер из класса BrowserFactory с нужным URL
        driver = BrowserFactory.StartBrowser("chrome", "http://the-internet.herokuapp.com");
        //Created PageObject using PageFactory
        //Создана страница POMPageFactoryFindBy в виде объекта checkBox
        POMPageFactoryFindBy checkBox = PageFactory.initElements(driver, POMPageFactoryFindBy.class);
        //Call the method checkBox()
        //Вызывает метод checkBox()
        checkBox.checkBox();

        //Step3
        /**
         * В данном случае чтобы Войти нужно
         * перед URL подставить логин и пароль
         * admin:admin@
         * полная версия "http://admin:admin@the-internet.herokuapp.com/digest_auth"
         */
        driver = BrowserFactory.StartBrowser("chrome", "http://admin:admin@the-internet.herokuapp.com/digest_auth");
        POMPageFactoryFindBy digestAuthentication = PageFactory.initElements(driver, POMPageFactoryFindBy.class);
        digestAuthentication.digestAuthentication();

        //Step4
//        driver = BrowserFactory.StartBrowser("chrome", "http://the-internet.herokuapp.com");
//        POMPageFactoryFindBy drugAndDrop = PageFactory.initElements(driver, POMPageFactoryFindBy.class);
//        drugAndDrop.drugAndDrop();

        //Step5
        driver = BrowserFactory.StartBrowser("chrome", "http://the-internet.herokuapp.com/drag_and_drop");
        POMPageFactoryFindBy drugAndDropJavaScript = PageFactory.initElements(driver, POMPageFactoryFindBy.class);
        drugAndDropJavaScript.drugAndDropJavaScript();

        //Step6
        driver = BrowserFactory.StartBrowser("chrome", "http://the-internet.herokuapp.com");
        POMPageFactoryFindBy dropDownMenu = PageFactory.initElements(driver, POMPageFactoryFindBy.class);
        dropDownMenu.dropDownMenu();

        //Step7
        driver = BrowserFactory.StartBrowser("chrome_options", "http://the-internet.herokuapp.com/download");
        POMPageFactoryFindBy downloadFile = PageFactory.initElements(driver, POMPageFactoryFindBy.class);
        downloadFile.downloadFile();

        //Step8
        driver = BrowserFactory.StartBrowser("chrome", "http://the-internet.herokuapp.com/upload");
        POMPageFactoryFindBy uploadFile = PageFactory.initElements(driver, POMPageFactoryFindBy.class);
        uploadFile.uploadFile();

    }
}
