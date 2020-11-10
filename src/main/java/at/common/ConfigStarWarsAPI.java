package at.common;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.Properties;

/**
 * Доступ к данным конфига
 */
public class ConfigStarWarsAPI {
    private static final Logger logger = LoggerFactory.getLogger(ConfigStarWarsAPI.class);

    private ConfigStarWarsAPI() {
    }

    private static Properties configStarWarsAPI = new Properties();

    static {
        try {
            configStarWarsAPI.load(ConfigStarWarsAPI.class.getResourceAsStream("/configStarWarsAPI.properties"));
        } catch (IOException e) {
            logger.error("Не удалось загрузить конфиг файл", e);
            e.printStackTrace();
        }
    }

    public static String getSDCDefaultUsername(){
        return configStarWarsAPI.getProperty("sdc.username");
    }

    public static String getSDCDefaultPassword(){
        return configStarWarsAPI.getProperty("sdc.password");
    }

    public static String getSDCHost(){
        return configStarWarsAPI.getProperty("sdc.host");
    }

    public static int getSDCPort(){
        return Integer.parseInt(configStarWarsAPI.getProperty("sdc.port"));
    }

    public static String getSDCPath(){
        return configStarWarsAPI.getProperty("sdc.path");
    }
}
