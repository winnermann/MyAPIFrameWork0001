package at.common;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.Properties;

/**
 * Доступ к данным конфига
 */
public class ConfigReqresInLoginUnsuccessful {
    private static final Logger logger = LoggerFactory.getLogger(ConfigReqresInLoginUnsuccessful.class);

    private ConfigReqresInLoginUnsuccessful() {
    }

    private static Properties configReqresInLoginUnsuccessful = new Properties();

    static {
        try {
            configReqresInLoginUnsuccessful.load(ConfigReqresInLoginUnsuccessful.class.getResourceAsStream("/configReqresInLoginUnsuccessful.properties"));
        } catch (IOException e) {
            logger.error("Не удалось загрузить конфиг файл", e);
            e.printStackTrace();
        }
    }

    public static String getSDCDefaultUsername(){
        return configReqresInLoginUnsuccessful.getProperty("sdc.username");
    }

    public static String getSDCDefaultPassword(){
        return configReqresInLoginUnsuccessful.getProperty("sdc.password");
    }

    public static String getSDCHost(){
        return configReqresInLoginUnsuccessful.getProperty("sdc.host");
    }

    public static int getSDCPort(){
        return Integer.parseInt(configReqresInLoginUnsuccessful.getProperty("sdc.port"));
    }

    public static String getSDCPath(){
        return configReqresInLoginUnsuccessful.getProperty("sdc.path");
    }
}
