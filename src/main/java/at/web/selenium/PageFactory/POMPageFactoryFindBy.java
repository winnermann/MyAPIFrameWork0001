package at.web.selenium.PageFactory;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

public class POMPageFactoryFindBy {

    WebDriver driver;

    public POMPageFactoryFindBy(WebDriver ldriver) {
        this.driver = ldriver;
    }

    //Два варианта записи так @FindBy(id = "header-lk-button") и по стандарту @FindBy(how = How.ID,using = "header-lk-button")
//    @FindBy(id = "header-lk-button")
//    WebElement loginButton;
//    @FindBy(how = How.ID,using = "header-lk-button")
//    WebElement loginButton2;

    @FindBy(how = How.CSS,using = "#content h1")
    @CacheLookup //Кэширует объект, что бы повторно не искать на странице
    WebElement messageWelcome;

    @FindBy(how = How.CSS,using = "#content li:nth-child(6) a")
    WebElement menuCheckboxes;

    @FindBy(how = How.CSS,using = "#checkboxes input[type=checkbox]:nth-child(1)")
    WebElement checkboxe1;

    @FindBy(how = How.CSS,using = "#checkboxes input[type=checkbox]:nth-child(3)")
    WebElement checkboxe2;

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

}
