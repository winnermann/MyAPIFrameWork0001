package at.common;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.Properties;

/**
 * Доступ к данным конфига
 */
public class ConfigReqresInLoginSuccessful {
    private static final Logger logger = LoggerFactory.getLogger(ConfigReqresInLoginSuccessful.class);

    private ConfigReqresInLoginSuccessful() {
    }

    private static Properties configReqresInLoginSuccessful = new Properties();

    static {
        try {
            configReqresInLoginSuccessful.load(ConfigReqresInLoginSuccessful.class.getResourceAsStream("/configReqresInLoginSuccessful.properties"));
        } catch (IOException e) {
            logger.error("Не удалось загрузить конфиг файл", e);
            e.printStackTrace();
        }
    }

    public static String getSDCDefaultUsername(){
        return configReqresInLoginSuccessful.getProperty("sdc.username");
    }

    public static String getSDCDefaultPassword(){
        return configReqresInLoginSuccessful.getProperty("sdc.password");
    }

    public static String getSDCHost(){
        return configReqresInLoginSuccessful.getProperty("sdc.host");
    }

    public static int getSDCPort(){
        return Integer.parseInt(configReqresInLoginSuccessful.getProperty("sdc.port"));
    }

    public static String getSDCPath(){
        return configReqresInLoginSuccessful.getProperty("sdc.path");
    }
}
