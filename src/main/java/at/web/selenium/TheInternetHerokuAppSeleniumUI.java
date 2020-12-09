package at.web.selenium;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class TheInternetHerokuAppSeleniumUI {
    private static Object driver;

    @Step("Step Basic Auth (user and pass: admin): Авторизация")
    public static void basicAuth(){
        //Установить в настройки путь к драйверу
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");

        //Инициализирует Вэб драйвер
        ChromeDriver driver = new ChromeDriver();

        //Установить максимальный размер окна браузера
        driver.manage().window().maximize();

        //Ожидает открытия окна 10 секунд
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        /**
         * В данном случае чтобы Войти нужно
         * перед URL подставить логин и пароль
         * admin:admin@
         * полная версия "http://admin:admin@the-internet.herokuapp.com/basic_auth"
         */
        //Откравает браузер
        driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");

        //Проверяет, что на странице есть слова "Basic Auth"
        WebElement messageBasicAuth = driver.findElement(By.cssSelector("#content h3"));
        messageBasicAuth.getText().equals("Basic Auth");

        //Проверяет, что на странице есть слова "Congratulations! You must have the proper credentials."
        WebElement message = driver.findElement(By.cssSelector("#content p"));
        message.getText().equals("Congratulations! You must have the proper credentials.");

        //Закрывает драйвер
        driver.quit();

    }

    @Step("Checkboxes: Проставить и снять Чек-бокс")
    public static void checkBox(){
        //Открыть браузер
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://the-internet.herokuapp.com");

        //Убедиться что на странице есть слова "Welcome to the-internet"
        WebElement messageWelcome = driver.findElement(By.cssSelector("#content h1"));
        messageWelcome.getText().equals("Welcome to the-internet");
        //Убедиться что ссылка содержит слова "Checkboxes" и перейти по ссылке
        WebElement menuCheckboxes = driver.findElement(By.cssSelector("#content li:nth-child(6) a"));
        menuCheckboxes.getText().equals("Checkboxes");
        menuCheckboxes.click();

        //Проверяет что Чек-бокс не выбран
        WebElement checkboxe1 = driver.findElement(By.cssSelector("#checkboxes input[type=checkbox]:nth-child(1)"));
        //Проверяет что Чек-бокс не выбран
        if ( !checkboxe1.isSelected())
        {
            //Проставляет Чек-бокс
            checkboxe1.click();
        }
        //Проверяет что Чек-бокс выбран
        Assert.assertTrue(checkboxe1.isSelected());

        //Проверяет что Чек-бокс выбран
        WebElement checkboxe2 = driver.findElement(By.cssSelector("#checkboxes input[type=checkbox]:nth-child(3)"));

        //Проверяет что Чек-бокс выбран
        if ( checkboxe2.isSelected())
        {
            //Снимает Чек-бокс
            checkboxe2.click();
        }
        //Проверяет что Чек-бокс не выбран
        Assert.assertFalse(checkboxe2.isSelected());

        //Закрывает драйвер
        driver.quit();
    }


    @Step("Step Digest Authentication (user and pass: admin): Авторизация")
    public static void digestAuthentication(){
        //Открыть браузер
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        /**
         * В данном случае чтобы Войти нужно
         * перед URL подставить логин и пароль
         * admin:admin@
         * полная версия "http://admin:admin@the-internet.herokuapp.com/digest_auth"
         */
        driver.get("http://admin:admin@the-internet.herokuapp.com/digest_auth");

        //Проверяет, что на странице есть слова "Digest Auth"
        WebElement messageBasicAuth = driver.findElement(By.cssSelector("#content h3"));
        messageBasicAuth.getText().equals("Digest Auth");

        //Проверяет, что на странице есть слова "Congratulations! You must have the proper credentials."
        WebElement message = driver.findElement(By.cssSelector("#content p"));
        message.getText().equals("Congratulations! You must have the proper credentials.");

        //Закрывает драйвер
        driver.quit();
    }

    @Step("Drag and Drop")
    public static void drugAndDrop(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://the-internet.herokuapp.com");

        //Убедиться что на странице есть слова "Welcome to the-internet"
        WebElement messageWelcome = driver.findElement(By.cssSelector("#content h1"));
        messageWelcome.getText().equals("Welcome to the-internet");

        //Убедиться что ссылка содержит слова "Drag and Drop" и перейти по ссылке
        WebElement menuDragAndDrop = driver.findElement(By.cssSelector("#content li:nth-child(10) a"));
        menuDragAndDrop.getText().equals("Drag and Drop");
        menuDragAndDrop.click();

        //Проверяет что элемент КолонкаА содержит текст А
        WebElement draggable = driver.findElement(By.cssSelector("#column-a"));
        draggable.getText().equals("A");
        //Проверяет что элемент КолонкаБ содержит текст Б
        WebElement target = driver.findElement(By.cssSelector("#column-b"));
        target.getText().equals("B");
        //Перетаскивает элемент КолонкаА на элемент КолонкаБ
        //Первый вариант
        new Actions(driver).dragAndDrop(draggable, target).perform();
        //Второй вариант
        new Actions(driver).clickAndHold(draggable).moveToElement(target).release().perform();
        //Проверяет что элемент КолонкаА содержит текст Б
        draggable.getText().equals("B");

        //Закрывает драйвер
        driver.quit();
    }

    @Step("Drag and Drop with JavaScript file")
    public static void drugAndDropJavaScript() throws IOException {
        //Открыть браузер
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://the-internet.herokuapp.com/drag_and_drop");

        //Проверяет что элемент КолонкаА содержит текст А
        WebElement draggable = driver.findElement(By.cssSelector("#column-a"));
        draggable.getText().equals("A");

        //Проверяет что элемент КолонкаБ содержит текст Б
        WebElement target = driver.findElement(By.cssSelector("#column-b"));
        target.getText().equals("B");

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
        driver.executeScript(javaScript);

        //Проверяет что элемент КолонкаА содержит текст Б
        draggable.getText().equals("B");

        //Делает паузу и браузер не закрывается
        //Thread.sleep(500000);

        //Закрывает драйвер
        driver.quit();
    }

    @Step("Dropdown: Выбрать из Dropdown menu сначала Option 2, а потом Option 1")
    public static void dropDownMenu(){
        //Открыть браузер
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://the-internet.herokuapp.com");

        //Убедиться что на странице есть слова "Welcome to the-internet"
        WebElement messageWelcome = driver.findElement(By.cssSelector("#content h1"));
        messageWelcome.getText().equals("Welcome to the-internet");
        //Убедиться что ссылка содержит слова "Dropdown" и перейти по ссылке
        WebElement menuDropdown = driver.findElement(By.cssSelector("#content li:nth-child(11) a"));
        menuDropdown.getText().equals("Dropdown");
        menuDropdown.click();

        //Выбрать из выпадающего меню
        Select dropdown = new Select(driver.findElement(By.cssSelector("#dropdown")));

        //Вариант 1
        //Выбрать из выпадающего меню Option 2
        dropdown.selectByIndex(2);
        //Выбрать из выпадающего меню Option 1
        dropdown.selectByIndex(1);

        //Вариант 2
        //Выбрать из выпадающего меню Option 2
        dropdown.selectByValue("2");
        //Выбрать из выпадающего меню Option 1
        dropdown.selectByValue("1");

        //Вариант 3
        //Выбрать из выпадающего меню Option 2
        dropdown.selectByVisibleText("Option 2");
        //Выбрать из выпадающего меню Option 1
        dropdown.selectByVisibleText("Option 1");

        //Закрывает драйвер
        driver.quit();

    }

    @Step("File Download: Загрузка файла c сервера")
    public static void downloadFile() throws IOException, InterruptedException {
        //Открыть браузер
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        //Создает переменную downloadFilePath с путем к папке для загрузки файла
        String downloadFilePath = "D:\\SpringStudy\\MyAPIFrameWork0001\\build\\downloads";
        HashMap<String, Object> chromePref = new HashMap<>();
        chromePref.put("profile.default_content_settings.popups", 0);
        //Устанавливает путь из переменной downloadFilePath для загрузки файла
        chromePref.put("download.default_directory", downloadFilePath);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePref);
        ChromeDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://the-internet.herokuapp.com/download");

        //Производит загрузку файла some-file.txt с сервера в папку build/downloads
        WebElement report = driver.findElement(By.xpath("//a[contains(text(),'some-file.txt')]"));
        report.click();
        //Ожидание скачивания файла
        Thread.sleep(5000);
        //Выводит в консоль только название файла
        System.out.println(report.getText()+" Имя файла");
        //Проверяет, что нужный файл скачан с сервера
        Assert.assertTrue(report.getText().equals("some-file.txt"));
        //Удаляет папку downloads с загруженным файлом some-file.txt
        FileUtils.deleteDirectory(new File("build/downloads"));
        //Закрывает драйвер
        driver.quit();
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
