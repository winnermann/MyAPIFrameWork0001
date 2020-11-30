package at.web;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.element;
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

        //можно скачивать файлы с помощью встроенного в селенид прокси-сервера
        //включим встроенный в селенид прокси-сервер
//        Configuration.proxyEnabled = true;
//        Configuration.fileDownload = PROXY;

        //Создает папку в которую будет скачан файл
        //Configuration.fileDownload = FOLDER;
        //Configuration.reportsFolder = "download";

        //$("#content > div > a:nth-child(16)").download();

        //Производит загрузку файла text.txt с сервера в папку build/downloads
        element(By.xpath("//a[contains(text(),'text.txt')]")).download();

        //File report = $("#content > div > a:nth-child(16)").download();

        //Удаляет папку downloads с загруженным файлом text.txt
        FileUtils.deleteDirectory(new File("build/downloads"));
    }
}
