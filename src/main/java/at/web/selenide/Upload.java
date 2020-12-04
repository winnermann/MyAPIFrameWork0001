package at.web.selenide;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class Upload {
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
