package at.web.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class AutomationpracticeSeleniumUI {
    public static void login(){
        //Установить в настройки путь к драйверу
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");

        //Инициализирует Вэб драйвер
        ChromeDriver driver = new ChromeDriver();

        //Установить максимальный размер окна браузера
        driver.manage().window().maximize();

        //Ожидает открытия окна 10 секунд
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Откравает браузер
        driver.get("http://automationpractice.com");

        //Получает  заголовок страницы в переменную title
        String title = driver.getTitle();

        //Выводит  заголовок (title) страницы в консоль
        System.out.println(title);

        //Проверяет, что заголовок страницы правильный
        Assert.assertTrue(title.equals("My Store"));

        //Нажать на кнопку "Sign in"
        WebElement element = driver.findElement(By.cssSelector("#header div.header_user_info a"));
        element.click();

        //Заполняет поле email невалидным адресом
        WebElement emailField = driver.findElementByCssSelector("#email");
        emailField.sendKeys("admin");

        //Заполняет поле пароль и нажимает клавишу Enter
        WebElement passwordField = driver.findElementByCssSelector("#passwd");
        passwordField.sendKeys("123456");
        passwordField.sendKeys(Keys.ENTER);

        //Проверяет, что отобразилось сообщение "Invalid email address."
        WebElement message = driver.findElement(By.xpath("//li[contains(text(),'Invalid email address.')]"));
        message.getText().equals("Invalid email address.");

        //Закрывает драйвер
        driver.quit();

    }
}
