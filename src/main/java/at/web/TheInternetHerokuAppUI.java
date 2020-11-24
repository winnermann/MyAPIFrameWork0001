package at.web;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class TheInternetHerokuAppUI {
    @Step("Step Basic Auth (user and pass: admin): Авторизация")
    public static void basicAuth(){
        //Открыть браузер
        System.setProperty("selenide.browser", "chrome");
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
        Configuration.timeout = 6000;

        /**
         * В данном случае чтобы Войти нужно
         * перед URL подставить логин и пароль
         * admin:admin@
         * полная версия "http://admin:admin@the-internet.herokuapp.com/basic_auth"
         */
        open("http://admin:admin@the-internet.herokuapp.com/basic_auth");
        element(By.cssSelector("#content h3")).shouldHave(text("Basic Auth"));
        element(By.cssSelector("#content p")).shouldHave(text("Congratulations! You must have the proper credentials."));
    }

    @Step("Checkboxes: Проставить и снять Чек-бокс")
    public static void checkBox(){
        //Открыть браузер
        System.setProperty("selenide.browser", "chrome");
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
        Configuration.timeout = 6000;
        open("http://the-internet.herokuapp.com");

        //Убедиться что на странице есть слова "Welcome to the-internet"
        element(By.cssSelector("#content h1")).shouldHave(text("Welcome to the-internet"));
        //Убедиться что ссылка содержит слова "Checkboxes" и перейти по ссылке
        element(By.cssSelector("#content li:nth-child(6) a")).shouldHave(text("Checkboxes")).click();

        //Проверяет что Чек-бокс не выбран
        $(By.cssSelector("#checkboxes input[type=checkbox]:nth-child(1)")).shouldNotBe(selected);
        $(By.cssSelector("#checkboxes input[type=checkbox]:nth-child(1)")).shouldNotBe(checked);
        //Проставляет Чек-бокс
        $(By.cssSelector("#checkboxes input[type=checkbox]:nth-child(1)")).setSelected(true);
        //Проверяет что Чек-бокс выбран
        $(By.cssSelector("#checkboxes input[type=checkbox]:nth-child(1)")).shouldBe(selected);
        $(By.cssSelector("#checkboxes input[type=checkbox]:nth-child(1)")).shouldBe(checked);

        //Проверяет что Чек-бокс выбран
        $(By.cssSelector("#checkboxes input[type=checkbox]:nth-child(3)")).shouldBe(selected);
        $(By.cssSelector("#checkboxes input[type=checkbox]:nth-child(3)")).shouldBe(checked);
        //Снимает Чек-бокс
        $(By.cssSelector("#checkboxes input[type=checkbox]:nth-child(3)")).setSelected(false);
        //Проверяет что Чек-бокс не выбран
        $(By.cssSelector("#checkboxes input[type=checkbox]:nth-child(3)")).shouldNotBe(selected);
        $(By.cssSelector("#checkboxes input[type=checkbox]:nth-child(3)")).shouldNotBe(checked);
    }


    @Step("Step Digest Authentication (user and pass: admin): Авторизация")
    public static void digestAuthentication(){
        //Открыть браузер
        System.setProperty("selenide.browser", "chrome");
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
        Configuration.timeout = 6000;

        /**
         * В данном случае чтобы Войти нужно
         * перед URL подставить логин и пароль
         * admin:admin@
         * полная версия "http://admin:admin@the-internet.herokuapp.com/digest_auth"
         */
        open("http://admin:admin@the-internet.herokuapp.com/digest_auth");
        element(By.cssSelector("#content h3")).shouldHave(text("Digest Auth"));
        element(By.cssSelector("#content p")).shouldHave(text("Congratulations! You must have the proper credentials."));
    }
}
