package at.common;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.Properties;

/**
 * Доступ к данным конфига
 */
public class ConfigReqresInDeleteUser {
    private static final Logger logger = LoggerFactory.getLogger(ConfigReqresInDeleteUser.class);

    private ConfigReqresInDeleteUser() {
    }

    private static Properties configReqresInDeleteUser = new Properties();

    static {
        try {
            configReqresInDeleteUser.load(ConfigReqresInDeleteUser.class.getResourceAsStream("/configReqresInDeleteUser.properties"));
        } catch (IOException e) {
            logger.error("Не удалось загрузить конфиг файл", e);
            e.printStackTrace();
        }
    }

    public static String getSDCDefaultUsername(){
        return configReqresInDeleteUser.getProperty("sdc.username");
    }

    public static String getSDCDefaultPassword(){
        return configReqresInDeleteUser.getProperty("sdc.password");
    }

    public static String getSDCHost(){
        return configReqresInDeleteUser.getProperty("sdc.host");
    }

    public static int getSDCPort(){
        return Integer.parseInt(configReqresInDeleteUser.getProperty("sdc.port"));
    }

    public static String getSDCPath(){
        return configReqresInDeleteUser.getProperty("sdc.path");
    }
}
