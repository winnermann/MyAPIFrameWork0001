package at.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * Доступ к данным конфига
 */
public class ConfigPlaceGoatAPI {
    private static final Logger logger = LoggerFactory.getLogger(ConfigPlaceGoatAPI.class);

    private ConfigPlaceGoatAPI() {
    }

    private static Properties configPlaceGoatAPI = new Properties();

    static {
        try {
            configPlaceGoatAPI.load(ConfigPlaceGoatAPI.class.getResourceAsStream("/configPlaceGoatAPI.properties"));
        } catch (IOException e) {
            logger.error("Не удалось загрузить конфиг файл", e);
            e.printStackTrace();
        }
    }

    public static String getSDCDefaultUsername(){
        return configPlaceGoatAPI.getProperty("sdc.username");
    }

    public static String getSDCDefaultPassword(){
        return configPlaceGoatAPI.getProperty("sdc.password");
    }

    public static String getSDCHost(){
        return configPlaceGoatAPI.getProperty("sdc.host");
    }

    public static int getSDCPort(){
        return Integer.parseInt(configPlaceGoatAPI.getProperty("sdc.port"));
    }

    public static String getSDCPath(){
        return configPlaceGoatAPI.getProperty("sdc.path");
    }
}
