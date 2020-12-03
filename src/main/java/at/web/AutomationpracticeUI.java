package at.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AutomationpracticeUI {
    public static void login(){
        //Открыть браузер
        System.setProperty("selenide.browser", "chrome");
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
        Configuration.timeout = 6000;
        open("http://automationpractice.com");
        $("#header div.header_user_info a").shouldHave(Condition.text("Sign in")).click();
        //Заполняет поле email невалидным адресом
        $("#email").setValue("admin");
        //Заполняет поле пароль и нажимает клавишу Enter
        $("#passwd").setValue("123456").pressEnter();
        //Проверяет, что отобразилось сообщение "Invalid email address."
        $(By.xpath("//li[contains(text(),'Invalid email address.')]")).shouldHave(Condition.text("Invalid email address."));
    }
}
