package at.web.selenium.PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * Класс запускает нужный браузер по названию
 * из тест-класса POMPageFactoryFindByTest
 */

public class BrowserFactory
{
    static WebDriver driver;
    public static WebDriver StartBrowser(String browserName, String url)
    {
        if (browserName.equalsIgnoreCase("firefox"))
        {
            driver = new FirefoxDriver();

        }

        else if (browserName.equalsIgnoreCase("chrome"))
        {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
            driver = new ChromeDriver();

        }

        else if (browserName.equalsIgnoreCase("chrome_options"))
        {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");

            //Создает переменную downloadFilePath с путем к папке для загрузки файла
            String downloadFilePath = "D:\\SpringStudy\\MyAPIFrameWork0001\\build\\downloads";
            HashMap<String, Object> chromePref = new HashMap<>();
            chromePref.put("profile.default_content_settings.popups", 0);
            //Устанавливает путь из переменной downloadFilePath для загрузки файла
            chromePref.put("download.default_directory", downloadFilePath);
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", chromePref);

            driver = new ChromeDriver(options);

        }
        else if (browserName.equalsIgnoreCase("IE"))
        {
            driver = new InternetExplorerDriver();

        }

        //Открыть браузер
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(url);
        return driver;

    }
}
