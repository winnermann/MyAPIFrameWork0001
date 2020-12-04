package at.web.selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.io.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class TheInternetHerokuAppUI {
    private static Object driver;

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

    @Step("Drag and Drop")
    public static void drugAndDrop(){
        System.setProperty("selenide.browser", "chrome");
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
        Configuration.timeout = 6000;
        open("http://the-internet.herokuapp.com");

        //Убедиться что на странице есть слова "Welcome to the-internet"
        element(By.cssSelector("#content h1")).shouldHave(text("Welcome to the-internet"));
        //Убедиться что ссылка содержит слова "Dropdown" и перейти по ссылке
        element(By.cssSelector("#content li:nth-child(10) a")).shouldHave(text("Drag and Drop")).click();

        //Первый вариант
        //Проверяет что элемент КолонкаА содержит текст А
        element(By.cssSelector("#column-a")).shouldHave(text("A"));
        //Перетаскивает элемент КолонкаА на элемент КолонкаБ
        $("#column-a").dragAndDropTo("#column-b");
        //Проверяет что элемент КолонкаА содержит текст Б
        element(By.cssSelector("#column-a")).shouldHave(text("B"));

        //Второй вариант
        //Проверяет что элемент КолонкаА содержит текст А
        element(By.cssSelector("#column-a")).shouldHave(text("A"));
        //Перетаскивает элемент КолонкаА на элемент КолонкаБ, убеждается что действие произошло и
        //проверяет что элемент КолонкаА содержит текст Б
        $("#column-a").dragAndDropTo("#column-b").should(appear).shouldHave(Condition.text("B"));
    }

    @Step("Drag and Drop with JavaScript file")
    public static void drugAndDropJavaScript() throws IOException {
        //Открыть браузер
        System.setProperty("selenide.browser", "chrome");
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
        Configuration.timeout = 6000;
        open("http://the-internet.herokuapp.com/drag_and_drop");

        //Проверяет что элемент КолонкаА содержит текст А
        element(By.cssSelector("#column-a")).shouldHave(text("A"));

        //Создадим объект класса File
        File dnd_javascript = new File("scripts/dnd.js");
        FileReader reader = new FileReader(dnd_javascript);
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuilder builder = new StringBuilder();
        bufferedReader.lines().forEach(
                o->builder.append(o).append('\n')
        );

        String javaScript = builder.toString();
        javaScript = javaScript + " simulateDragDrop(document.getElementById('column-a'),document.getElementById('column-b'));";
        Selenide.executeJavaScript(javaScript);

        //Проверяет что элемент КолонкаА содержит текст Б
        element(By.cssSelector("#column-a")).shouldHave(text("B"));

        //Делает паузу и браузер не закрывается
        //Thread.sleep(500000);
    }

    @Step("Dropdown: Выбрать из Dropdown menu сначала Option 2, а потом Option 1")
    public static void dropDownMenu(){
        //Открыть браузер
        System.setProperty("selenide.browser", "chrome");
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
        Configuration.timeout = 6000;
        open("http://the-internet.herokuapp.com");

        //Убедиться что на странице есть слова "Welcome to the-internet"
        element(By.cssSelector("#content h1")).shouldHave(text("Welcome to the-internet"));
        //Убедиться что ссылка содержит слова "Dropdown" и перейти по ссылке
        element(By.cssSelector("#content li:nth-child(11) a")).shouldHave(text("Dropdown")).click();

        //Выбрать из выпадающего меню
        SelenideElement parentDiv = $("#dropdown");
        // Find `<button>` element and `click()` on it
        //Выбрать из выпадающего меню Option 2
        parentDiv.find("option:nth-child(3)").scrollTo().click();
        //проверяет что меню Option 2 выбрано
        parentDiv.find("option:nth-child(3)").shouldHave(text("Option 2"));

        //Выбрать из выпадающего меню Option 1
        parentDiv.find("option:nth-child(2)").scrollTo().click();
        //проверяет что меню Option 1 выбрано
        parentDiv.find("option:nth-child(2)").shouldHave(text("Option 1"));

    }

    @Step("File Download: Загрузка файла c сервера")
    public static void downloadFile() throws IOException {
        //Открыть браузер
        System.setProperty("selenide.browser", "chrome");
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
        Configuration.timeout = 6000;
        open("http://the-internet.herokuapp.com/download");

        //Производит загрузку файла some-file.txt с сервера в папку build/downloads
        File report = element(By.xpath("//a[contains(text(),'some-file.txt')]")).download();

        //Проверяет, что файл действительно скачан с сервера
        Assert.assertEquals(report.getName(), "some-file.txt");

        //Выводит в консоль весь путь до файла
        System.out.println(report + " Путь до файла");
        //Выводит в консоль только название файла
        System.out.println(report.getName()+" Имя файла");

        //Удаляет папку downloads с загруженным файлом text.txt
        FileUtils.deleteDirectory(new File("build/downloads"));
    }

    @Step("Upload: Загрузка файла на сервер")
    public static void uploadFile(){
        //Открыть браузер
        System.setProperty("selenide.browser", "chrome");
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
        Configuration.timeout = 6000;
        open("http://the-internet.herokuapp.com/upload");
        //Загрузить файл
        $("body input").uploadFile(new File("upload/uploadFile.txt"));
        //Нажать кнопку подтвердить
        element(By.id("file-submit")).click();
        //Проверить что файл загружен
        element(By.id("uploaded-files")).shouldHave(text("uploadFile.txt"));
    }

}
