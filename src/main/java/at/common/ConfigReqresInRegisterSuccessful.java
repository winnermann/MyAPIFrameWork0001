package at.common;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.Properties;

/**
 * Доступ к данным конфига
 */
public class ConfigReqresInRegisterSuccessful {
    private static final Logger logger = LoggerFactory.getLogger(ConfigReqresInRegisterSuccessful.class);

    private ConfigReqresInRegisterSuccessful() {
    }

    private static Properties configReqresInRegisterSuccessful = new Properties();

    static {
        try {
            configReqresInRegisterSuccessful.load(ConfigReqresInRegisterSuccessful.class.getResourceAsStream("/configReqresInRegisterSuccessful.properties"));
        } catch (IOException e) {
            logger.error("Не удалось загрузить конфиг файл", e);
            e.printStackTrace();
        }
    }

    public static String getSDCDefaultUsername(){
        return configReqresInRegisterSuccessful.getProperty("sdc.username");
    }

    public static String getSDCDefaultPassword(){
        return configReqresInRegisterSuccessful.getProperty("sdc.password");
    }

    public static String getSDCHost(){
        return configReqresInRegisterSuccessful.getProperty("sdc.host");
    }

    public static int getSDCPort(){
        return Integer.parseInt(configReqresInRegisterSuccessful.getProperty("sdc.port"));
    }

    public static String getSDCPath(){
        return configReqresInRegisterSuccessful.getProperty("sdc.path");
    }
}
