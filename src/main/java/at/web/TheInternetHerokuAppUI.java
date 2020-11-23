package at.web;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.element;
import static com.codeborne.selenide.Selenide.open;

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

//    @Step("Step2: Авторизация")
//    public static void basicAuth(){
//        //Убедиться что на странице есть слова "Welcome to the-internet"
//        element(By.cssSelector("#content h1")).shouldHave(text("Welcome to the-internet"));
//        //Убедиться что ссылка содержит слова "Basic Auth" и перейти по ссылке
//        element(By.cssSelector("#content li:nth-child(3)")).shouldHave(text("Basic Auth")).click();
//    }


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
        element(By.cssSelector("#content > div > h3")).shouldHave(text("Digest Auth"));
        element(By.cssSelector("#content > div > p")).shouldHave(text("Congratulations! You must have the proper credentials."));
    }
}
