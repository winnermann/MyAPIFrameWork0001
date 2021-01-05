package at.web.selenium.PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

public class BrowserFactory
{
    static WebDriver driver;
    public static WebDriver StartBrowser(String browserName)
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
        else if (browserName.equalsIgnoreCase("IE"))
        {
            driver = new InternetExplorerDriver();

        }

        //Открыть браузер
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://the-internet.herokuapp.com");
        return driver;

    }
}
