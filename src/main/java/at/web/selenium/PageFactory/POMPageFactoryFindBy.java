package at.web.selenium.PageFactory;

import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class POMPageFactoryFindBy {

    WebDriver driver;

    //ldriver - local driver

    public POMPageFactoryFindBy(WebDriver ldriver) {
        this.driver = ldriver;
    }

    //Два варианта записи так @FindBy(id = "header-lk-button") и по стандарту @FindBy(how = How.ID,using = "header-lk-button")
//    @FindBy(id = "header-lk-button")
//    WebElement loginButton;
//    @FindBy(how = How.ID,using = "header-lk-button")
//    WebElement loginButton2;

    @FindBy(how = How.CSS,using = "#content h3")
    WebElement messageBasicDigestAuth;

    @FindBy(how = How.CSS,using = "#content p")
    WebElement message;

    @FindBy(how = How.CSS,using = "#content h1")
    @CacheLookup //Кэширует объект, что бы повторно не искать на странице
    WebElement messageWelcome;

    @FindBy(how = How.CSS,using = "#content li:nth-child(6) a")
    WebElement menuCheckboxes;

    @FindBy(how = How.CSS,using = "#checkboxes input[type=checkbox]:nth-child(1)")
    WebElement checkboxe1;

    @FindBy(how = How.CSS,using = "#checkboxes input[type=checkbox]:nth-child(3)")
    WebElement checkboxe2;

    @FindBy(how = How.CSS,using = "#content li:nth-child(10) a")
    WebElement menuDragAndDrop;

    @FindBy(how = How.CSS,using = "#column-a")
    WebElement draggable;

    @FindBy(how = How.CSS,using = "#column-b")
    WebElement target;

    @FindBy(how = How.CSS,using = "#content li:nth-child(11) a")
    WebElement menuDropdown;

    @FindBy(how = How.CSS,using = "#dropdown")
    WebElement dropdown_;

    @FindBy(how = How.XPATH,using = "//a[contains(text(),'some-file.txt')]")
    WebElement report;

    @FindBy(how = How.CSS,using = "body input")
    WebElement uploadField;

    @FindBy(how = How.ID,using = "file-submit")
    WebElement submitButton;


    @Step("Step Basic Auth (user and pass: admin): Авторизация")
    public void basicAuth(){

        //Проверяет, что на странице есть слова "Basic Auth"
        Assert.assertTrue(messageBasicDigestAuth.getText().equals("Basic Auth"));
        System.out.println(messageBasicDigestAuth.getText());

        //Проверяет, что на странице есть слова "Congratulations! You must have the proper credentials."
        Assert.assertTrue(message.getText().equals("Congratulations! You must have the proper credentials."));
        System.out.println(message.getText());

        //Закрывает драйвер
        driver.quit();

    }

    @Step("Checkboxes: Проставить и снять Чек-бокс")
    public void checkBox(){

        //Убедиться что на странице есть слова "Welcome to the-internet"
        messageWelcome.getText().equals("Welcome to the-internet");

        //Убедиться что ссылка содержит слова "Checkboxes" и перейти по ссылке
        menuCheckboxes.getText().equals("Checkboxes");
        menuCheckboxes.click();

        //Проверяет что Чек-бокс не выбран
        if ( !checkboxe1.isSelected())
        {
            //Проставляет Чек-бокс
            checkboxe1.click();
        }
        //Проверяет что Чек-бокс выбран
        Assert.assertTrue(checkboxe1.isSelected());

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
    public void digestAuthentication(){

        //Проверяет, что на странице есть слова "Digest Auth"
        Assert.assertTrue(messageBasicDigestAuth.getText().equals("Digest Auth"));
        System.out.println(messageBasicDigestAuth.getText());

        //Проверяет, что на странице есть слова "Congratulations! You must have the proper credentials."
        Assert.assertTrue(message.getText().equals("Congratulations! You must have the proper credentials."));
        System.out.println(message.getText());

        //Закрывает драйвер
        driver.quit();
    }

    @Step("Drag and Drop")
    public void drugAndDrop(){

        //Убедиться что на странице есть слова "Welcome to the-internet"
        Assert.assertTrue(messageWelcome.getText().equals("Welcome to the-internet"));

        //Убедиться что ссылка содержит слова "Drag and Drop" и перейти по ссылке
        Assert.assertTrue(menuDragAndDrop.getText().equals("Drag and Drop"));
        menuDragAndDrop.click();

        //Проверяет что элемент КолонкаА содержит текст А
        Assert.assertTrue(draggable.getText().equals("A"));
        //Проверяет что элемент КолонкаБ содержит текст Б
        Assert.assertTrue(target.getText().equals("B"));
        //Перетаскивает элемент КолонкаА на элемент КолонкаБ
        //Первый вариант
        new Actions(driver).dragAndDrop(draggable, target).perform();
        //Второй вариант
        new Actions(driver).clickAndHold(draggable).moveToElement(target).release().perform();
        //Проверяет что элемент КолонкаА содержит текст Б
        Assert.assertTrue(draggable.getText().equals("B"));

        //Закрывает драйвер
        driver.quit();
    }

    @Step("Drag and Drop with JavaScript file")
    public void drugAndDropJavaScript() throws IOException {

        //Проверяет что элемент КолонкаА содержит текст А
        Assert.assertTrue(draggable.getText().equals("A"));

        //Проверяет что элемент КолонкаБ содержит текст Б
        Assert.assertTrue(target.getText().equals("B"));

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
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(javaScript);

        //Проверяет что элемент КолонкаА содержит текст Б
        Assert.assertTrue(draggable.getText().equals("B"));

        //Делает паузу и браузер не закрывается
        //Thread.sleep(500000);

        //Закрывает драйвер
        driver.quit();
    }

    @Step("Dropdown: Выбрать из Dropdown menu сначала Option 2, а потом Option 1")
    public void dropDownMenu(){

        //Убедиться что на странице есть слова "Welcome to the-internet"
        Assert.assertTrue(messageWelcome.getText().equals("Welcome to the-internet"));
        //Убедиться что ссылка содержит слова "Dropdown" и перейти по ссылке
        Assert.assertTrue(menuDropdown.getText().equals("Dropdown"));
        menuDropdown.click();

        //Выбрать из выпадающего меню
        //Select dropdown = new Select(driver.findElement(By.cssSelector("#dropdown")));
        Select dropdown = new Select(dropdown_);

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
    public void downloadFile() throws IOException, InterruptedException {

        //Производит загрузку файла some-file.txt с сервера в папку build/downloads
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
    public void uploadFile() throws InterruptedException {

        //Загрузить файл
        uploadField.sendKeys("D:\\SpringStudy\\MyAPIFrameWork0001\\upload\\uploadFile.txt");
        //Ожидание загрузки файла
        Thread.sleep(2000);
        //Нажать кнопку подтвердить
        submitButton.click();
        //Закрывает драйвер
        driver.close();
    }

}
