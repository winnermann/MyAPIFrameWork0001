package at.tests;

import at.api.DownloadFileUsingRestAssured;
import at.api.DownloadFileUsingRestAssuredHerokuAppAPI;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

import java.io.IOException;

public class DownloadFileUsingRestAssuredTest {

    @Test
    @Description(value = "API")
    @Epic("Функциональные тесты")
    @Severity(value = SeverityLevel.NORMAL)

    public void testCases() throws IOException {
        DownloadFileUsingRestAssured.canDownloadFilesWithRestAssured();
        DownloadFileUsingRestAssuredHerokuAppAPI.canDownloadFilesWithRestAssured();
    }
}
