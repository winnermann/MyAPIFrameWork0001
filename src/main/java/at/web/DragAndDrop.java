package at.web;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.element;
import static com.codeborne.selenide.Selenide.open;

public class DragAndDrop {
    @Step("DragAndDrop")
    public static void dragAndDrop() throws FileNotFoundException, InterruptedException {
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

}
