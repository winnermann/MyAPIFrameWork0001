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

        // if your url was extracted from a json respose in another message then you might need to decode it first
        // to make sure it is a completely valid URL e.g. doesn't have any \u0026 type values
        //String urlToDownload="https://avatars3.githubusercontent.com/u/2621217?s=40&v=4";

        //Пробба
        String urlToDownload="http://the-internet.herokuapp.com/download/teamWork1.png";
        urlToDownload = UrlDecoder.urlDecode(urlToDownload, Charset.defaultCharset(), false);


        // Sometimes I add a timestamp to the file e.g.
        //String downloadFileName = "downloadedFile_" + System.currentTimeMillis() + "_.txt";

        // Sometimes I add a GUID to the file e.g.
        //String downloadFileName = "downloadedFile_" + UUID.randomUUID() + "_.txt";

        // the point is, control the filename so you know what you are downloading
        String downloadFileName = "teamWork1.png";


        // For the purpose of the test, if the file already exists then I will delete it

        File checkDownloaded = new File(outputPath.getPath(), downloadFileName);
        if(checkDownloaded.exists()) {
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
