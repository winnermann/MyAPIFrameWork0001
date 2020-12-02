package at.web;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class Download {
    @Step("File Download: Загрузка файла c сервера")
    public static void downloadFile() throws IOException {
        //Открыть браузер
        System.setProperty("selenide.browser", "chrome");
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
        Configuration.timeout = 6000;
        open("http://the-internet.herokuapp.com/download");

        //Производит загрузку файла some-file.txt с сервера в папку build/downloads
        File report = $(By.xpath("//a[contains(text(),'some-file.txt')]")).download();

        //Удаляет папку downloads с загруженным файлом some-file.txt
        FileUtils.deleteDirectory(new File("build/downloads"));
    }
}
