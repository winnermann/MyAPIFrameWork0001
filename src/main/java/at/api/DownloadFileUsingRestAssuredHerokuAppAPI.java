package at.api;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.filter.log.UrlDecoder;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class DownloadFileUsingRestAssuredHerokuAppAPI {

    @Test
    @Step("Загрузить файл teamWork1.png в папку downloads")
    public static void canDownloadFilesWithRestAssured() throws IOException {

        //Схраняем путь к файлу в переменную downloadFolder
        String downloadFolder = "downloads";
        //Создаем объект File в папке (или пути из нескольких папок) downloads и сохраняем в переменную outputPath
        File outputPath = new File(downloadFolder);

        //Создает папку (структуру папок), если папка (путь) не существует
        outputPath.mkdirs();

        // sometimes we might be bypassing login or need login credentials created by cookies
        // we can create a hashmap of cookies if we need to
        Map<String, String> cookies = new HashMap();
        // e.g. if I needed to inject a session cookie
        //cookies.put("session_id", Secret.SESSION_ID);

        // sometimes our access controls might be via headers so I might need to set those up
        // we can create a hashmap of headers if we need to
        Map<String, String> headers = new HashMap();
        //cookies.put("X-AUTH-CODE", Secret.AUTH_CODE_HEADER);

        //Схраняем url к файлу в переменную urlToDownload
        String urlToDownload="http://the-internet.herokuapp.com/download/teamWork1.png";
        urlToDownload = UrlDecoder.urlDecode(urlToDownload, Charset.defaultCharset(), false);

        // the point is, control the filename so you know what you are downloading
        String downloadFileName = "teamWork1.png";


        //Проверяет существует ли файл teamWork1.png и удаляет, если существует
        File checkDownloaded = new File(outputPath.getPath(), downloadFileName);
        //Проверяет существует ли файл teamWork1.png
        if(checkDownloaded.exists()) {
            //удаляет файл teamWork1.png
            checkDownloaded.delete();
        }

        // get image using RestAssured
        downloadUrlAsFile(cookies, headers, urlToDownload, outputPath, downloadFileName);


        // Added an assert to check if file exists
        checkDownloaded = new File(outputPath.getPath(), downloadFileName);
        Assert.assertTrue(checkDownloaded.exists());


    }

    private static void downloadUrlAsFile(final Map<String, String> cookies, final Map<String, String> headers, final String urlToDownload, final File outputPath, final String filename) throws IOException {

        File outputFile = new File(outputPath.getPath(), filename);


        final Response response = RestAssured.given().headers(headers).cookies(cookies).when().get(urlToDownload).andReturn();

        // check if the URL actually exists
        if(response.getStatusCode() == 200){

            // I am choosing to delete the file if it already exists and write it again
            // if it already exists you might choose to return and not overwrite it
            if (outputFile.exists()) {
                outputFile.delete();
            }

            // I might choose to use the mime type of the file to control the file extension
            // here I am just outputting it to the console to demonstrate how to get the type
            System.out.println("Downloaded an " + response.getHeader("Content-Type"));

            // get the contents of the file
            byte[] fileContents = response.getBody().asByteArray();

            // output contents to file
            OutputStream outStream=null;

            try {

                outStream = new FileOutputStream(outputFile);
                outStream.write(fileContents);

            }catch(Exception e){

                System.out.println("Error writing file " + outputFile.getAbsolutePath());

            }finally {

                if(outStream!=null){
                    outStream.close();
                }
            }
        }
    }
}
