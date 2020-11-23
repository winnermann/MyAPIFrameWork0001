package at.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * Доступ к данным конфига
 */
public class ConfigTheInternetHerokuAppUI {
    private static final Logger logger = LoggerFactory.getLogger(ConfigTheInternetHerokuAppUI.class);

    private ConfigTheInternetHerokuAppUI() {
    }

    private static Properties configTheInternetHerokuAppUI = new Properties();

    static {
        try {
            configTheInternetHerokuAppUI.load(ConfigTheInternetHerokuAppUI.class.getResourceAsStream("/configTheInternetHerokuAppUI.properties"));
        } catch (IOException e) {
            logger.error("Не удалось загрузить конфиг файл", e);
            e.printStackTrace();
        }
    }

    public static String getUIDefaultUsername(){
        return configTheInternetHerokuAppUI.getProperty("sdc.username");
    }

    public static String getUIDefaultPassword(){
        return configTheInternetHerokuAppUI.getProperty("sdc.password");
    }

    public static String getUIHost(){
        return configTheInternetHerokuAppUI.getProperty("ui.host");
    }

    public static int getUIPort(){
        return Integer.parseInt(configTheInternetHerokuAppUI.getProperty("sdc.port"));
    }

    public static String getUIPath(){
        return configTheInternetHerokuAppUI.getProperty("sdc.path");
    }
}
