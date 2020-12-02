package at.tests;

import at.web.Download;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

import java.io.IOException;

public class TheInternetHerokuAppUITest {
    @Test
    @Description(value = "UI")
    @Epic("Функциональные тесты")
    @Severity(value = SeverityLevel.NORMAL)

    public void testCases() throws IOException, InterruptedException {
//        TheInternetHerokuAppUI.basicAuth();
//        TheInternetHerokuAppUI.checkBox();
//        TheInternetHerokuAppUI.digestAuthentication();
//        //TheInternetHerokuAppUI.drugAndDrop();
//        TheInternetHerokuAppUI.drugAndDropJavaScript();
//        DragAndDrop.dragAndDrop();
//        TheInternetHerokuAppUI.dropDownMenu();
//        TheInternetHerokuAppUI.downloadFile();
//        TheInternetHerokuAppUI.uploadFile();
//        Upload.uploadFile();
        Download.downloadFile();
    }
}
