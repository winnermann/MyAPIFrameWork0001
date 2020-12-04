package at.web.selenide;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.testng.Assert;

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

        //можно скачивать файлы с помощью встроенного в селенид прокси-сервера
        //включить прокси-сервер
        //Configuration.proxyEnabled = true;
        //
        //Configuration.fileDownload = PROXY;

        //Производит загрузку файла some-file.txt с сервера в папку build/downloads
        //File report = $(By.xpath("//a[contains(text(),'some-file.txt')]")).download().withTimeout(4000));

        File report = $(By.xpath("//a[contains(text(),'some-file.txt')]")).download();

        //Проверяет, что файл действительно скачан с сервера
        Assert.assertEquals(report.getName(), "some-file.txt");

        //Выводит в консоль весь путь до файла
        System.out.println(report + " Путь до файла");
        //Выводит в консоль только название файла
        System.out.println(report.getName()+" Имя файла");

        //Удаляет папку downloads с загруженным файлом some-file.txt
        FileUtils.deleteDirectory(new File("build/downloads"));
    }
}
