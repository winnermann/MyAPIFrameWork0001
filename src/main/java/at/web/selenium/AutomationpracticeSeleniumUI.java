package at.web.selenium;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class AutomationpracticeSeleniumUI {
    public static void login(){
        //Установить в настройки путь к драйверу
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");

        //Инициализирует Вэб драйвер
        ChromeDriver driver = new ChromeDriver();

        //Откравает браузер
        driver.get("http://automationpractice.com");

        //Получает  заголовок страницы в переменную title
        String title = driver.getTitle();

        //Выводит  заголовок (title) страницы в консоль
        System.out.println(title);

        //Проверяет, что заголовок страницы правильный
        Assert.assertTrue(title.equals("My Store"));

        //Закрывает драйвер
        driver.quit();

    }
}
