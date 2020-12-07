package at.web.selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import org.testng.Assert;

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
        //Выводит  заголовок страницы в консоль
        System.out.println(Selenide.title());
        //Проверяет, что заголовок страницы правильный
        Assert.assertTrue(Selenide.title().equals("My Store"));
        //Нажать на кнопку "Sign in"
        $("#header div.header_user_info a").shouldHave(Condition.text("Sign in")).click();
        //Заполняет поле email невалидным адресом
        $("#email").setValue("admin");
        //Заполняет поле пароль и нажимает клавишу Enter
        $("#passwd").setValue("123456").pressEnter();
        //Проверяет, что отобразилось сообщение "Invalid email address."
        $(By.xpath("//li[contains(text(),'Invalid email address.')]")).shouldHave(Condition.text("Invalid email address."));
    }
}
